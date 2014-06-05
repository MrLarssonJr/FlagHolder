package insertName.flagHolder.launcher;

import insertName.flagHolder.network.server.ServerGUI;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class StartServerAction extends AbstractAction implements Runnable {
	
	private JFrame frame;
	
	public StartServerAction(JFrame frame) {
		super("Start New Server");
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void run() {
		try {
			new ServerGUI();
			Thread.sleep(20);
			InetAddress address = InetAddress.getByName("localhost");
			int port = 45678;
			frame.setContentPane(new Lobby(address, port, frame));
			frame.pack();
			frame.validate();
		}
		catch (IOException | InterruptedException e1) {
			JOptionPane.showMessageDialog(frame, "Can't start server", "IO Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
