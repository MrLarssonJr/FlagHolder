package insertName.flagHolder.network.messages;

import java.io.*;
import java.util.*;

public class ConnectedClientsMessage implements Serializable {
	public final ArrayList<Long> clientIDs;

	public ConnectedClientsMessage(ArrayList<Long> clientIDs) {
		super();
		this.clientIDs = clientIDs;
	}

}
