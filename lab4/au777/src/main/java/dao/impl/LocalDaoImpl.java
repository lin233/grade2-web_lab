package dao.impl;

import dao.LocalDao;
import model.Local;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class LocalDaoImpl extends HibernateDaoSupport implements LocalDao {

	public void update(Local user) {
		getHibernateTemplate().merge(user);
	}

	public Local getLocalById(int id) {
		@SuppressWarnings("unchecked")
		List<Local> users = (List<Local>) getHibernateTemplate().find(
				"from Local as u where u.id=?", 1);
		Local book = users.size() > 0 ? users.get(0) : null;
		return book;
	}

	public List<Local> getLocal() {
		@SuppressWarnings("unchecked")
		List<Local> users = (List<Local>) getHibernateTemplate()
				.find("from Local");
		return users;
	}

}
