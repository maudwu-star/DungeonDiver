package pkgScreens;

//imports
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

//module imports
import pkgDungeonDiver.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hamlet extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Hamlet(MainFrame mainFrame) {
        setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);	
		
		//character label
		JLabel lblCurrentChar = new JLabel("Current character: N/A");
		lblCurrentChar.setBounds(22, 11, 417, 14);
		add(lblCurrentChar);
		
		//selector confirm button
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
				//change current HP to selected max HP
				DungeonDiverRunner.playerChar.setCurrHP(DungeonDiverRunner.playerChar.getMaxHP());
				//collect name user would like to use 
				String heroName = DungeonDiverRunner.validateInputStr("What name would you like your hero to use?");
				//update name if one was entered
				if (!(heroName == " ")) {//a name was put in
					DungeonDiverRunner.playerChar.setName(heroName);
				}
				
				//show message before game begins
				JOptionPane.showMessageDialog(null, "Welcome to the Dungeons, " + DungeonDiverRunner.playerChar.getName() + ".");
				
				//launch map
				DungeonDiverRunner.activeMap = new MapFrame(mainFrame);
				
				//switch into battle screen 
				mainFrame.panelSwapper(new Battle(mainFrame, DungeonDiverRunner.activeMap));
			}
		});
		btnConfirm.setBounds(22, 36, 89, 23);
		add(btnConfirm);
		//disable at start
		btnConfirm.setEnabled(false);
		
		//generate 3 characters
		pkgDungeonDiver.Character char1 = new pkgDungeonDiver.Character();
		pkgDungeonDiver.Character char2 = new pkgDungeonDiver.Character();
		pkgDungeonDiver.Character char3 = new pkgDungeonDiver.Character();

		//character 1 stats
		JLabel lblGuy1 = new JLabel(generateDisplayStats(char1));
		lblGuy1.setBounds(22, 118, 89, 153);
		lblGuy1.setVerticalAlignment(SwingConstants.TOP);
		add(lblGuy1);
		
		//character 1 selector
		JButton btnGuy1 = new JButton("Guy 1");
		btnGuy1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
				//choose character
				DungeonDiverRunner.playerChar.setATK(char1.getATK());
				DungeonDiverRunner.playerChar.setMaxHP(char1.getMaxHP());
				DungeonDiverRunner.playerChar.setDEF(char1.getDEF());
				DungeonDiverRunner.playerChar.setName(char1.getName());
				//enable confirm
				btnConfirm.setEnabled(true);
				//update label
				lblCurrentChar.setText("Current character: Guy 1");
			}
		});
		btnGuy1.setBounds(22, 96, 89, 23);
		add(btnGuy1);
		
		//character 2 selector
		JButton btnGuy2 = new JButton("Guy 2");
		btnGuy2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
				//choose character
				DungeonDiverRunner.playerChar.setATK(char2.getATK());
				DungeonDiverRunner.playerChar.setMaxHP(char2.getMaxHP());
				DungeonDiverRunner.playerChar.setDEF(char2.getDEF());
				DungeonDiverRunner.playerChar.setName(char2.getName());
				//enable confirm
				btnConfirm.setEnabled(true);	
				//update label
				lblCurrentChar.setText("Current character: Guy 2");
			}
		});
		btnGuy2.setBounds(181, 96, 89, 23);
		add(btnGuy2);
		
		//character 2 stats
		JLabel lblGuy2 = new JLabel(generateDisplayStats(char2));
		lblGuy2.setVerticalAlignment(SwingConstants.TOP);
		lblGuy2.setBounds(181, 118, 89, 153);
		add(lblGuy2);
		
		//character 3 selector
		JButton btnGuy3 = new JButton("Guy 3");
		btnGuy3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
				//choose character
				DungeonDiverRunner.playerChar.setATK(char3.getATK());
				DungeonDiverRunner.playerChar.setMaxHP(char3.getMaxHP());
				DungeonDiverRunner.playerChar.setDEF(char3.getDEF());
				DungeonDiverRunner.playerChar.setName(char3.getName());
				//enable confirm
				btnConfirm.setEnabled(true);
				//update label
				lblCurrentChar.setText("Current character: Guy 3");
			}
		});
		btnGuy3.setBounds(338, 96, 89, 23);
		add(btnGuy3);
		
		//character 3 stats
		JLabel lblGuy3 = new JLabel(generateDisplayStats(char3));
		lblGuy3.setVerticalAlignment(SwingConstants.TOP);
		lblGuy3.setBounds(338, 118, 89, 153);
		add(lblGuy3);				
	}
	
	//function to display stats
	public String generateDisplayStats(pkgDungeonDiver.Character charStats) {
		String outputString = "<html>NAME: " + charStats.getName() + "<br>"
							+ "HP: " + charStats.getMaxHP() + "<br>"
							+ "ATK: " + charStats.getATK() + "<br>"
							+ "DEF: " + charStats.getDEF() + "</html>";
		return outputString;
	}
}
