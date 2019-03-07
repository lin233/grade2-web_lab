package action;


//这里面只能调 service里面的函数

import model.Cart;
import service.AppService;

import java.util.List;

public class CartAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private double price;
	private String publisher;
	private String language;
	private int amount;
	private int id;

	//控制反转
	private AppService appService;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		Cart cart = new Cart(title, author, price, publisher, language, amount);
		appService.addCart(cart);

		return SUCCESS;
	}

	public String add() throws Exception {

		Cart cart = new Cart(title, author, price, publisher, language, amount);
		appService.addCart(cart);

		return SUCCESS;
	}

	public String delete() throws Exception {

		Cart cart = appService.getCartById(id);
		appService.deleteCart(cart);

		return SUCCESS;
	}

	public String update() throws Exception {

		Cart cart = appService.getCartById(id);
		cart.setTitle(cart.getTitle());
		cart.setAuthor(cart.getAuthor());
		cart.setPrice(cart.getPrice());
		cart.setPublisher(cart.getPublisher());
		cart.setLanguage(cart.getLanguage());
		cart.setAmount(amount);
		appService.updateCart(cart);

		return SUCCESS;
	}

	public String allcarts() throws Exception {

		List<Cart> carts = appService.getAllCarts();
		request().setAttribute("carts", carts);

		return SUCCESS;
	}
}

