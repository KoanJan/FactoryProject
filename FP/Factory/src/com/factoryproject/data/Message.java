package com.factoryproject.data;
// Generated 2014-4-21 13:33:54 by Hibernate Tools 3.2.0.CR1


import java.util.Date;

/**
 * Represents a message in the
 * 			database. @author Tony
 * 			Hong (with help from Hibernate)
 */
public class Message  implements java.io.Serializable {


     private int id;
     private String content;
     /**
      * When the message was created
     */
     private Date createdDate;
     /**
      * Messages by this user
     */
     private User speaker;
     private Message replyingMessage;

    public Message() {
    }

	
    public Message(String content, User speaker) {
        this.content = content;
        this.speaker = speaker;
    }
    public Message(String content, Date createdDate, User speaker, Message replyingMessage) {
       this.content = content;
       this.createdDate = createdDate;
       this.speaker = speaker;
       this.replyingMessage = replyingMessage;
    }
   
    public int getId() {
        return this.id;
    }
    
    protected void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    /**       
     *      * When the message was created
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /**       
     *      * Messages by this user
     */
    public User getSpeaker() {
        return this.speaker;
    }
    
    public void setSpeaker(User speaker) {
        this.speaker = speaker;
    }
    public Message getReplyingMessage() {
        return this.replyingMessage;
    }
    
    public void setReplyingMessage(Message replyingMessage) {
        this.replyingMessage = replyingMessage;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("replyingMessage").append("='").append(getReplyingMessage()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }



}


