package service.impl;

import java.util.List;

import dao.*;
import model.Book;
import model.User;
import model.Cart;
import service.AppService;

/**
 * @author seniyuting
 * @version 1.0
 * 
 */
public class AppServiceImpl implements AppService {

	
	//这个dao是控制反转
	private BookDao bookDao;

	private UserDao userDao;

	private CartDao cartDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	/**
	 * book
	 * 
	 */
	public Integer addBook(Book book) {
		return bookDao.save(book);
	}

	public void deleteBook(Book book) {
		bookDao.delete(book);
	}

	public void updateBook(Book book) {
		bookDao.update(book);
	}

	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}

	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}


	/**
	 * user
	 * 
	 */
	public Integer addUser(User user) {
		return userDao.save(user);
	}

	public void deleteUser(User user) {
		userDao.delete(user);
	}

	public void updateUser(User user) {
		userDao.update(user);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}


	/**
	 * cart
	 *
	 */
	public Integer addCart(Cart cart) {
		return cartDao.save(cart);
	}

	public void deleteCart(Cart cart) {
		cartDao.delete(cart);
	}

	public void updateCart(Cart cart) {
		cartDao.update(cart);
	}

	public Cart getCartById(int id) {
		return cartDao.getCartById(id);
	}

	public List<Cart> getAllCarts() {
		return cartDao.getAllCarts();
	}




}
