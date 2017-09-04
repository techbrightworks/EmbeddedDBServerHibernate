package org.srinivas.siteworks.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static org.hsqldb.ServerConstants.*;
import org.hsqldb.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerAdmin {

	private Server hsqlServer;
	private static final Logger logger = LoggerFactory.getLogger(ServerAdmin.class);

	/**
	 * Sets the up embedded server.
	 */
	public void setUpEmbeddedServer() {
		setHsqlServer(new Server());
		getHsqlServer().setLogWriter(null);
		getHsqlServer().setSilent(true);
		getHsqlServer().setDatabaseName(0, "embeddeddb");
		getHsqlServer().setDatabasePath(0, "file:data/embeddeddb;ifexists=false;shutdown=false");
	}

	/**
	 * Start embedded server.
	 */
	public void startEmbeddedServer() {
		logger.info("Starting Embedded Server");
		if (getHsqlServer().getState() == SERVER_STATE_SHUTDOWN) {
			getHsqlServer().start();
		}
		Connection conn = callConnection(null, "SA", "");
		logger.info("Altering to add new User and user rights");
		alterCreateUser(conn);
		alterUserRights(conn);
	}

	/**
	 * Shut embedded server. 
	 * @param conn
	 */
	public void shutEmbeddedServer(Connection conn) {
		logger.info("Shutting down...");
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("SHUTDOWN COMPACT");
			statement.close();
			logger.info("Shut Down Completed");
		} catch (SQLException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	/**
	 * Shut embedded server. 
	 * @param server
	 * @param conn
	 * @return the connection
	 */
	public Connection shutEmbeddedServer(Server server, Connection conn) {
		logger.info("Shutting down...");
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("SHUTDOWN COMPACT");
			statement.close();
			conn.close();
			conn = null;
			logger.info("Shut Down Completed");
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				logger.info(e1.getMessage());
				e1.printStackTrace();
			}
			conn = null;
			logger.info(e.getMessage());
			e.printStackTrace();
		} finally {
			server.stop();
			server.shutdown();
		}

		return conn;
	}

	/**
	 * Gets the hsql server. 
	 * @return the hsql server
	 */
	public Server getHsqlServer() {
		return hsqlServer;
	}

	/**
	 * Sets the hsql server. 
	 * @param hsqlServer the new hsql server
	 */
	public void setHsqlServer(Server hsqlServer) {
		this.hsqlServer = hsqlServer;
	}

	/**
	 * Call connection.
	 * @param conn
	 * @param dbuser
	 * @param dbpassword
	 * @return the connection
	 */
	public static Connection callConnection(Connection conn, String dbuser, String dbpassword) {
		String dburl = "jdbc:hsqldb:hsql://localhost/data/embeddeddb;hsqldb.write_delay=false;shutdown=false;close_result=true;";
		logger.info("Establishing connection" + dburl);
		try {
			loadDriver();
			conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
			logger.info("connection established and connection closed is " + conn.isClosed());
		} catch (SQLException e1) {
			logger.info(e1.getMessage());
			e1.printStackTrace();
		}
		return conn;
	}

	/**
	 * Call connection. 
	 * @param conn
	 * @return the connection
	 */
	public static Connection callConnection(Connection conn) {
		conn = callConnection(conn, "techbrightworks", "password");
		return conn;
	}

	/**
	 * Simple connection. 
	 * @return the connection
	 */
	public static Connection simpleConnection() {
		String dburl = "jdbc:hsqldb:hsql://localhost/data/embeddeddb;";

		logger.info("Connecting: " + dburl);
		Connection conn = null;
		try {
			loadDriver();
			conn = DriverManager.getConnection(dburl, "techbrightworks", "password");
		} catch (SQLException e1) {
			logger.info("Error:" + e1.getMessage());
		}
		return conn;
	}

	/**
	 * Checks if is database connection active. 
	 * @return true, if is database connection active
	 */
	public static boolean isDatabaseConnectionActive() {
		boolean databaseConnectionActive = false;
		Connection conn = simpleConnection();

		if (null != conn) {
			databaseConnectionActive = true;
			try {
				conn.close();
			} catch (SQLException e) {
				logger.info("Error:" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			databaseConnectionActive = false;
		}
		return databaseConnectionActive;
	}

	/**
	 * Alter create user. 
	 * @param conn
	 */
	private void alterCreateUser(Connection conn) {

		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("CREATE USER 'techbrightworks' PASSWORD 'password'");
			statement.close();
			conn = null;
			logger.info("Alter done to create a new user");
		} catch (SQLException e) {
			conn = null;
			logger.info(e.getMessage());

		}
	}

	/**
	 * Alter user rights. 
	 * @param conn
	 */
	private void alterUserRights(Connection conn) {
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("GRANT DBA TO TECHBRIGHTWORKS");
			statement.close();
			conn = null;
			logger.info("Alter rights for new user");
		} catch (SQLException e) {
			conn = null;
			logger.info(e.getMessage());

		}

	}

	/**
	 * Load driver.
	 */
	private static void loadDriver() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {

			logger.info(e.getMessage());
			e.printStackTrace();

		}
	}

}
