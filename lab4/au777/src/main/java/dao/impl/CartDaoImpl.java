package dao.impl;

import dao.CartDao;
import model.Cart;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class CartDaoImpl extends HibernateDaoSupport implements CartDao {

	public Integer save(Cart cart) {
		return (Integer) getHibernateTemplate().save(cart);//add
	}

	public void delete(Cart cart) {
		getHibernateTemplate().delete(cart);
	}

	public void update(Cart cart) {
		getHibernateTemplate().merge(cart);
	}

	public Cart getCartById(int id) {
		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) getHibernateTemplate().find(
				"from Cart as b where b.id=?", id);
		Cart cart = carts.size() > 0 ? carts.get(0) : null;
		return cart;
	}

	public List<Cart> getAllCarts() {
		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) getHibernateTemplate()
				.find("from Cart");
		return carts;
	}

}

