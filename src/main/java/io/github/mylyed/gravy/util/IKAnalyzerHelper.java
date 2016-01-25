package io.github.mylyed.gravy.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * IK分词助手
 * 
 * @author lilei
 * 
 *
 */
public class IKAnalyzerHelper {

	static Analyzer analyzer = new IKAnalyzer(true);

	/**
	 * 获取分词结果
	 * @param str
	 * @return
	 */
	public static List<String> getWords(String str) {
		return getWords(str, analyzer);
	}

	/**
	 * 获取分词结果
	 * 
	 * @param 输入的字符串
	 * @param 分词器
	 * @return 分词结果
	 */
	public static List<String> getWords(String str, Analyzer analyzer) {
		List<String> result = new ArrayList<String>();
		TokenStream stream = null;
		try {
			stream = analyzer.tokenStream("content", new StringReader(str));
			CharTermAttribute attr = stream.addAttribute(CharTermAttribute.class);
			stream.reset();
			while (stream.incrementToken()) {
				result.add(attr.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
