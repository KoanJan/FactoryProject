package com.factoryproject.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9214640148739782031L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ActionContext ctx = ActionContext.getContext();
		Map<String,Object> session = ctx.getSession();
		Object user = session.get("user");
		//�û�δ��¼
		if(user==null){
			HttpServletRequest req = ServletActionContext.getRequest();
			String path = req.getRequestURI().substring(0);
			String queryString = req.getQueryString();
			//Ԥ����ָ��
			if(queryString==null){
				queryString = "";
			}
			String realPath = path + "?" +queryString;
			//ԭҳ�����Ӵ���session
			session.put("prePage",realPath);
//			return "login";
		}
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		
//		ActionContext ctx = ActionContext.getContext();
//		Map<String,Object> session = ctx.getSession();
//		Object user = session.get("user");
//		//�û�δ��¼
//		if(user==null){
//			HttpServletRequest req = ServletActionContext.getRequest();
//			String path = req.getRequestURI().substring(10);
//			String queryString = req.getQueryString();
//			//Ԥ����ָ��
//			if(queryString==null){
//				queryString = "";
//			}
//			String realPath = path + "?" +queryString;
//			//ԭҳ�����Ӵ���session
//			session.put("prePage",realPath);
////			return "login";
//		}
		//�û��Ѿ���¼
		return arg0.invoke();
	}

}
