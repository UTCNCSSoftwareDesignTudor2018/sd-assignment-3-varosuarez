package ass3.communication;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ro.tuc.dsrl.ds.handson.assig.two.rpc.Dispatcher;
import ro.tuc.dsrl.ds.handson.assig.two.rpc.Message;
import ro.tuc.dsrl.ds.handson.assig.two.rpc.Registry;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems,
 *          http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-server
 * @Since: Sep 24, 2015
 * @Description:
 *
 */
public class Session extends Thread {
	private static final Log LOGGER = LogFactory.getLog(Session.class);

	private Socket clientSocket;
	private ObjectInputStream inFromClient;
	private ObjectOutputStream outToClient;
	
	private static final String TERMINATE = "terminate";

	public Session(Socket cSocket) {
		this.clientSocket = cSocket;
		try {
			inFromClient = new ObjectInputStream(clientSocket.getInputStream());
			outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			LOGGER.error("", e);
		}
	}

	@Override
	public void run() {
		boolean run = true;
		while (run) {
			Message messageReceived = null;
			Message messageToSend;
			// System.out.println(inFromClient.markSupported());
			try {
				messageReceived = (Message) inFromClient.readObject();
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = null;
				try {
					jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(messageReceived);
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*if(messageReceived.getMethodName().equals("addArticle")){
					sendMessageToClient(new Message("ReaderView","update",null));
				}*/
				
				
				//System.out.println("Server received message: \n"+ jsonString);
				//System.out.println(messageReceived.getMethodName());
				if (TERMINATE.equals(messageReceived.getMethodName())) {
					//System.out.println("TERMINATE");
					run = false;
					messageToSend = new Message();
					messageToSend.setMethodName(TERMINATE);
				} else {
					if ("checkendpoint".equals(messageReceived.getMethodName())) {
						// if incoming message is for a lookup (checkendpoint),
						// then
						// check if the endpoint is valid
						messageToSend = checkEndpoint(messageReceived);
					} else {
						// else, dispatch the message and execute the request
						// (Dispatcher will handle everything)
						messageToSend = Dispatcher.getInstance().execute(
								messageReceived);
					}
				}
				// send back the response to the client
				sendMessageToClient(messageToSend);
				ObjectMapper mapper1 = new ObjectMapper();
				String jsonString1 = null;
				
				try {
					jsonString1 = mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(messageToSend);
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//System.out.println("Server response message: \n"+ jsonString1);

			} catch (EOFException e) {
				run = false;
			} catch (SocketException e) {
				System.out.println("HERE");
				run = false;
				e.printStackTrace();
			} catch (IOException e) {
				LOGGER.error("", e);
				closeAll();
				break;
			} catch (ClassNotFoundException e) {
				LOGGER.error("", e);
			}
		}
		closeAll();
	}

	/**
	 * Checks if the endpoint is valid (i.e. there exists an object in the
	 * registry which has associated the given endpoint).
	 *
	 * @param messageReceived
	 *            message from client containing the endpoint
	 */
	private Message checkEndpoint(Message messageReceived) {
		Message messageToSend = new Message();
		Object[] arguments = new Object[1];
		String endpointName = messageReceived.getEndPoint();

		// check in registry if there exists an entry for endpointName
		if (Registry.getInstance().getEndpoint(endpointName) != null) {
			arguments[0] = "OK";
		} else {
			arguments[0] = "ERROR";
		}

		messageToSend.setArguments(arguments);
		messageToSend.setEndPoint(endpointName);
		messageToSend.setMethodName(messageReceived.getMethodName());

		return messageToSend;
	}

	public void sendMessageToClient(Message messageToSend) {
		try {
			outToClient.writeObject(messageToSend);
		} catch (IOException e) {
			LOGGER.error("", e);
		}
	}

	public void closeAll() {
		try {
			// Close the input stream
			if (inFromClient != null) {
				inFromClient.close();
			}
			// Close the output stream
			if (outToClient != null) {
				outToClient.close();
			}
			// Close the socket
			if (clientSocket != null) {
				clientSocket.close();
			}
		} catch (IOException e) {
			LOGGER.error("", e);
		} finally {
			inFromClient = null;
			outToClient = null;
			clientSocket = null;
		}
	}
}
