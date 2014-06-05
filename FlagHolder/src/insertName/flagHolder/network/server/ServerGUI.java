package insertName.flagHolder.network.server;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import simpleEngine.log.NewLogEntryEvent;
import simpleEngine.log.NewLogEntryEventListener;
import simpleEngine.network.Server;

public class ServerGUI extends JFrame implements NewLogEntryEventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4709710626896530507L;
	private Server server;
	private JTextArea log;
	
	public ServerGUI() throws IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
		//Set up frame
		this.setTitle("Flagholder Server");
		
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		GridBagConstraints gbc;
		
		log = new JTextArea();
		log.setEditable(false);
		log.setToolTipText("This servers log");
		
		JScrollPane scpne = new JScrollPane(log);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		panel.add(scpne, gbc);
		
		this.setContentPane(panel);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		server = new Server(45678);
	}

	@Override
	public void newLogEntryEvent(NewLogEntryEvent e) {
		log.append(e.toString() + "\n");
	}
	
	public static void main(String[] args) throws IOException {
		new ServerGUI();
	}

}
