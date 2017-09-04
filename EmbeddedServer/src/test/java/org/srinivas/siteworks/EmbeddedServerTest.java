package org.srinivas.siteworks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.DispatcherServlet;
import org.srinivas.siteworks.hibernate.Admin_Servlet_Start;
import org.srinivas.siteworks.hibernate.ServerAdmin;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/spring-servlet.xml") 
public class EmbeddedServerTest {
	
	/**
	 * Test start embedded server page. 
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testStartEmbeddedServerPage() throws ServletException, IOException {		
		MockHttpServletRequest request = new MockHttpServletRequest(new MockServletContext(""), "POST", "/StartPage.jsp");	
		MockHttpServletResponse response = new MockHttpServletResponse();
	    DispatcherServlet dispatcherServlet = new DispatcherServlet() ; 
	    dispatcherServlet.setContextConfigLocation("file:src/test/resources/spring-servlet.xml"); 
		dispatcherServlet.init(new MockServletConfig());	    
	    dispatcherServlet.service(request, response);
	    String resp = response.getContentAsString();		
	    assertEquals("Server_Started", resp.trim());
		shutServer();
	}
	
	/**
	 * Test shut down embedded server page. 
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testShutDownEmbeddedServerPage() throws ServletException, IOException {		
		useServerAdminStart();		
		MockHttpServletRequest request = new MockHttpServletRequest(new MockServletContext(""), "POST", "/ShutPage.jsp");	
		MockHttpServletResponse response = new MockHttpServletResponse();
	    DispatcherServlet dispatcherServlet = new DispatcherServlet() ; 
	    dispatcherServlet.setContextConfigLocation("file:src/test/resources/spring-servlet.xml");  
		dispatcherServlet.init(new MockServletConfig());	  
	    dispatcherServlet.service(request, response);
	    String resp = response.getContentAsString();		
	    assertEquals("ShutDown_Completed", resp.trim());		
	}

	/**
	 * Use server admin start.
	 */
	private void useServerAdminStart() {
		ServerAdmin server = new ServerAdmin();
		server.setUpEmbeddedServer();
		server.startEmbeddedServer();
		Connection conn = null;
		conn =  ServerAdmin.callConnection(conn);
		assertNotNull(conn);			
		Admin_Servlet_Start.setServer(server);
	}

	/**
	 * Shut server.
	 */
	private void shutServer() {
		Admin_Servlet_Start.getServer().shutEmbeddedServer(Admin_Servlet_Start.getServer().getHsqlServer(), ServerAdmin.callConnection(null));
	}	
	
	
}
