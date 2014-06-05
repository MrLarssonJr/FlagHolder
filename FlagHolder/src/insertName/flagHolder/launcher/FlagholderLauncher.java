package insertName.flagHolder.launcher;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

		this.setContentPane(new JoinScreen(this));

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new FlagholderLauncher();
	}
}
