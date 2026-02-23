package pkgScreens;

//imports
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.text.DecimalFormat;
import javax.swing.Timer;

//module imports 
import pkgDungeonDiver.*;

public class Battle extends JPanel {
	private static final long serialVersionUID = 1L;
	//variable to control turn
	private String currTurn = "NA";
	private DecimalFormat df = new DecimalFormat("0.#");
	//initiate current enemy
	static Enemy currEnemy;

	/**
	 * Create the panel.
	 */
	public Battle(MainFrame mainFrame, MapFrame mapFrame) {
		setLayout(null);
		
		//player character image label
		ImageIcon playerIcon = new ImageIcon(Battle.class.getResource("/Images/StickMan.png"));
		JLabel lblPlayerPhoto = new JLabel(playerIcon);
		lblPlayerPhoto.setBounds(46, 185, 64, 64);
		add(lblPlayerPhoto);
		
		//gleam current location info
		String RoomType = pkgDungeonDiver.DungeonDiverRunner.mapFunction[pkgDungeonDiver.DungeonDiverRunner.playerRow][pkgDungeonDiver.DungeonDiverRunner.playerCol];

		//assign enemy type
		if (RoomType.equals("BOSS")) {//boss room
			currEnemy = new Enemy("Boss");
		} else {//not a boss room -- randomize common enemy
			//loop until currEnemy is decided and NOT the boss
			do {
				//select a random name
				int index = (int)(Math.random()*pkgDungeonDiver.DungeonDiverRunner.EnemyTypes.size());
				String randEnemy = pkgDungeonDiver.DungeonDiverRunner.EnemyTypes.get(index);
				if (!randEnemy.equals("Boss")) {
					currEnemy = new Enemy(pkgDungeonDiver.DungeonDiverRunner.EnemyTypes.get(index));	
				}
			} while (currEnemy == null);
		}
		
		//enemy image label
		JLabel lblEnemyPhoto = new JLabel(new ImageIcon(Battle.class.getResource(currEnemy.getIMG())));
		lblEnemyPhoto.setBounds(316, 185, 64, 64);
		add(lblEnemyPhoto);
		
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
		btnNext.setBounds(316, 126, 89, 23);
		add(btnNext);
		//keep enabled on launch
		btnNext.setEnabled(true);
		
		//announcements label
		JLabel lblAnnouncements = new JLabel("You have encountered a " + currEnemy.getName());
		lblAnnouncements.setOpaque(true);
		lblAnnouncements.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnouncements.setBackground(Color.WHITE);
		lblAnnouncements.setBounds(46, 56, 357, 64);
		add(lblAnnouncements);
		
		//enemy hp
		JLabel lblEnemyHP = new JLabel();
		lblEnemyHP.setOpaque(true);
		lblEnemyHP.setBackground(Color.WHITE);
		lblEnemyHP.setHorizontalAlignment(SwingConstants.CENTER);
		updateHP(currEnemy, lblEnemyHP);
		lblEnemyHP.setBounds(242, 160, 198, 14);
		add(lblEnemyHP);
		
		//retreat button
		JButton btnRetreat = new JButton("Retreat");
		btnRetreat.setBounds(351, 11, 89, 23);
		add(btnRetreat);
		//keep disabled on launch
		btnRetreat.setEnabled(false);
		
		//attack button
		JButton btnAtk = new JButton("Attack");
		btnAtk.setBounds(10, 11, 89, 23);
		add(btnAtk);
		//keep disabled on launch
		btnAtk.setEnabled(false);
		
		//background image label
		ImageIcon backgroundIcon = new ImageIcon(Battle.class.getResource("/Images/DDBackground.jpg"));		
		JLabel lblBackground = new JLabel(backgroundIcon);
		lblBackground.setBounds(0, -11, 450, 300);
		
		//next button functions
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
				//decide next unit turn
				if (currTurn.equals("PLAYER")) {
					//rotate turn
					currTurn = "ENEMY";
					//take enemy turn
					attackTurn(currEnemy, pkgDungeonDiver.DungeonDiverRunner.playerChar, lblPlayerHP, lblAnnouncements, mainFrame);
				} else if (currTurn.equals("ENEMY")) {
					//rotate turn 
					currTurn = "PLAYER";
				} else if (currTurn.equals("DONE")){
					//if enemy defeated, give money
					if (currEnemy.getCurrHP() <= 0) {
						//give money
						int lootMoney = (int)(Math.random()*10) + 5;
						//increase money
						DungeonDiverRunner.playerChar.currGold += lootMoney;
						//change announcements
						updateAnnouncement(lblAnnouncements, "You have looted " + lootMoney + " gold. You now have " + DungeonDiverRunner.playerChar.currGold + " gold.");	
					}
					//set turn to leave
					currTurn = "LEAVE";
				} else if (currTurn.equals("LEAVE")) {
					//reset enemy
					currEnemy = null;
					//switch to scrolling screen
					mainFrame.panelSwapper(new EmptyRoom(mainFrame, mapFrame));
				} else {
					//decide first move
					if (DungeonDiverRunner.playerChar.currBoots == null) {//no boots -- player first
						//randomize turns
						int randTurn = (int)(Math.random()*2);
						if (randTurn == 1) {//take enemy turn before rotating to player
							//take enemy turn
							attackTurn(currEnemy, pkgDungeonDiver.DungeonDiverRunner.playerChar, lblPlayerHP, lblAnnouncements, mainFrame);
						}
					}
					//set turn to player
					currTurn = "PLAYER";
				}
				
				//return player agency buttons if player turn 
				if (currTurn.equals("PLAYER")) {
					btnAtk.setEnabled(true);
					btnRetreat.setEnabled(true);
					//disable self until attack performed
					btnNext.setEnabled(false);
				}
			}
		});
		add(lblBackground);
		
		//retreat and attack functions
		btnRetreat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {        		
				//show attempt to run
				updateAnnouncement(lblAnnouncements, "Attempting to run...");
				
				//wait to allow attempt announcement time to show
				Timer retTimer = new Timer(1000, w -> {
					//attempt retreat
					int retAttempt = (int)(Math.random()*3) + 1;
					
					//depending on attempt success, switch turn/retreat conditions
					if (retAttempt == 1) {//retreat attempt success
						//switch turn to finish battle
						currTurn = "LEAVE";
						//show success
						updateAnnouncement(lblAnnouncements, "Attempt successful!");
					} else {//attempt failed
						//announce failed attempt
						updateAnnouncement(lblAnnouncements, "Attempt failed.");
					}
					//enable next
					btnNext.setEnabled(true);
				});
				
				//make sure timer only runs once
				retTimer.setRepeats(false);
				//launch timer 
				retTimer.start();
				
				//take away player action buttons
				btnRetreat.setEnabled(false);
				btnAtk.setEnabled(false);
				//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
			}
		});
		btnAtk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
				//play bonk sound
				pkgDungeonDiver.MusicPlayer.playSound("bonk.mp3");
				//attack the enemy using weapon & attack multiplier
				attackTurn(pkgDungeonDiver.DungeonDiverRunner.playerChar, currEnemy, lblEnemyHP, lblAnnouncements, mainFrame);
				//take away player action buttons
				btnRetreat.setEnabled(false);
				btnAtk.setEnabled(false);
				//enable next
				btnNext.setEnabled(true);
			}
		});
	}
	
	//function to update HP
	public void updateHP(pkgDungeonDiver.Unit unit, JLabel label) {
		label.setText(unit.getName() + " HP: " + df.format(unit.getCurrHP()) + "/" + df.format(unit.getMaxHP()));
	}
	
	//function to update the announcements
	public void updateAnnouncement(JLabel lblAnnouncements, String text) {
		lblAnnouncements.setText(text);
	}
	
	//function for attack (er is attacker, ee is attackee)
	public void attackTurn(pkgDungeonDiver.Unit er, pkgDungeonDiver.Unit ee, JLabel eeLabel, JLabel lblAnnoucenements, MainFrame mainFrame) {
		//calculate damage
		double dmgDone = er.getATK() * ((int)(Math.random()*10)+20)/10.0 - ee.getDEF() * ((int)(Math.random()*20))/10.0;
		//if damage negative, correct to 0 
		if (dmgDone < 0) {
			//correct damage to 0
			dmgDone = 0;
		}
		
		//update hp
		ee.setCurrHP(ee.getCurrHP() - dmgDone);
		updateHP(ee, eeLabel);
		
		//if killed, do updates
		if (ee.getCurrHP() <= 0) {
			//depending on character do stuff
			if (ee == pkgDungeonDiver.DungeonDiverRunner.playerChar) {
				//switch into battle screen 
				mainFrame.panelSwapper(new GameOver(mainFrame));
			} else {
				//update turn 
				currTurn = "DONE";
				//cleared current obstacle -- clear room
				DungeonDiverRunner.mapFunction[DungeonDiverRunner.playerRow][DungeonDiverRunner.playerCol] = "VOID";
				//update announcement
				updateAnnouncement(lblAnnoucenements, "Congrats, you have defeated " + ee.getName() + "!");
				//return
				return;
			}
		}
		
		//update announcement
		updateAnnouncement(lblAnnoucenements, er.getName() + " attacked " + ee.getName() + " for " + df.format(dmgDone) + " DMG");
	}
}
