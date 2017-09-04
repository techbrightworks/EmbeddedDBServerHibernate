package org.srinivas.siteworks;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.srinivas.siteworks.hibernate.Account;
import org.srinivas.siteworks.hibernate.Developer;



public class HibernateClientTest {

	private static final Logger logger = LoggerFactory.getLogger(HibernateClientTest.class);
	private DefaultHttpClient httpclient;

	/**
	 * Start server.
	 */
	@Before
	public void startServer() {
		httpclientCall("http://localhost:8080/EmbeddedServer/Admin_Servlet_Start");

	}

	/**
	 * Shut client.
	 */
	@After
	public void shutClient() {
		httpclientCall("http://localhost:8080/EmbeddedServer/Admin_Servlet_ShutDown");
		httpclient.getConnectionManager().shutdown();
	}

	/**
	 * Test save account.
	 */
	@Test
	public void testSaveAccount() {
		HibernateClientTestUtility.saveAccount();
		List<Developer> developers = HibernateClientTestUtility.queryDevelopers();
		Developer resultDeveloper = HibernateClientTestUtility.findYourDeveloper("srinivas.jasti@techbrightworks.com", developers);
		assertTrue(resultDeveloper.getSkillSet().equals("Java"));
		assertEquals("Srinivas", resultDeveloper.getFirstName());
		List<Account> accounts = HibernateClientTestUtility.queryAccounts();
		Account resultAccount = HibernateClientTestUtility.findYourAccount("srinivas.jasti@techbrightworks.com", accounts);
		assertEquals("srinivas", resultAccount.getUserName());
		Developer resultDeveloperTwo = HibernateClientTestUtility.findYourDeveloper("click.lock@lock.com", developers);
		assertTrue(resultDeveloperTwo.getSkillSet().equals("Shell"));
		assertEquals("Click", resultDeveloperTwo.getFirstName());
		Account resultAccountTwo = HibernateClientTestUtility.findYourAccount("click.lock@lock.com", accounts);
		assertEquals("click123", resultAccountTwo.getUserName());
	}

	/**
	 * Test delete account.
	 */
	@Test
	public void testDeleteAccount() {
		HibernateClientTestUtility.deleteAccount();
		List<Developer> developers = HibernateClientTestUtility.queryDevelopers();
		Developer resultDeveloper = HibernateClientTestUtility.findYourDeveloper("srinivas.jasti@techbrightworks.com", developers);
		assertTrue(resultDeveloper.getSkillSet().equals("Java"));
		assertEquals("Srinivas", resultDeveloper.getFirstName());
		List<Account> accounts = HibernateClientTestUtility.queryAccounts();
		Account resultAccount = HibernateClientTestUtility.findYourAccount("srinivas.jasti@techbrightworks.com", accounts);
		assertEquals("srinivas", resultAccount.getUserName());
		Developer resultDeveloperTwo = HibernateClientTestUtility.findYourDeveloper("click.lock@lock.com", developers);
		assertNull(resultDeveloperTwo);
	}

	/**
	 * Httpclient call. 
	 * @param url           
	 */
	private void httpclientCall(String url) {
		httpclient = new DefaultHttpClient();
		HttpResponse response;
		try {
			HttpPost httpPost = new HttpPost(url);
			response = httpclient.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			logger.info("Response Status:" + response.getStatusLine());
			if (responseEntity != null) {
				logger.info("Response content length: " + responseEntity.getContentLength());
				logger.info(response.toString());
			}
			logger.info("Response Code : " + response.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
