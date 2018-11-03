package action;

import model.Book;
import model.Order;
import model.Orderitem;
import service.AppService;

import java.util.List;

public class OrderitemAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int orderid;
	private int bookid;
	private int amount;
	private int id;


	private AppService appService;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

		Orderitem orderitem = new Orderitem(orderid, bookid, amount);
		appService.addOrderitem(orderitem);

		return SUCCESS;
	}

	public String add() throws Exception {

		Orderitem orderitem = new Orderitem(orderid, bookid, amount);
		appService.addOrderitem(orderitem);

		return SUCCESS;
	}

	public String delete() throws Exception {

		Orderitem orderitem = appService.getOrderitemById(id);
		appService.deleteOrderitem(orderitem);

		return SUCCESS;
	}

	public String update() throws Exception {

		Orderitem orderitem = appService.getOrderitemById(id);
		orderitem.setOrderid(orderid);
		orderitem.setBookid(bookid);
		orderitem.setAmount(amount);
		appService.updateOrderitem(orderitem);

		return SUCCESS;
	}

	public String allorderitems() throws Exception {

		List<Orderitem> orderitems = appService.getAllOrderitems();
		request().setAttribute("orderitems", orderitems);

		List<Order> orders = appService.getAllOrders();
		request().setAttribute("orders", orders);

		List<Book> books = appService.getAllBooks();
		request().setAttribute("books", books);

		return SUCCESS;
	}
}
