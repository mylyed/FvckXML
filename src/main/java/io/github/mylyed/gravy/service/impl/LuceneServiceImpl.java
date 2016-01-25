package io.github.mylyed.gravy.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import io.github.mylyed.gravy.dao.GoodsDAO;
import io.github.mylyed.gravy.entitis.Goods;
import io.github.mylyed.gravy.entitis.Page;
import io.github.mylyed.gravy.service.LuceneService;

@Service("luceneService")
public class LuceneServiceImpl implements LuceneService {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	GoodsDAO goodsDAO;

	static boolean index_ok = false;

	@Override
	public void createIndex() {
		if (index_ok) {
			return;
		}
		try {
			Directory dir = FSDirectory.open(Paths.get("E:\\lucene_index"));
			Analyzer analyzer = new IKAnalyzer(true);
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			iwc.setOpenMode(OpenMode.CREATE);

			IndexWriter writer = new IndexWriter(dir, iwc);
			Long count = goodsDAO.getGoodsCount();
			logger.debug("商品总共{}个", count);
			// 每次读取几条数据
			int num = 100;
			// 循环次数
			int time = (int) (count % num == 0 ? (count / num) : (count / num) + 1);
			logger.debug("循环{}次，每次读取{}条数据", time, num);
			for (int i = 0; i < time; i++) {
				List<Goods> goodss = goodsDAO.getGoods(new Page(num, i + 1));
				long s = System.currentTimeMillis();
				indexDocs(writer, goodss);
				long e = System.currentTimeMillis();
				logger.debug("耗时：{}", e - s);
				logger.debug("完成第{}次，还剩{}次", (i + 1), (time - i - 1));
				writer.commit();
			}

			writer.close();
			index_ok = true;
			logger.debug("初始化索引完成");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void UpdateIndex() {
	}

	private void indexDocs(IndexWriter writer, List<Goods> goods) throws IOException {
		for (Goods g : goods) {
			Document doc = new Document();
			addFields(doc, g);
			// logger.debug("文档内容{}", doc.toString());
			// if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
			writer.addDocument(doc);
			// }
		}
	}

	private static void addFields(Document document, Object o) {
		Class<?> clazz = o.getClass();
		Field[] fs = clazz.getDeclaredFields();
		for (Field f : fs) {
			Object str = getValue(f, o);
			if (str == null) {
				continue;
			}
			IndexableField indexableField = new org.apache.lucene.document.Field(f.getName(), str.toString(),
					TextField.TYPE_STORED);
			document.add(indexableField);
			// if (String.class.isAssignableFrom(f.getType())) {
			// StringField field = new StringField(f.getName(), str.toString(),
			// Store.YES);
			// document.add(field);
			// } else if (Integer.class.isAssignableFrom(f.getType())) {
			// LongField field = new LongField(f.getName(),
			// Long.parseLong(str.toString()), Store.NO);
			// document.add(field);
			// } else {
			// StringField field = new StringField(f.getName(), str.toString(),
			// Store.NO);
			// document.add(field);
			// }
		}

	}

	public static Object getValue(Field f, Object o) {
		String fName = f.getName();
		String name = "get" + fName.substring(0, 1).toUpperCase() + fName.substring(1, fName.length());
		try {
			Method method = o.getClass().getDeclaredMethod(name);
			return method.invoke(o);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void convertGoods(Document d, Goods goods, Highlighter highlighter) {
		for (Field f : Goods.class.getDeclaredFields()) {
			String p = d.get(f.getName());

			if (p == null || p.length() == 0) {
				continue;
			}

			TokenStream tokenStream = analyzer.tokenStream(f.getName(), new StringReader(p));
			try {
				p = highlighter.getBestFragment(tokenStream, p);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (p == null || p.length() == 0) {
				continue;
			}

			if (String.class.isAssignableFrom(f.getType())) {
				setValue(f, goods, p);
			} else if (Integer.class.isAssignableFrom(f.getType())) {
				setValue(f, goods, Integer.parseInt(p));
			} else if (BigDecimal.class.isAssignableFrom(f.getType())) {
				setValue(f, goods, BigDecimal.valueOf(Double.parseDouble(p)));
			}
		}
	}

	public static void setValue(Field f, Object o, Object p) {
		String fName = f.getName();
		String name = "set" + fName.substring(0, 1).toUpperCase() + fName.substring(1, fName.length());
		try {
			Method method = o.getClass().getDeclaredMethod(name, f.getType());
			// Object ob =
			method.invoke(o, p);
			// System.out.println(ob.toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	static Path p = Paths.get("E:\\lucene_index");
	static Directory directory;
	static DirectoryReader ireader;
	static IndexSearcher indexSearcher;
	static Analyzer analyzer = new IKAnalyzer(true);

	static {
		try {
			directory = FSDirectory.open(p);
			ireader = DirectoryReader.open(directory);
			indexSearcher = new IndexSearcher(ireader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Goods> search(String key) {
		// key = key.replaceAll("*", "");
		List<Goods> goods = new ArrayList<>();
		try {
			QueryParser parser = new QueryParser("goods_name", analyzer);
			Query query = parser.parse(key);

			// 高亮显示关键字，如果内容中本来就有<span></span>，可能导致显示错乱
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));

			ScoreDoc[] docs = indexSearcher.search(query, 1000).scoreDocs;
			for (int i = 0; i < docs.length; i++) {
				Goods g = new Goods();
				Document d = indexSearcher.doc(docs[i].doc);
				convertGoods(d, g, highlighter);
				goods.add(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// ireader.close();
				// directory.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return goods;
	}

	public static void main(String[] args) throws ParseException {
		QueryParser parser = new QueryParser("goods_name", analyzer);
		Query query = parser.parse("好啊");
		System.out.println(query.toString());
	}
	// public void search(String f, String k, List<Goods> src) throws Exception
	// {
	// QueryParser parser = new QueryParser(f, analyzer);
	// Query query = parser.parse(k);
	// ScoreDoc[] docs = indexSearcher.search(query, 1000).scoreDocs;
	// for (int i = 0; i < docs.length; i++) {
	// Goods g = new Goods();
	// Document d = indexSearcher.doc(docs[i].doc);
	// convertGoods(d, g);
	// src.add(g);
	// }
	// }
}
