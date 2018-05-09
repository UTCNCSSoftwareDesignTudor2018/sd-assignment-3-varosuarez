package ro.tuc.dsrl.ds.handson.assig.two.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.google.gson.Gson;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 *          Distributed Systems Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-rpc
 * @Since: Sep 24, 2015
 * @Description:
 *	Provides a static method too look up for a remote object on the server.
 */
public class Naming {
	private Naming() {
	}


	/**
	 * Looks up on the server registry if there is any class published by the server
	 * which implements the given interface.
	 *
	 * If the remote class exists, it returns a Proxy of that class. The Proxy object
	 * has associated a ProxyCallHandler (see below). The Proxy object
	 * has the same interface as the remote class (same methods), but when a method
	 * is called on the proxy, the invoke() method of the ProxyCallHandler is automatically
	 * called.
	 *
	 * @param clazz Interface of which implementation is to be found on the server
	 * @param connection The connection through which to communicate with the server
	 * @return if there is such a remote object published, return a Proxy to the object
	 * 		 	else return null.
	 */
	public static <T> T lookup(Class<T> clazz, Connection connection) {
		Message messageToSend;

		//compose a Message to check if the endpoint is valid
		messageToSend = new Message();
		messageToSend.setEndPoint(clazz.getSimpleName());
		messageToSend.setMethodName("checkendpoint");
		//send the message
		Message messageReceived = connection.sendMessage(messageToSend);
		//the first position in arguments field of the Message contains the status of the endpoint verification
		Object object = messageReceived.getArguments()[0];
		if (object != null) {
			//if status is OK, i.e. endpoint is valid
			if (object instanceof String && "OK".equals(object)) {
				/*
				creates a new Proxy, which implements the clazz interface, and calls the given ProxyCallHandler
				when its methods are called
				*/
				return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new ProxyCallHandler(connection));
			}
			else if (object instanceof String && "ERROR".equals(object)) {
				System.out.println("There's no object on the provided endpoint: " + clazz.getSimpleName() + " !");
				return null;
			}
		}

		return null;
	}

	/**
	 * Class providing the invoke() method which is automatically called by any Proxy instance when
	 * its methods are called.
	 */
	private static class ProxyCallHandler implements InvocationHandler {
		private Connection connection;

		public ProxyCallHandler(Connection connection) {
			this.connection = connection;
		}

		/**
		 * Receives as arguments the instance of the proxy from where invoke() was called,
		 * the Method from the proxy where invoke() was called, and the arguments of that
		 * method.
		 *
		 * It bundles  the interface name (endpoint), the method name, the arguments in a
		 * Message and then sends the message to the server (remote invocation of the method).
		 *
		 * Returns the return value of the remotely invoked method, or throws exception if the
		 * remote method threw such an exception.
		 *
		 * @param proxy proxy from where invoke() was called
		 * @param method method from where invoke() was called
		 * @param args arguments of the method from where invoke() was called
		 * @return return value of the remotely called method
		 * @throws Throwable throws an exception, if the exception was bundled in the response message, i.e.
		 * 	the remote method threw an exception
		 */
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			Message messageToSend = new Message();

			messageToSend.setEndPoint(proxy.getClass().getInterfaces()[0].getSimpleName());
			messageToSend.setMethodName(method.getName());
			messageToSend.setArguments(args);
			
			Message messageReceived = connection.sendMessage(messageToSend);
			Object result = messageReceived.getArguments()[0];
			if (result instanceof Throwable) {
				throw (Throwable) result;
			}

			return result;
		}
	}
}
