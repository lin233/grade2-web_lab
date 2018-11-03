package action;


//这里面只能调 service里面的函数

import model.Book;
import service.AppService;

import java.sql.Date;
import java.util.List;

public class BookAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private double price;
	private String publisher;
	private String language;
	private int id;

	//控制反转
	private AppService appService;

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

		Book book = new Book(title, author, price, publisher, language);
		appService.addBook(book);

		return SUCCESS;
	}

	public String add() throws Exception {

		Book book = new Book(title, author, price, publisher, language);
		appService.addBook(book);
		return SUCCESS;
	}

	public String delete() throws Exception {

		Book book = appService.getBookById(id);
		appService.deleteBook(book);

		return SUCCESS;
	}

	public String update() throws Exception {

		Book book = appService.getBookById(id);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(price);
		book.setPublisher(publisher);
		book.setLanguage(language);
		appService.updateBook(book);

		return SUCCESS;
	}

	public String allbooks() throws Exception {

		List<Book> books = appService.getAllBooks();
		request().setAttribute("books", books);

		return SUCCESS;
	}
}
