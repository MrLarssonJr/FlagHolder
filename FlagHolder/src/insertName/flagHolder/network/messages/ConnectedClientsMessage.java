package insertName.flagHolder.network.messages;

import java.io.*;
import java.util.*;

public class ConnectedClientsMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3722300212084514935L;
	public final ArrayList<String> clients;

	public ConnectedClientsMessage(ArrayList<String> clients) {
		super();
		this.clients = clients;
	}

}
