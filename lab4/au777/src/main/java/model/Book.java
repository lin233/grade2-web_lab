package model;

public class Book {

	private int id;
	private String title;
	private String author;
	private double price;
	private String publisher;
	private String language;

	public Book() {
	}

	public Book(String title, String author, double price, String publisher, String language) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
		this.language = language;

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


}
