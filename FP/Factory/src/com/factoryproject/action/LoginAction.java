package com.factoryproject.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.factoryproject.dao.UserHibernateDAO;
import com.factoryproject.data.User;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7574409915963991310L;
	private User user = new User();

	public String execute() {
		ActionContext context = ActionContext.getContext();

		UserHibernateDAO userHome = new UserHibernateDAO();
		List<User> userList = null;
		userList = userHome.getAllUsers();
		userHome.destory();

		// for test
		// System.out.println("Show user list: ");
		boolean hasUser = false;

		for (User u : userList) {
			// System.out.println(user);
			if (u.getUsername().equals(user.getUsername())
					&& u.getPassword().equals(user.getPassword())) {
				hasUser = true;
				context.getSession().put("user", u);
			}
		}

		if (hasUser) {
			return SUCCESS;
		} else {

			return ERROR;
		}
	}

	public void validate() {
		if (user.getUsername() == null || user.getUsername().trim().equals("")) {
			addFieldError("username", getText("username.required"));
		}

		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			addFieldError("password", getText("password.required"));
		}
	}

	@Override
	public User getModel() {
		return user;
	}

	/**
	 * for unit test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LoginAction cua = new LoginAction();
		System.out.println(cua.execute());
	} // EOM

} // EOC
