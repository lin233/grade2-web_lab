package service;

import java.util.List;

import model.*;

/**
 * @author seniyuting
 * @version 1.0
 * 
 */

//这里面，action中所有要用到的数据库操作的函数，只能调dao里面的方法，
public interface AppService {

	/**
	 * book
	 * 
	 */
	public Integer addBook(Book book);

	public void deleteBook(Book book);

	public void updateBook(Book book);

	public Book getBookById(int id);

	public List<Book> getAllBooks();


	/**
	 * user
	 * 
	 */
	public Integer addUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);

	public User getUserById(int id);

	public User getUserByName(String username);

	public List<User> getAllUsers();


	/**
	 * cart
	 *
	 */
	public Integer addCart(Cart cart);

	public void deleteCart(Cart cart);

	public void updateCart(Cart cart);

	public Cart getCartById(int id) ;

	public List<Cart> getAllCarts() ;

}
