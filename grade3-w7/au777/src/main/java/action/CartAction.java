package action;


//这里面只能调 service里面的函数

import model.Cart;
import service.AppService;

import java.util.ArrayList;
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
		List<Cart> carts = new ArrayList<Cart>();
		if(session().getAttribute("carts")==null){
			session().setAttribute("carts",carts);
		}
		else{
			carts = (List<Cart>) session().getAttribute("carts");
		}
		Cart cart = new Cart(id,title, author, price, publisher, language, amount);
		carts.add(cart);
		session().setAttribute("carts",carts);
		return SUCCESS;
	}

	public String add() throws Exception {

		List<Cart> carts = new ArrayList<Cart>();
		if(session().getAttribute("carts")==null){
			session().setAttribute("carts",carts);
		}
		else{
			carts = (List<Cart>) session().getAttribute("carts");
		}
		Cart cart = new Cart(id, title, author, price, publisher, language, amount);
		carts.add(cart);
		session().setAttribute("carts",carts);
		System.out.println(id+"!!!!!!!!");
		return SUCCESS;
	}

	public String delete() throws Exception {
		List<Cart> carts = new ArrayList<Cart>();
		carts = (List<Cart>) session().getAttribute("carts");
		for(Cart x:carts){
			if(x.getId()==id){
				carts.remove(x);
			}
		}
		session().setAttribute("carts",carts);
		return SUCCESS;
	}

	public String update() throws Exception {
		List<Cart> carts = new ArrayList<Cart>();
		carts = (List<Cart>) session().getAttribute("carts");
		for(int i = 0;i<carts.size();i++){
			Cart x=carts.get(i);
			if(x.getId()==id){
				x.setAmount(amount);
				carts.set(i,x);
			}
		}
		session().setAttribute("carts",carts);
		return SUCCESS;
	}

	public String allcarts() throws Exception {

		return SUCCESS;
	}
}

