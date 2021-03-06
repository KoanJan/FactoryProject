package com.factoryproject.dao;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.*;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import com.factoryproject.HibernateSessionFactory;

/**
 * Interface of all DAOs using Hibernate <br \>
 * 所有使用Hibernate的DAO的接口
 * 
 * @author TonyHong
 * 
 * @param <T>
 *            Type of instance <br \>
 *            存储对象的类型
 * @param <ID>
 *            Type of index <br \>
 *            对象索引的类型
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable>
		implements GenericDAO<T, ID> {
	private Class<T> persistentClass;
	private Session session;

	/**
	 * Construction function <br />
	 */
	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Set current session <br />
	 * @param s
	 *            The current session <br />
	 */
	public void setSession(Session s) {
		this.session = s;
	}

	/**
	 * Get the current session <br />
	 * 
	 * @return The current session <br />
	 */
	protected Session getSession() {
		if (session == null)
			session = HibernateSessionFactory.getSession();
		return session;
	}

	/**
	 * Get the types of persistent class<br />
	 * 
	 * @return The types of persistent class<br />
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public T findById(ID id, boolean lock) {
		T entity;
		if (lock)
			entity = (T) getSession().load(getPersistentClass(), id,
					LockMode.UPGRADE);
		else
			entity = (T) getSession().load(getPersistentClass(), id);
		return entity;
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		List<T> results = crit.add(example).list();
		return results;
	}

	public T makePersistent(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void makeTransient(T entity) {
		getSession().delete(entity);
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	public void close() {
		getSession().close();
	}

	/**
	 * Find specific object/objects by criterion <br />
	 * 
	 * @param criterion
	 *            The reference to the List of criterion for target
	 *            object/objects <br />
	 * @return The reference to the List of object which comes from
	 *         the query by the example <br />
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		List<T> results = crit.list();
		return results;
	}
}
