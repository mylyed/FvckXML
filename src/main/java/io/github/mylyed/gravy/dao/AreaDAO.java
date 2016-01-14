package io.github.mylyed.gravy.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.mylyed.gravy.entitis.Area;

@Service
@Transactional
public class AreaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Area> getAll() {
		@SuppressWarnings("unchecked")
		List<Area> list = sessionFactory.getCurrentSession().createQuery("from Area").list();
		return list;
	}

	public List<Area> getAreasById(String id) {
		@SuppressWarnings("unchecked")
		List<Area> list = sessionFactory.getCurrentSession().createQuery("from Area a where a.parentid =" + id).list();
		return list;
	}
}
