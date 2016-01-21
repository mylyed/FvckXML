package io.github.mylyed.gravy.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.github.mylyed.gravy.entitis.Goods;
import io.github.mylyed.gravy.entitis.Page;

@Component()
public class GoodsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public List<Goods> getGoods(Page page) {
		@SuppressWarnings("unchecked")
		List<Goods> list = sessionFactory.getCurrentSession().createQuery("from Goods")
				.setFirstResult((page.getPageNum() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
		return list;
	}
}
