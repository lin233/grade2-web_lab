//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package action;

import model.User;
import service.AppService;

public class LocalAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private AppService appService;

	public LocalAction() {
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public String execute() throws Exception {
		return "success";
	}

	public String local() throws Exception {
		if(session().getAttribute("username")!=null) return "success";
		else return LOGIN;

	}

	public String logout() throws Exception {
		session().setAttribute("username", null);
		session().setAttribute("login_result", null);
		return "success";
	}


	public String login() throws Exception {
		if(session().getAttribute("username")!=null)return "success";
		User user = this.appService.getUserByName(this.username);
		if (user == null) {
			session().setAttribute("login_result","UserName doesn't exist");
			return LOGIN;
		} else if (!user.getPassword().equals(this.password)) {
			session().setAttribute("login_result","Passsword Wrong");
			return LOGIN;
		} else {
			session().setAttribute("username", this.username);
			session().setAttribute("login_result","login success");
			System.out.println("login success");

			return "success";
		}
	}
}
