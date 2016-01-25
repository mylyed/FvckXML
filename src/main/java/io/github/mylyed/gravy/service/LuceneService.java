package io.github.mylyed.gravy.service;

import java.util.List;

import io.github.mylyed.gravy.entitis.Goods;

public interface LuceneService {
	/**
	 * 创建索引
	 */
	public void createIndex();

	/**
	 * 搜索
	 * 
	 * @param key
	 *            关键字
	 * @return
	 */
	public List<Goods> search(String key);
}
