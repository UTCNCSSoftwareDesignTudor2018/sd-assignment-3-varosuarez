package ass3.communication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ass3.data.repository.ArticleDao;
import ass3.data.repository.WriterDao;
import common.repositories.IWriterDao;
import ro.tuc.dsrl.ds.handson.assig.two.rpc.Registry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 *          Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-server
 * @Since: Sep 24, 2015
 * @Description:
 *	Thread which listens for incoming connections and creates a Session for each client.
 */
public class Server implements Runnable {
	private static final Log LOGGER = LogFactory.getLog(Server.class);

	private ServerSocket serverSocket;
	
	private int state;
	//List<Observer> observers = new ArrayList<>();

	/**
	 * Create a socket object from the ServerSocket to listen to and accept
	 * connections.
	 */
	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		IWriterDao wD = new WriterDao();

		Registry.getInstance().registerEndpoint("IWriterDao", wD);
		Registry.getInstance().registerEndpoint("IArticleDao", new ArticleDao((WriterDao) wD));
		new Thread(this).start();
	}

	/**
	 * Accepts connections from clients and assigns a thread to deal with the messages form and to the client
	 */
	public void run() {
		while (true) {
			try {
				synchronized (this) {
					Socket clientSocket;
					clientSocket = serverSocket.accept();
					Session cThread = new Session(clientSocket);
					cThread.start();
				}
			} catch (IOException e) {
				LOGGER.error("",e);
			}
		}
	}
	
	/*public void add(Observer o) {
        observers.add(o);
    }

    public int getState() {
        return state;
    }

    public void setState(int value) {
        this.state = value;
        execute();
    }

    private void execute() {
        for (Observer observer : observers) {
            observer.update();
        }
    }*/

}