package pkgScreens;

//imports
import javax.swing.JPanel;

//import files
import pkgDungeonDiver.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.SwingConstants;

public class Healing extends JPanel {
	//boolean to control if leaving on next button press
	private boolean leavingFrame = false;
	private MapFrame mapFrame;
	private DecimalFormat df = new DecimalFormat("0.#");
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Healing(MainFrame mainFrame, MapFrame mapFrame) {
		this.mapFrame = mapFrame;
		setLayout(null);
		
		//healer photo label
		ImageIcon healerIcon = new ImageIcon(Battle.class.getResource("/Images/Healer.png"));
		JLabel lblHealer = new JLabel(healerIcon);
		lblHealer.setBounds(90, 10, 168, 168);
		lblHealer.setHorizontalAlignment(JLabel.CENTER);
		lblHealer.setVerticalAlignment(JLabel.CENTER);
		add(lblHealer);
		
		//announcements label
		JLabel lblAnnouncements = new JLabel("You have encountered a Healer");
		lblAnnouncements.setOpaque(true);
		lblAnnouncements.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnouncements.setBackground(Color.WHITE);
		lblAnnouncements.setBounds(45, 189, 357, 64);
		add(lblAnnouncements);
		
		//heal button
		JButton btnHeal = new JButton("Heal");
		btnHeal.setEnabled(false);
		btnHeal.setBounds(313, 10, 89, 23);
		add(btnHeal);
		
		//leave button
		JButton btnLeave = new JButton("Leave");
		btnLeave.setEnabled(false);
		btnLeave.setBounds(313, 41, 89, 23);
		add(btnLeave);		
		
		//next button
		JButton btnNext = new JButton("Next");
		btnNext.setEnabled(true);
		btnNext.setBounds(313, 158, 89, 23);
		add(btnNext);
		
		//button functions
		//heal
		btnHeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
				//randomize heal
				double healNum = (Math.random()*15) + 5;
				//heal user 
				pkgDungeonDiver.DungeonDiverRunner.playerChar.setCurrHP(pkgDungeonDiver.DungeonDiverRunner.playerChar.getCurrHP() + healNum);
				//take down heal if overhealed
				if (pkgDungeonDiver.DungeonDiverRunner.playerChar.getCurrHP() > pkgDungeonDiver.DungeonDiverRunner.playerChar.getMaxHP()) {
					//set hp to max 
					pkgDungeonDiver.DungeonDiverRunner.playerChar.setCurrHP(pkgDungeonDiver.DungeonDiverRunner.playerChar.getMaxHP());
				}
				//update announcement
				updateAnnouncement(lblAnnouncements, "You have been healed for " + df.format(healNum) + "HP. Your HP is now " + df.format(pkgDungeonDiver.DungeonDiverRunner.playerChar.getCurrHP()) + "/" + df.format(pkgDungeonDiver.DungeonDiverRunner.playerChar.getMaxHP()));
				//set leaving frame to be true
				leavingFrame = true;
				//designate current function to be used
				DungeonDiverRunner.mapFunction[DungeonDiverRunner.playerRow][DungeonDiverRunner.playerCol] = "VOID";
				//disable functions, enable next
				btnHeal.setEnabled(false);
				btnLeave.setEnabled(false);
				btnNext.setEnabled(true);
			}
		});
		
		//leave
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
				//update announcement
				updateAnnouncement(lblAnnouncements, "You have left.");
				//set leaving frame to be true
				leavingFrame = true;
				//disable functions, enable next
				btnHeal.setEnabled(false);
				btnLeave.setEnabled(false);
				btnNext.setEnabled(true);
			}
		});
		
		//next
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
				//disable self
				btnNext.setEnabled(false);
				//go back to empty room if leaving
				if (leavingFrame) {//leaving
				//switch to scrolling screen
				mainFrame.panelSwapper(new EmptyRoom(mainFrame, mapFrame));
				} else {//enable other buttons if not leaving
					btnHeal.setEnabled(true);
					btnLeave.setEnabled(true);
				}
			}
		});
		
	}
	
	//function to update the announcements
	public void updateAnnouncement(JLabel lblAnnouncements, String text) {
		lblAnnouncements.setText(text);
	}
}
