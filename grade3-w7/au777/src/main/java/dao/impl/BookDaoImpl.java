package dao.impl;

import java.util.List;

import model.Book;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.BookDao;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BookDaoImpl extends HibernateDaoSupport implements BookDao {

	@Transactional(value = "TransactionManager",propagation = Propagation.REQUIRES_NEW)
	public Integer save(Book book) {
		return (Integer) getHibernateTemplate().save(book);//add
	}

	@Transactional(value = "TransactionManager",propagation = Propagation.REQUIRES_NEW)
	public void delete(Book book) {
		getHibernateTemplate().delete(book);
	}

	@Transactional(value = "TransactionManager",propagation = Propagation.REQUIRES_NEW)
	public void update(Book book) {
		getHibernateTemplate().merge(book);
	}

	@Transactional(value = "TransactionManager",propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
	public Book getBookById(int id) {
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) getHibernateTemplate().find(
				"from Book as b where b.id=?", id);
		Book book = books.size() > 0 ? books.get(0) : null;
		return book;
	}

	@Transactional(value = "TransactionManager",propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public List<Book> getAllBooks() {
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) getHibernateTemplate()
				.find("from Book");
		return books;
	}

}
