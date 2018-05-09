package ass3.communication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 *          Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-server
 * @Since: Sep 24, 2015
 * @Description:
 *	Server application starting point.
 */
public class ServerStart {
	private static final Log LOGGER = LogFactory.getLog(ServerStart.class);

	private static final int PORT = 8889;

	private ServerStart() {
	}

	public static void main(String[] args) {
		try {
			new Server(PORT);
			System.out.println("The server started.");
		} catch (IOException e) {
			LOGGER.error("",e);
		}
	}
	public  void run() {
		try {
			new Server(PORT);
			System.out.println("The server started.");
		} catch (IOException e) {
			LOGGER.error("",e);
		}
	}
}
