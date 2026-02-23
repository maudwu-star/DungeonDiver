package pkgScreens;

//import modules
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.util.Arrays;
import pkgDungeonDiver.*;

public class Occurrence extends JPanel {
	//script control variable
	int currScriptIndex = 0;
	private static final long serialVersionUID = 1L;
	private DecimalFormat df = new DecimalFormat("0.#");

	/**
	 * Create the panel.
	 */
	public Occurrence(MainFrame mainFrame, MapFrame mapFrame) {
		//variable to control turn
		setLayout(null);
		//randomize an occurrence on launch
		OccurrenceDatabase currOccurrence = new OccurrenceDatabase();
		
		//player character image label
		ImageIcon playerIcon = new ImageIcon(Occurrence.class.getResource("/Images/StickMan.png"));
		JLabel lblPlayerPhoto = new JLabel(playerIcon);
		lblPlayerPhoto.setBounds(46, 185, 64, 64);
		add(lblPlayerPhoto);
		
		//player hp
		JLabel lblPlayerHP = new JLabel();
		lblPlayerHP.setOpaque(true);
		lblPlayerHP.setBackground(Color.WHITE);
		lblPlayerHP.setHorizontalAlignment(SwingConstants.CENTER);
		updateHP(pkgDungeonDiver.DungeonDiverRunner.playerChar, lblPlayerHP);
		lblPlayerHP.setBounds(10, 160, 141, 14);
		add(lblPlayerHP);
		
		//next button
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(303, 97, 89, 23);
		add(btnNext);
		//keep enabled on launch
		btnNext.setEnabled(true);
		
		//announcements label
		JLabel lblAnnouncements = new JLabel(currOccurrence.occurrenceScript.get(currScriptIndex));
		lblAnnouncements.setOpaque(true);
		lblAnnouncements.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnouncements.setBackground(Color.WHITE);
		lblAnnouncements.setBounds(35, 22, 357, 64);
		add(lblAnnouncements);
		
		//background image label
		ImageIcon backgroundIcon = new ImageIcon(Occurrence.class.getResource("/Images/OccurrenceBG.jpg"));		
		JLabel lblBackground = new JLabel(backgroundIcon);
		lblBackground.setBounds(0, -11, 450, 300);
		
		//next button functions
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//update to next script state
        		try {
        			//move to next line
        			currScriptIndex += 1;
        			//load in line
        			String nextLine = currOccurrence.occurrenceScript.get(currScriptIndex);
        			//display line
        			updateAnnouncement(lblAnnouncements, nextLine);
        		} catch (IndexOutOfBoundsException r) {
        			//leave
    				DungeonDiverRunner.mapFunction[DungeonDiverRunner.playerRow][DungeonDiverRunner.playerCol] = "VOID";
    				mainFrame.panelSwapper(new EmptyRoom(mainFrame, mapFrame));
        		}
			};
		});
		
		//add background to back
		add(lblBackground);
	}
	
	//function to update HP
	public void updateHP(pkgDungeonDiver.Unit unit, JLabel label) {
		label.setText(unit.getName() + " HP: " + df.format(unit.getCurrHP()) + "/" + df.format(unit.getMaxHP()));
	}
	
	//function to update the announcements
	public void updateAnnouncement(JLabel lblAnnouncements, String text) {
		lblAnnouncements.setText(text);
	}
}
