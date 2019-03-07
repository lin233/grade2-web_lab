package action;


//这里面只能调 service里面的函数
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.Book;
import model.Local;
import model.User;
import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import service.AppService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;


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


		List<Local> users = this.appService.getLocal();
		this.request().setAttribute("localuser", users);

		User user = this.appService.getUserByName(users.iterator().next().getUsername());
		request().setAttribute("user", user);


		// 连接到 mongodb 服务
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		System.out.println("Connect to database successfully");
		MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		System.out.println("集合 test 选择成功");

		FindIterable<Document> findIterable = collection.find();  //获取迭代器

		MongoCursor<Document> mongoCursor = findIterable.iterator();  //获取游标


		ArrayList<String> pic = new ArrayList<String>();
		String a = "";
		while(mongoCursor.hasNext()){  //循环获取数据
			Document out1=mongoCursor.next();
			System.out.println(out1);
			a = out1.getString("likes");
			System.out.println(a);
			pic.add(a);
		}
		request().setAttribute("headphoto", pic );


	/*
		PrintWriter out = response().getWriter();
		response().setContentType("text/html;charset=utf-8");

		out.println("<!DOCTYPE html>\n" +
				"<html>\n" +
				"<body>\n" +
				"<img src=\"data:image/jpg; base64,R0lGODlhFAAUANUAAP//////AP8A//8AAAD//wD/AAAA/wAAAP39APz8APr6APn5APj4APPzAPLyAOrqAODgAN/fANnZANfXAMvLAMnJAL6+AL29ALy8ALu7ALq6ALe3AKysAKurAKamAJ+fAJeXAJSUAJOTAIKCAHV1AHNzAGxsAGJiAGBgAFtbAFZWAFVVAFRUAFNTAFJSAFFRAE5OAP///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAADEALAAAAAAUABQAAAaIwJhwGDsYj8Tk8KDyRBaNiWd1UAoPL01gy91mXFXiweTobo1bhyksZJXNAXQatrzA4ZQTO3XvPkhsRR19AQgjRkkHEH0giEoHDHcbgYlvZh+UYhJ9JZlXg30injF8hBUomQcWhFudSisNrAEhlGMJrAhriS0YfRcsowcpHBEKChEcKaNiR45JQQA7></img>"+
							"</body>\n" +
				"</html>\n" +
				"\n" +
				"\n");

		Iterator<Book> it = books.iterator();

		ArrayList<JSONArray> booksJson = new ArrayList<JSONArray>();
		while (it.hasNext()) {
			Book book = (Book) it.next();
			ArrayList<String> arrayList = new ArrayList<String>();
			String bookid =  Integer.toString(book.getId());
			arrayList.add(bookid);
			arrayList.add(book.getTitle());
			arrayList.add(book.getAuthor());
			arrayList.add(book.getLanguage());
			arrayList.add(book.getPublisher());
			arrayList.add(book.getLanguage());
			booksJson.add(JSONArray.fromObject(arrayList));
		}
		JSONArray books1 = JSONArray.fromArray(booksJson.toArray());

		out.println(books1);
		out.flush();
		out.close();

		String imgFile = "C:\\Users\\My\\Pictures\\Camera Roll\\1.jpg";//待处理的图片
		String imgbese=getImgStr(imgFile);
		System.out.println(imgbese.length());
		System.out.println(imgbese);
*/
		return SUCCESS;
	}


}
