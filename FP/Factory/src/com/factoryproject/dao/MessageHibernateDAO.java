package com.factoryproject.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;

import com.factoryproject.HibernateSessionFactory;
import com.factoryproject.dao.GenericHibernateDAO;
import com.factoryproject.data.Message;
import com.factoryproject.data.User;

/**
 * The DAO of Message object
 * 
 * @author TonyHong
 */
public class MessageHibernateDAO extends GenericHibernateDAO<Message, Integer> {
	private static Session currentSession;
	private Transaction tx;

	/**
	 * Constructor function <br />
	 * Begin a new transaction automatically <br />
	 */
	public MessageHibernateDAO() {
		openSession();
		beginTransaction();
	}

	/**
	 * Deconstructor function <br />
	 * Must be called after using the object!!! <br />
	 * 一定要在使用完DAO对象后调用！！！
	 */
	public void destory() {
		commitTransaction();
		closeSession();
	}

	/**
	 * Open a new hibernate session by calling getSession() in <br />
	 * <code> com.factoryproject.HibernateSessionFactory </code>
	 * 
	 * @see com.factoryproject.HibernateSessionFactory
	 */
	protected void openSession() {
		currentSession = getSession();
		if (currentSession != null)
			setSession(currentSession);
	}

	/**
	 * Close the single hibernate session instance by calling closeSession() in <br />
	 * <code> com.factoryproject.HibernateSessionFactory </code>
	 * 
	 * @see com.factoryproject.HibernateSessionFactory
	 */
	protected void closeSession() {
		close();
	}

	/**
	 * Begin a new transaction by calling beginTransaction() in <br />
	 * <code>  org.hibernate.Session </code>
	 * 
	 * @see org.hibernate.Transaction
	 */
	protected void beginTransaction() {
		if (currentSession != null)
			tx = currentSession.beginTransaction();
	}

	/**
	 * Commit the current transaction by calling commit() in <br />
	 * <code> org.hibernate.Session </code>
	 * 
	 * @see org.hibernate.Transaction
	 */
	protected void commitTransaction() {
		if (tx != null)
			tx.commit();
	}

	/**
	 * Persist a Message object <br />
	 * 
	 * @param Message
	 * @return
	 */
	public Message addMessage(Message message) {
		makePersistent(message);
		return message;
	}

	/**
	 * Delete a specific Message object <br />
	 * 
	 * @param Message
	 * @return
	 */
	public Message deleteMessage(Message message) {
		makeTransient(message);
		return message;
	}

	/**
	 * Find all Message objects <br />
	 * 
	 * @return a list of all Message objects
	 */
	public List<Message> getAllMessages() {
		return findAll();
	}

	/**
	 * Find a Message object by its index <br />
	 * 
	 * @param messageID
	 * @return an object of index MessageID
	 */
	public Message findMessageByID(Integer messageID) {
		return findById(messageID, false);
	}

	/**
	 * Find the content of the Message object by its index <br />
	 * 
	 * @param messageID
	 * @return The content of the object of index MessageID
	 */
	public String getContentByID(Integer messageID) {
		return findMessageByID(messageID).getContent();
	}

	/**
	 * Find the added date of the Message object by its index <br />
	 * 
	 * @param messageID
	 * @return The added date of the object of index MessageID
	 */
	public Date getCreatedDateByID(Integer messageID) {
		return findMessageByID(messageID).getCreatedDate();
	}

	/**
	 * Find the speaker of the Message object by its index <br />
	 * 
	 * @param messageID
	 * @return The added date of the object of index MessageID
	 */
	public User getSpeakerByID(Integer messageID) {
		return findMessageByID(messageID).getSpeaker();
	}

	/**
	 * Find the replying message ID of the Message object by its index <br />
	 * 
	 * @param messageID
	 * @return The added date of the object of index MessageID
	 */
	public Message getReplyingMessageByID(Integer messageID) {
		return findMessageByID(messageID).getReplyingMessage();
	}

	/**
	 * Find the messages of the Message object by specfic content <br />
	 * 
	 * @param content
	 * @return The added date of the object of index MessageID
	 */
	public Message findMessageByContent(String content) {
		Criteria criteria = getSession().createCriteria(Message.class);
		criteria.add(Restrictions.eq("content", content.trim()));
		return (Message) criteria.uniqueResult();
	}

	/**
	 * Find the messages of the Message object by specfic speaker <br />
	 * 
	 * @param speaker
	 * @return The added date of the object of index MessageID
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findMessagesBySpeaker(User speaker) {
		Criteria criteria = getSession().createCriteria(Message.class);
		List<Message> result = criteria
				.add(Restrictions.eq("speaker", speaker)).list();
		return result;
	}

	/**
	 * Find the messages of the Message object by specfic added date <br />
	 * 
	 * @param addedDate
	 * @return The added date of the object of index MessageID
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findMessagesByDateAdded(Date addedDate) {
		Criteria criteria = getSession().createCriteria(Message.class);
		List<Message> result = criteria.add(
				Restrictions.eq("dateAdded", addedDate)).list();
		return result;
	}

	/**
	 * Find the messages of the Message object by specfic speaker <br />
	 * 
	 * @param replyingMessageID
	 * @return The replyingMessageID of the object of index MessageID
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findMessagesByReplyingMessageID(
			Message replyingMessageID) {
		Criteria criteria = getSession().createCriteria(Message.class);
		List<Message> result = criteria.add(
				Restrictions.eq("replyingMessageID", replyingMessageID)).list();
		return result;
	}
}
