

package com.factoryproject.action;
import java.sql.Connection;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.factoryproject.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;



public  class RegisterAction extends ActionSupport implements ModelDriven<User>{
	
    /**
	 * this action is used for browse-by to register an account
	 */
	private static final long serialVersionUID = 2158346796850031514L;
	
	private User user = new User();

    public String execute() throws Exception{
        
    	//ȡ��������û���(user)������(pwd)
    	user.setUsername(ServletActionContext.getRequest().getParameter("username"));
    	user.setPassword(ServletActionContext.getRequest().getParameter("password"));
    	Connection conn = null;
    	//�����ύ
    	if(user.toString().isEmpty())return INPUT;
    	try{
    		//����������ȡ������
			ValueStack stack = ActionContext.getContext().getValueStack();
			conn = (Connection) stack.getContext().get("conn");
//			if(conn != null){
//				System.out.println("conn:Connection �ǿգ�Action�Ѿ����յ�����Interceptor�Ĳ�����");
//			}else{
//				System.out.println("conn:Connection �գ�Actionδ���յ�����Intercptor�Ĳ�����");
//			}
			
			//�û��������벻��Ϊ��
	    	if(user.getUsername().equals(null)||user.getPassword().equals(null))return INPUT;
	    	//���ݿ�����
	    	Statement stmt = conn.createStatement();
	    	String sql="insert into user(username,password)values('"+user.getUsername()+"','"+user.getPassword()+"')";

	    	//�����ݿ���������û�
	    	int rows = stmt.executeUpdate(sql);
	    	if(rows==0){
	    		return ERROR;
	    	}
	    	else
	    		return SUCCESS;
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		return ERROR;
    	}
        
    }



	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}



 }