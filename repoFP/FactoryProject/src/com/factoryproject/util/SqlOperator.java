package com.factoryproject.util;

import java.sql.*;

public class SqlOperator {
	public static Connection getConnection(){
		Connection conn = null;
		try{
//			Class.forName(SqlParameter.getDriver());//ע�����ݿ�����
			Class.forName("com.mysql.jdbc.Driver");//����mysql����
			String url = "jdbc:mysql://localhost:3306/test";
			String user = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, user, password);
//			conn = DriverManager.getConnection(SqlParameter.getConnection(),SqlParameter.getUser(),SqlParameter.getPassword());
			System.out.println("���ݿ����ӳɹ�");
		}catch(Exception e){
			System.out.println("���ݿ�����ʧ��..."+e.getMessage());
		}
		return conn;
		
	}

}
