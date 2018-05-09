package ro.tuc.dsrl.ds.handson.assig.two.rpc;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 *          Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-rpc
 * @Since: Sep 24, 2015
 * @Description:
 * 	Provides a mapping of endpoint-object. It is used by the server to specify which object
 * 	can be remotely used by a client. The client has to identify the object by the endpoint.
 */
public class Registry {
	private static Registry ourInstance = new Registry();
	private Map<String, Object> endPoints;

	private Registry() {
		endPoints = new HashMap<String, Object>();
	}

	public static Registry getInstance() {
		return ourInstance;
	}

	public void registerEndpoint(String endpointName, Object endpoint) {
		endPoints.put(endpointName, endpoint);
	}

	public Object getEndpoint(String endpointName) {
		return endPoints.get(endpointName);
	}

	public void unregisterEndpoint(String endpointName) {
		endPoints.remove(endpointName);
	}
}
