package org.srinivas.siteworks;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.srinivas.siteworks.hibernate.ServerAdmin;
import static org.hsqldb.ServerConstants.*;



public class ServerAdminTest {


	private Connection conn;
	private ServerAdmin server;

	
	@Before
	public void setUp() {
		conn = null;
		server = new ServerAdmin();
		server.setUpEmbeddedServer();
	}

	/**
	 * Shut database.
	 */
	public void shutDatabase() {
		server.shutEmbeddedServer(server.getHsqlServer(), conn);

	}

	
	/**
	 * Test start database.
	 */
	@Test
	public void testStartDatabase() {
		server.startEmbeddedServer();
		conn = ServerAdmin.callConnection(conn);
		assertNotNull(conn);
		shutDatabase();
	}

	
	/**
	 * Test shut database. 
	 * @throws SQLException
	 */
	@Test
	public void testShutDatabase() throws SQLException {
		server.startEmbeddedServer();
		Connection conn = ServerAdmin.callConnection(null);
		conn = server.shutEmbeddedServer(server.getHsqlServer(), conn);
		assertEquals(server.getHsqlServer().getState(), SERVER_STATE_SHUTDOWN);
		assertNull(conn);
	}

}
