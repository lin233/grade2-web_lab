package action;

import model.Book;
import model.User;
import service.AppService;

import java.util.List;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String role;
	private String phone;
	private String email;
	private int id;

	private AppService appService;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

		User user = new User(username, password, role, phone,email);
		appService.addUser(user);

		return SUCCESS;
	}

	public String add() throws Exception {

		User user = new User(username, password, role ,phone,email);

			appService.addUser(user);
			//return LOGIN;
           return "success";
	}

	public String delete() throws Exception {

		User user = appService.getUserById(id);
		appService.deleteUser(user);
		return SUCCESS;
	}

	public String allusers() throws Exception {

		List<User> users = appService.getAllUsers();
		request().setAttribute("users", users);

		return SUCCESS;
	}

	public String update() throws Exception {

		User user = appService.getUserById(id);


		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		appService.updateUser(user);

		return SUCCESS;
	}




}
