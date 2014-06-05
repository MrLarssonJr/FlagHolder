package insertName.flagHolder.launcher;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.*;

public class FlagholderLauncher extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1923526432007138716L;

	private JPanel joinPanel, lobbyPanel;

	public FlagholderLauncher() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
		//Set up frame
		this.setTitle("Flagholder Launcher");
//		this.setResizable(false);

		joinPanel = new JPanel();

		{	//Set up join panel
			joinPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

			GridBagLayout layout = new GridBagLayout();
			joinPanel.setLayout(layout);
			GridBagConstraints gbc;

			{	//Set up join section
				String tooltipText = "Enter IP to server on LAN and click on \"Join\" to join the server.";

				JLabel ipLabel = new JLabel("Server IP:");
				ipLabel.setToolTipText(tooltipText);
				gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.insets = new Insets(0, 5, 0, 0);
				gbc.anchor = GridBagConstraints.SOUTHWEST;
				joinPanel.add(ipLabel, gbc);

				JTextField ipField = new JTextField(11);
				JoinAction join = new JoinAction(this, lobbyPanel, ipField);
				ipField.setToolTipText(tooltipText);
				ipField.setAction(join);
				gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				joinPanel.add(ipField, gbc);

				JButton joinBtn = new JButton(join);
				joinBtn.setToolTipText(tooltipText);
				gbc = new GridBagConstraints();
				gbc.gridx = 1;
				gbc.gridy = 1;
				gbc.anchor = GridBagConstraints.EAST;
				joinPanel.add(joinBtn, gbc);
			}

			JSeparator sep = new JSeparator();
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridwidth = 2;
			gbc.gridy = 2;
			gbc.insets = new Insets(3, 0, 3, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			joinPanel.add(sep, gbc);


			{	//Set up start server section
				JButton startBtn = new JButton("Start New Server");
				startBtn.setToolTipText("Click to start a new server.");
				gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 3;
				gbc.gridwidth = 2;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				joinPanel.add(startBtn, gbc);
			}


		}

		this.setContentPane(joinPanel);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new FlagholderLauncher();
	}
}
