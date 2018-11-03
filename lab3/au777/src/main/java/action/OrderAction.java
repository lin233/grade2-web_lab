package action;

import model.Order;
import model.User;
import service.AppService;

import java.sql.Date;
import java.util.List;

public class OrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int userid;
	private Date date;
	private int id;


	private AppService appService;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

		Order order = new Order(userid, date);
		appService.addOrder(order);

		return SUCCESS;
	}

	public String add() throws Exception {

		Order order = new Order(userid, date);
		appService.addOrder(order);

		return SUCCESS;
	}

	public String delete() throws Exception {

		Order order = appService.getOrderById(id);
		appService.deleteOrder(order);

		return SUCCESS;
	}

	public String update() throws Exception {

		Order order = appService.getOrderById(id);
		order.setUserid(userid);
		order.setDate(date);
		appService.updateOrder(order);

		return SUCCESS;
	}

	public String allorders() throws Exception {

		List<Order> orders = appService.getAllOrders();
		request().setAttribute("orders", orders);

		List<User> users = appService.getAllUsers();
		request().setAttribute("users", users);

		return SUCCESS;
	}


}
