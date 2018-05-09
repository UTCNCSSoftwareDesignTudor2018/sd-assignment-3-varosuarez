package ro.tuc.dsrl.ds.handson.assig.two.rpc;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 *          Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-rpc
 * @Since: Sep 24, 2015
 * @Description:
 *  Interface specifying the connection of a client to the server. Such a connection
 *  must provide a method to send a message to the server, and retrieve the message
 *  response.
 */
public interface Connection {
    Message sendMessage(Message message);
	Message sendMessage(String userJson);
}
