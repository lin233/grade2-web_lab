package model;

public class Cart {

	private int id;
	private String title;
	private String author;
	private double price;
	private String publisher;
	private String language;
	private int amount;

	public Cart() {
	}

	public Cart(int id,String title, String author, double price, String publisher, String language, int amount) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
		this.language = language;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void setLanguage(String language ) {
		this.language = language;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount ) {
		this.amount = amount;
	}


}
