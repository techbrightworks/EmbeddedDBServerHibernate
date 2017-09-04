package org.srinivas.siteworks.hibernate;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Admin_Servlet_ShutDown extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(Admin_Servlet_ShutDown.class);

	private static final long serialVersionUID = 1L;

	public Admin_Servlet_ShutDown() {

		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServerAdmin.isDatabaseConnectionActive()) {
			Connection conn = ServerAdmin.simpleConnection();
			if (conn != null) {
				conn = Admin_Servlet_Start.getServer().shutEmbeddedServer(Admin_Servlet_Start.getServer().getHsqlServer(), conn);
				response.getWriter().print("ShutDown_Completed");
			} else {
				logger.info("The DatabaseActive but Connection is null");
				response.getWriter().print("The DatabaseActive but Connection is null");
			}
		} else {
			logger.info("The server is already closed");
			response.getWriter().print("The server is already closed");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// No Get Requests
	}

}
