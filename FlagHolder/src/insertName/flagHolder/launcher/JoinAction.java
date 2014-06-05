package insertName.flagHolder.launcher;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JoinAction extends AbstractAction implements Runnable {

	/**
	 *
	 */
	private static final long serialVersionUID = 109804094404181681L;

	private JFrame frame;
	private JTextField ip;

	public JoinAction(JFrame frame, JTextField ipField) {
		super("Join Server");
		this.frame = frame;
		this.ip = ipField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void run() {
		try {
			InetAddress address = InetAddress.getByName(ip.getText());
			int port = 45678;
			frame.setContentPane(new Lobby(address, port, frame));
			frame.pack();
			frame.validate();
		}
		catch (IOException e1) {
			JOptionPane.showMessageDialog(frame, "Entered IP is no good!", "Bad IP", JOptionPane.ERROR_MESSAGE);
		}
	}

}
