package insertName.flagHolder.launcher;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DisconnectFromLobbyAction extends AbstractAction implements Runnable {
	
	private Lobby lobby;
	private JFrame frame;
	
	public DisconnectFromLobbyAction(Lobby lobby, JFrame frame) {
		super("Disconnect");
		this.lobby = lobby;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		lobby.disconnect();
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void run() {
		frame.setContentPane(new JoinScreen(frame));
		frame.pack();
		frame.validate();
	}

}
