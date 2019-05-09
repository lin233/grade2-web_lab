package dao.impl;

import dao.CartDao;
import model.Cart;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CartDaoImpl extends HibernateDaoSupport implements CartDao {

	@Transactional(value = "TransactionManager",propagation = Propagation.REQUIRES_NEW)
	public Integer save(Cart cart) {
		return (Integer) getHibernateTemplate().save(cart);//add
	}

	@Transactional(value = "TransactionManager",propagation = Propagation.REQUIRES_NEW)
	public void delete(Cart cart) {
		getHibernateTemplate().delete(cart);
	}

	@Transactional(value = "TransactionManager",propagation = Propagation.REQUIRES_NEW)
	public void update(Cart cart) {
		getHibernateTemplate().merge(cart);
	}

	@Transactional(value = "TransactionManager",propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public Cart getCartById(int id) {
		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) getHibernateTemplate().find(
				"from Cart as b where b.id=?", id);
		Cart cart = carts.size() > 0 ? carts.get(0) : null;
		return cart;
	}

	@Transactional(value = "TransactionManager",propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public List<Cart> getAllCarts() {
		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) getHibernateTemplate()
				.find("from Cart");
		return carts;
	}

}

