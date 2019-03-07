package dao;

import model.Cart;

import java.util.List;

public interface CartDao {

	public Integer save(Cart cart);

	public void delete(Cart cart);

	public void update(Cart cart);

	public Cart getCartById(int id);

	public List<Cart> getAllCarts();

}

