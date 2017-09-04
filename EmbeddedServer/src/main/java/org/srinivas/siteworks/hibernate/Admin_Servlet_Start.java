package org.srinivas.siteworks.hibernate;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Admin_Servlet_Start extends HttpServlet {
	
	private static final long serialVersionUID = -1550041299484535649L;	
	private static ServerAdmin server;	
	private static final Logger logger = LoggerFactory.getLogger(Admin_Servlet_Start.class);



	public Admin_Servlet_Start() {
		super();
	}


	/**
	 * Gets ServerAdmin server	 
	 * @return 
	 */
	public static ServerAdmin getServer() {
		return server;
	}

	/** 
	 *Sets ServerAdmin server	 
	 *@param server 
	 */
	public static void setServer(ServerAdmin server) {
		Admin_Servlet_Start.server = server;
	}


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		if (!ServerAdmin.isDatabaseConnectionActive()) {
			Admin_Servlet_Start.setServer(new ServerAdmin());
			Admin_Servlet_Start.getServer().setUpEmbeddedServer();
			Admin_Servlet_Start.getServer().startEmbeddedServer();
			conn = ServerAdmin.callConnection(conn);
			response.getWriter().print("Server_Started");

		} else {
			logger.info("server already started");
			response.getWriter().print("server already started");
		}
	}


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// No Get Requests

	}

}
