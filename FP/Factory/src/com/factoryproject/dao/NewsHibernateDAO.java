package com.factoryproject.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;

import com.factoryproject.dao.GenericHibernateDAO;
import com.factoryproject.data.News;
import com.factoryproject.data.User;

/**
 * The DAO of News object
 * 
 * @author TonyHong
 */
public class NewsHibernateDAO extends GenericHibernateDAO<News, Integer> {
	private static Session currentSession;
	private Transaction tx;

	/**
	 * Constructor function <br />
	 * Begin a new transaction automatically <br />
	 */
	public NewsHibernateDAO() {
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
	 * Persist a News object <br />
	 * 
	 * @param News
	 * @return
	 */
	public News addNews(News message) {
		makePersistent(message);
		return message;
	}

	/**
	 * Delete a specific News object <br />
	 * 
	 * @param News
	 * @return
	 */
	public News deleteNews(News message) {
		makeTransient(message);
		return message;
	}

	/**
	 * Find all News objects <br />
	 * 
	 * @return a list of all News objects
	 */
	public List<News> getAllNewss() {
		return findAll();
	}

	/**
	 * Find a News object by its index <br />
	 * 
	 * @param messageID
	 * @return an object of index NewsID
	 */
	public News findNewsByID(Integer messageID) {
		return findById(messageID, false);
	}

	/**
	 * Find the content of the News object by its index <br />
	 * 
	 * @param messageID
	 * @return The content of the object of index NewsID
	 */
	public String getContentByID(Integer messageID) {
		return findNewsByID(messageID).getContent();
	}

	/**
	 * Find the added date of the News object by its index <br />
	 * 
	 * @param messageID
	 * @return The added date of the object of index NewsID
	 */
	public Date getCreatedDateByID(Integer messageID) {
		return findNewsByID(messageID).getCreatedDate();
	}

	/**
	 * Find the pubisher of the News object by its index <br />
	 * 
	 * @param messageID
	 * @return The added date of the object of index NewsID
	 */
	public User getPublisherByID(Integer messageID) {
		return findNewsByID(messageID).getPublisher();
	}

	/**
	 * Find the messages of the News object by specfic content <br />
	 * 
	 * @param content
	 * @return The added date of the object of index NewsID
	 */
	public News findNewsByContent(String content) {
		Criteria criteria = getSession().createCriteria(News.class);
		criteria.add(Restrictions.eq("content", content.trim()));
		return (News) criteria.uniqueResult();
	}

	/**
	 * Find the messages of the News object by specfic pubisher <br />
	 * 
	 * @param pubisher
	 * @return The added date of the object of index NewsID
	 */
	@SuppressWarnings("unchecked")
	public List<News> findNewssByPubisher(User pubisher) {
		Criteria criteria = getSession().createCriteria(News.class);
		List<News> result = criteria
				.add(Restrictions.eq("pubisher", pubisher)).list();
		return result;
	}

	/**
	 * Find the messages of the News object by specfic added date <br />
	 * 
	 * @param createdDate
	 * @return The added date of the object of index NewsID
	 */
	@SuppressWarnings("unchecked")
	public List<News> findNewssByCreatedDate(Date createdDate) {
		Criteria criteria = getSession().createCriteria(News.class);
		List<News> result = criteria.add(
				Restrictions.eq("createdDate", createdDate)).list();
		return result;
	}

}
