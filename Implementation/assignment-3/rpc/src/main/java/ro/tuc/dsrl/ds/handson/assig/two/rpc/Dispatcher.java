package ro.tuc.dsrl.ds.handson.assig.two.rpc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 *          Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-rpc
 * @Since: Sep 24, 2015
 * @Description:
 *	Dispatches the call received from the client. It interprets
 * the given Message, gets the correct object from the registry,
 * calls the required method of that object and then bundles and
 * returns a response Message.
 */
public class Dispatcher {
	private static final Log LOGGER = LogFactory.getLog(Dispatcher.class);

	private Registry registry;
	private static Dispatcher ourInstance = new Dispatcher();

	private Dispatcher() {
		registry = Registry.getInstance();
	}

	public static Dispatcher getInstance() {
		return ourInstance;
	}

	/**
	 * Executes the operation requested in the Message argument, by interpreting the Message:
	 * 	- gets from the registry the object from the required endpoint
	 * 	- gets the arguments of the method to be called
	 * 	- gets the name of the method to be called
	 * 	- calls the respective method, with those arguments
	 * 	- adds the return value of the method to the response Message
	 * 		OR adds an exception if the method threw an exception
	 *
	 * @param m
	 * @return
	 */
	public synchronized Message execute(Message m) {
		Message response = new Message();
		Object[] responseArgs = new Object[1];

		// Get endpoint
		Object objectEndpoint = registry.getEndpoint(m.getEndPoint());

		//get the types of the arguments for the method
		Class[] argTypes = new Class[m.getArguments().length];
		int length = m.getArguments().length;
		for (int i=0;i<length;i++) {
			argTypes[i] = m.getArguments()[i].getClass();
		}

		try {
			//get the method by method name and arguments types
			Method method = objectEndpoint.getClass().getMethod(m.getMethodName(),argTypes);

			//invoke the method, giving the arguments from the message
			responseArgs[0] = method.invoke(objectEndpoint,m.getArguments());

		} catch (NoSuchMethodException | IllegalAccessException e) {
			LOGGER.error("",e);
		} catch (InvocationTargetException e) {
			//if method threw exception, add the root exception (the cause of InvocationTargetException) to response
			responseArgs[0] = e.getCause();
		}

		// Set same endpoint for response
		response.setArguments(responseArgs);
		response.setEndPoint(m.getEndPoint());

		return response;
	}

}
