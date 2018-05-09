package communication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.entities.Writer;
import common.repositories.IArticleDao;
import common.repositories.IWriterDao;
import ro.tuc.dsrl.ds.handson.assig.two.rpc.Naming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems,
 *          http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-client
 * @Since: Sep 24, 2015
 * @Description: Starting point of the Client application.
 */
public class ClientStart {
	private static final Log LOGGER = LogFactory.getLog(ClientStart.class);

	private ClientStart() {
	}

	public static void main(String[] args) throws IOException {
		IArticleDao aD = null;
		IWriterDao wD = null;
		try {
			//ObjectMapper mapper = new ObjectMapper();
			
			wD = Naming.lookup(IWriterDao.class,
					ServerConnection.getInstance());
			aD = Naming.lookup(IArticleDao.class,
					ServerConnection.getInstance());
			List<Writer> ws = new ArrayList<>();
			ws = wD.findAll(true);
			
			for(Writer w: ws){
				System.out.println(w.toString());
			}
			wD.deleteWriterById("a");
			wD.addWriter(new Writer("a","a","a","a","a"));
			System.out.println(wD.findByUsernameAndPassword("a", "a").toString());
			aD.updateBody("student1", "23");
			System.out.println(aD.findByTitle("student1"));
			ServerConnection.getInstance().closeAll();
		} catch (Exception e) {
			LOGGER.error("",e);
			ServerConnection.getInstance().closeAll();
		}
	}
}
