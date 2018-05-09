package ro.tuc.dsrl.ds.handson.assig.two.rpc;

import java.io.Serializable;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 *          Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-rpc
 * @Since: Sep 24, 2015
 * @Description:
 *	Represents the object of communication between the client and the server.
 * It contains all the necessary fields for communication.
 *
 * For example, when the client sends the message to the server, the message contains:
 * 	- the endpoint (in the Registry, which is associated to the remote object)
 * 	- the name of the method to be called
 * 	- the arguments of the method, in order
 *
 * When the server replies, it adds the result (return value of the method, or a status
 * message, or an exception) in the arguments array, on the first position.
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private String endPoint;
	private String methodName;
	private Object[] arguments;

	public Message() {
	}

	public Message(String endPoint, String methodName, Object[] arguments) {
		this.endPoint = endPoint;
		this.methodName = methodName;
		this.arguments = arguments;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		return "Message{" + "endPoint='" + endPoint + '\'' + ", methodName='" + methodName + '\'' + ", arguments="
				+ arguments + '}';
	}
}
