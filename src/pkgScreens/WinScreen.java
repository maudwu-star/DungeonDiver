package pkgScreens;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import pkgDungeonDiver.*;

public class WinScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public WinScreen(MainFrame mainFrame, MapFrame mapFrame) {
		setLayout(null);
		
		JLabel lblYouWin = new JLabel("You Win!");
		lblYouWin.setBounds(10, 11, 182, 14);
		add(lblYouWin);
		
		JLabel lblFinalStats = new JLabel("Final stats:\nATK:" + DungeonDiverRunner.playerChar.getATK() +
										"\nDEF: " + DungeonDiverRunner.playerChar.getDEF() +
										"\nHP: " + DungeonDiverRunner.playerChar.getCurrHP() + "/" + DungeonDiverRunner.playerChar.getMaxHP());
		lblFinalStats.setBounds(10, 36, 134, 113);
		add(lblFinalStats);
		
		JButton btnPlayAgain = new JButton("Play Again?");
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		//reset game
        		DungeonDiverRunner.resetGame();
        		//switch into char. select screen 
				mainFrame.panelSwapper(new Hamlet(mainFrame));
			}
		});
		btnPlayAgain.setBounds(10, 160, 89, 23);
		add(btnPlayAgain);
	}
}
