package insertName.flagHolder.launcher;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JoinScreen extends JPanel {
	public JoinScreen(JFrame frame) {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
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
			this.add(ipLabel, gbc);

			JTextField ipField = new JTextField(11);
			JoinAction join = new JoinAction(frame, ipField);
			ipField.setToolTipText(tooltipText);
			ipField.setAction(join);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			this.add(ipField, gbc);

			JButton joinBtn = new JButton(join);
			joinBtn.setToolTipText(tooltipText);
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.anchor = GridBagConstraints.EAST;
			this.add(joinBtn, gbc);
		}

		JSeparator sep = new JSeparator();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(3, 0, 3, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(sep, gbc);


		{	//Set up start server section
			JButton startBtn = new JButton("Start New Server");
			startBtn.setToolTipText("Click to start a new server.");
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			this.add(startBtn, gbc);
		}
	}
}
