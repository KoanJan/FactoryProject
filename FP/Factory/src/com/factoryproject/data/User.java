package com.factoryproject.data;
// Generated 2014-4-21 13:33:54 by Hibernate Tools 3.2.0.CR1



/**
 * 			Represents a user and his/her security
 * 			information. @author Tony Hong (with help
 * 			from Hibernate)
 * 		
 */
public class User  implements java.io.Serializable {


     private int id;
     private String username;
     private String password;
     private int permission;

    public User() {
    }

    public User(String username, String password, int permission) {
       this.username = username;
       this.password = password;
       this.permission = permission;
    }
   
    public int getId() {
        return this.id;
    }
    
    protected void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPermission() {
        return this.permission;
    }
    
    public void setPermission(int permission) {
        this.permission = permission;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("username").append("='").append(getUsername()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


