package shh.fvckxml.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shh.fvckxml.entitis.Area;

@Service
public class AreaDAO {
	@Autowired
	SessionFactory sessionFactory;

	public List<Area> getAll() {
		Session s = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Area> list = s.createQuery("from Area").list();
		return list;
	}

	public List<Area> getAreaById(String id) {
		Session s = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Area> list = s.createQuery("from Area a where a.parentid =" + id).list();
		return list;
	}
}
