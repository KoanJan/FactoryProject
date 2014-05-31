

package com.factoryproject.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;
import com.factoryproject.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;




public  class LoginAction extends ActionSupport implements ModelDriven<User>{

	/**
	 * this action is for an account to login
	 */
	private static final long serialVersionUID = -7325326470596711055L;

	private User user = new User();
	
	//��¼֮ǰ��ҳ��
	private String prePage = null;
	
    public String getPrePage() {
		return prePage;
	}


	public String execute() throws Exception{
        
    	ActionContext ctx = ActionContext.getContext();
    	//��ȡ��¼ǰҳ��
    	prePage = (String) ctx.getSession().get("prePage");
    	
    	//ȡ��������û���(user)������(pwd)
    	user.setUsername(ServletActionContext.getRequest().getParameter("username"));
    	user.setPassword(ServletActionContext.getRequest().getParameter("password"));
    	    	Connection conn = null;
		try{
			
			//����������ȡ������
			ValueStack stack = ActionContext.getContext().getValueStack();
			conn = (Connection) stack.getContext().get("conn");
//			if(conn != null){
//				System.out.println("conn:Connection �ǿգ�Action�Ѿ����յ�����Interceptor�Ĳ�����");
//			}else{
//				System.out.println("conn:Connection �գ�Actionδ���յ�����Intercptor�Ĳ�����");
//			}
			
			//�û�������Ϊ��
	    	if(user.getUsername().equals(null))return "input";
	    	//���ݿ�����
	    	Statement stmt = conn.createStatement();
	    	ResultSet rs = null;
	    	String sql="select * from user where username ='"+user.getUsername()+"'";

	    	//��ѯ�����û�����Ӧ����ȷ����
	    	rs = stmt.executeQuery(sql);
	    	String correctPassword = null;
	    	
	    	//ȡ����ȷ����
	    	while(rs.next()){
	    		correctPassword = rs.getString(2);
	    	}
	    	//��������Ƿ���ȷ
	    	if(user.getPassword().equals(correctPassword)){
	    		//�����¼��Ϣ��session
	    		ctx.getSession().put("user", user);
	    		System.out.println("��¼�ɹ�");
	    		
	    		//��ȡsession�������Ƿ�д��session�ɹ�
//	    		System.out.println("ctx.getSession().get(\"user\")="+ctx.getSession().get("user").toString());
	    		
	    		//���ʵ�¼ҳ�淽ʽ
	    		if(prePage==null){
	    			return "home";
	    		}
	    		else return "success";
	    	}
	    	else{
	    		//���벻��ȷ
	    		System.out.println("��¼ʧ��");
//	    		inputstream = new ByteArrayInputStream("��¼ʧ�ܣ�".getBytes("utf-8"));
	    		return "input";
	    	}
			
			
		}catch(Exception e){
			System.out.println("���ݿ�����ʧ��..."+e.getMessage());
			return "error";
		}
		
    }


	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
 }