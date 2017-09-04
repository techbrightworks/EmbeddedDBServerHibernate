package org.srinivas.siteworks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.srinivas.siteworks.hibernate.Account;
import org.srinivas.siteworks.hibernate.Developer;


public class HibernateClientTestUtility {
	

	private static final Logger logger = LoggerFactory.getLogger(HibernateClientTestUtility.class);
	/**
	 * Save account.
	 */
	public static void saveAccount() {
		Session session = null;
		Transaction tx = null;
		Configuration configuration = new AnnotationConfiguration().configure();
		configuration.setProperty("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost/data/embeddeddb;shutdown=false;close_result=true");
		SessionFactory sf = configuration.buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();

		Account account = new Account();
		account.setUserName("click123");
		account.setPassword("password");
		account.setCreatedDate(new Date());

		Account accountTwo = new Account();
		accountTwo.setUserName("srinivas");
		accountTwo.setPassword("password");
		accountTwo.setCreatedDate(new Date());

		Developer developer = new Developer();
		developer.setFirstName("Click");
		developer.setLastName("Lock");
		developer.setEmailAddress("click.lock@lock.com");
		developer.setSkillSet("Shell");
		account.setDeveloper(developer);

		Developer developerTwo = new Developer();
		developerTwo.setFirstName("Srinivas");
		developerTwo.setLastName("Jasti");
		developerTwo.setEmailAddress("srinivas.jasti@techbrightworks.com");
		developerTwo.setSkillSet("Java");
		accountTwo.setDeveloper(developerTwo);

		try {
			session.save(developerTwo);
			session.save(accountTwo);
			session.save(developer);
			session.save(account);

			tx.commit();
			session.flush();
			session.close();
			sf.close();

		} catch (HibernateException e) {

			e.printStackTrace();

			if (tx != null && tx.isActive())
				tx.rollback();
		}
	}

	/**
	 * Delete account.
	 */
	@SuppressWarnings("unchecked")
	public static void deleteAccount() {
		Session session = null;
		Transaction tx = null;

		SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("userName", "click123"));

		List<Account> users = (ArrayList<Account>) criteria.list();

		for (Account user : users) {
			logger.info("Deleting User:");
			session.delete(user.getDeveloper());
			session.delete(user);
		}
		tx.commit();
		session.close();
		sf.close();
	}

	/**
	 * Query developers. 
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static List<Developer> queryDevelopers() {
		Session session;
		Query query;
		SessionFactory sf3 = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sf3.openSession();

		session.beginTransaction();

		query = (Query) session.createQuery("from Developer");

		List<Developer> developers = (ArrayList<Developer>) query.list();

		for (Developer developer : developers) {
			logger.info("developerId is:  " + developer.getDeveloperId());
			logger.info("firstname is:  " + developer.getFirstName());
			logger.info("lastname is:  " + developer.getLastName());
			logger.info("email is:  " + developer.getEmailAddress());
			logger.info("skillset is:  " + developer.getSkillSet());
		}
		session.getTransaction().commit();
		logger.info("developers size is:" + developers.size());
		session.close();
		sf3.close();
		return developers;
	}

	/**
	 * Query accounts. 
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static List<Account> queryAccounts() {
		Session session;
		SessionFactory sf2 = new AnnotationConfiguration().configure().buildSessionFactory();
		session = sf2.openSession();
		session.beginTransaction();
		Query query = (Query) session.createQuery("from Account");
		List<Account> accounts = (ArrayList<Account>) query.list();
		for (Account account : accounts) {
			logger.info("usernameis: " + account.getUserName());
			logger.info("password is:  " + account.getPassword());
			logger.info("accountid is:  " + account.getAccountId());
			logger.info("createdDate is:  " + account.getCreatedDate());
		}
		session.getTransaction().commit();
		logger.info("accounts size is:" + accounts.size());
		session.close();
		sf2.close();
		return accounts;
	}

	/**
	 * Find your account. 
	 * @param emailAddress
	 * @param accounts
	 * @return the account
	 */
	public static Account findYourAccount(String emailAddress, List<Account> accounts) {
		Account resultAccount = null;
		for (Account account : accounts) {
			if (account.getDeveloper().getEmailAddress().equals(emailAddress)) {
				resultAccount = account;
			}

		}
		return resultAccount;
	}

	/**
	 * Find your developer. 
	 * @param emailAddress
	 * @param developers
	 * @return the developer
	 */
	public static Developer findYourDeveloper(String emailAddress, List<Developer> developers) {
		Developer resultDeveloper = null;
		for (Developer developer : developers) {
			if (developer.getEmailAddress().equals(emailAddress)) {
				resultDeveloper = developer;
			}

		}
		return resultDeveloper;
	}
}
