package insertName.flagHolder.launcher;

import java.awt.event.*;
import java.net.*;

import javax.swing.*;

public class JoinAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 109804094404181681L;

	private JFrame frame;
	private JPanel lobby;
	private JTextField ip;

	public JoinAction(JFrame frame, JPanel lobby, JTextField ipField) {
		super("Join Server");
		this.frame = frame;
		this.lobby = lobby;
		this.ip = ipField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			InetAddress address = InetAddress.getByName(ip.getText());
			int port = 45678;
			frame.removeAll();
			frame.add(lobby);
			frame.pack();
		}
		catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(frame, "Entered IP is no good!", "Bad IP", JOptionPane.ERROR_MESSAGE);
		}
	}

}
