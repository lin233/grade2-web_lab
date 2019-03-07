//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package action;

import java.util.List;
import model.Local;
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
		List<Local> users = this.appService.getLocal();
		request().setAttribute("localuser", users);

		String theName = users.get(0).getUsername();
		if(theName.equals("UserName doesn't exist"))return LOGIN;
		if(theName.equals("Passsword Wrong"))return LOGIN;
		if(theName.equals("Please Login"))return LOGIN;
		else{
			return SUCCESS;
		}

	}


	public String logout() throws Exception {

		List<Local> users = this.appService.getLocal();
		request().setAttribute("localuser", users);

		String theName = users.get(0).getUsername();
		if(theName.equals("UserName doesn't exist"))return LOGIN;
		if(theName.equals("Passsword Wrong"))return LOGIN;
		if(theName.equals("Please Login"))return LOGIN;
		else{
			Local rel = this.appService.getLocalById(1);
			rel.setUsername("Please Login");
			this.appService.updateLocal(rel);
			return "success";
		}

	}


	public String update() throws Exception {
		Local user = this.appService.getLocalById(1);
		user.setUsername(this.username);
		this.appService.updateLocal(user);
		return "success";
	}

	public String login() throws Exception {
		User user = this.appService.getUserByName(this.username);

		Local rel = this.appService.getLocalById(1);

		if (user == null) {
			rel.setUsername("UserName doesn't exist");
			this.appService.updateLocal(rel);
			return LOGIN;
		} else if (!user.getPassword().equals(this.password)) {
			rel.setUsername("Passsword Wrong");
			this.appService.updateLocal(rel);
			return LOGIN;
		} else {
			rel.setUsername(this.username);
			this.appService.updateLocal(rel);
			return "success";
		}
	}
}
