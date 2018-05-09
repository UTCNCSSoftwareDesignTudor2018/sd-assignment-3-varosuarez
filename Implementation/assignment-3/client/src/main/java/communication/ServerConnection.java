package communication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import ro.tuc.dsrl.ds.handson.assig.two.rpc.Connection;
import ro.tuc.dsrl.ds.handson.assig.two.rpc.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems,
 *          http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-client
 * @Since: Sep 23, 2015
 * @Description: Assures the connection to a server and the communication with
 *               it via Messages. Implements the Connection interface, specified
 *               in the rpc package, which requires the implementation of the
 *               sendMessage() method.
 */
public class ServerConnection implements Connection {
	private static final Log LOGGER = LogFactory.getLog(ServerConnection.class);
	private Socket clientSocket;
	private ObjectOutputStream outToServer;
	private ObjectInputStream inFromServer;
	private static ServerConnection instance;
	
	private static final String TERMINATE = "terminate";

	/*
	 * The host and port of the server with which the socket communication is
	 * established is default set here (localhost, 8887). To establish
	 * connection to another location, change these values.
	 */
	private ServerConnection() throws IOException {
		clientSocket = new Socket("localhost", 8889);
		outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
		inFromServer = new ObjectInputStream(clientSocket.getInputStream());
	}

	public static ServerConnection getInstance() throws IOException {
		if (instance == null) {
			instance = new ServerConnection();
		}
		return instance;
	}

	/**
	 * Sends a message to the server, through the opened socket and receives a
	 * reply. The method waits for the server reply.
	 *
	 * @param messageToSend
	 *            Message to send to the server
	 * @return Message representing the response of the server
	 */
	public Message sendMessage(Message messageToSend) {
		Message messageReceived = null;
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(messageToSend);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Client sending request: "+ jsonString);
		try {
			outToServer.writeObject(messageToSend);
			messageReceived = (Message) inFromServer.readObject();
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.error("", e);
		}

		return messageReceived;
	}

	public void closeAll() {
		Message terminate = new Message();
		terminate.setMethodName(TERMINATE);
		Message messageReceived = sendMessage(terminate);
		if (messageReceived.getMethodName().equals(TERMINATE)) {
			try {
				// Close the input stream
				if (inFromServer != null) {
					inFromServer.close();
				}
				// Close the output stream
				if (outToServer != null) {
					outToServer.close();
				}

				// Close the socket
				if (clientSocket != null) {
					clientSocket.close();
				}
			} catch (IOException e) {
				LOGGER.error("", e);
			} finally {
				inFromServer = null;
				outToServer = null;
				clientSocket = null;
			}
		}
	}

	@Override
	public Message sendMessage(String userJson) {
		// TODO Auto-generated method stub
		return null;
	}
}