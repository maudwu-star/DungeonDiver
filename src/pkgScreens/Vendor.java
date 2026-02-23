package pkgScreens;

//imports
import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JButton;

import pkgDungeonDiver.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Vendor extends JPanel {
	//boolean to control if leaving on next button press
	private DecimalFormat df = new DecimalFormat("0.#");
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Vendor(MainFrame mainFrame, MapFrame mapFrame) {
		setLayout(null);
		//generate 4 random items
		Equipment item1 = new Equipment();
		Equipment item2 = new Equipment();
		Equipment item3 = new Equipment();
		Equipment item4 = new Equipment();

		
		JButton btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//play button sound
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");

				//remove shop from map
				DungeonDiverRunner.mapFunction[DungeonDiverRunner.playerRow][DungeonDiverRunner.playerCol] = "VOID";

				//return to main
				mainFrame.panelSwapper(new EmptyRoom(mainFrame, mapFrame));
			}
		});
		btnLeave.setBounds(10, 11, 89, 23);
		add(btnLeave);
		
		//labels
		//gold
		JLabel lblGold = new JLabel("Gold: " + DungeonDiverRunner.playerChar.currGold);
		lblGold.setBounds(103, 15, 165, 14);
		add(lblGold);
		
		//items
		JLabel lblCost1 = new JLabel("Cost: " + item1.equipmentCost);
		lblCost1.setBounds(239, 72, 115, 14);
		add(lblCost1);
		
		JLabel lblCost2 = new JLabel("Cost: " + item2.equipmentCost);
		lblCost2.setBounds(239, 106, 115, 14);
		add(lblCost2);
		
		JLabel lblCost3 = new JLabel("Cost: " + item3.equipmentCost);
		lblCost3.setBounds(239, 140, 115, 14);
		add(lblCost3);
		
		JLabel lblCost4 = new JLabel("Cost: " + item4.equipmentCost);
		lblCost4.setBounds(239, 174, 115, 14);
		add(lblCost4);
		
		//item purchase buttons
		JButton btnItem1 = new JButton(item1.name);
		btnItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//play button sound
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//attempt purchase
        		attemptPurchase(item1, lblGold, lblCost1, btnItem1);
			}
		});
		btnItem1.setBounds(10, 68, 219, 23);
		add(btnItem1);
		
		
		JButton btnItem2 = new JButton(item2.name);
		btnItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//play button sound
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//attempt purchase
        		attemptPurchase(item2, lblGold, lblCost2, btnItem2);
			}
		});
		btnItem2.setBounds(10, 102, 219, 23);
		add(btnItem2);
		
		JButton btnItem3 = new JButton(item3.name);
		btnItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//play button sound
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//attempt purchase
        		attemptPurchase(item3, lblGold, lblCost3, btnItem3);
			}
		});
		btnItem3.setBounds(10, 136, 219, 23);
		add(btnItem3);
		
		JButton btnItem4 = new JButton(item4.name);
		btnItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//play button sound
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//attempt purchase
        		attemptPurchase(item4, lblGold, lblCost4, btnItem4);
			}
		});
		btnItem4.setBounds(10, 170, 219, 23);
		add(btnItem4);
	}

	//functions
	//function for attempted purchase
	private void attemptPurchase(Equipment attemptEquipment, JLabel goldLabel, JLabel itemLabel, JButton itemButton) {
		//check if the player has enough money
		if (DungeonDiverRunner.playerChar.currGold < attemptEquipment.equipmentCost) {//can't afford
			//show error message
			JOptionPane.showMessageDialog(null, DungeonDiverRunner.playerChar.getName() + " does not have enough gold to purchase this item!");
			return;
		}
		
		//ask if the user wants to purchase
		int result = JOptionPane.showConfirmDialog(null, "Would you like to purchase " + attemptEquipment.name + "?" +
												"\nStats:\nATK: " + df.format(attemptEquipment.ATKModifier) +
												"\nDEF: " + df.format(attemptEquipment.DEFModifier) +
												"\nHP: " + df.format(attemptEquipment.HPModifier));
		if (result == JOptionPane.YES_OPTION) {//make purchase
			//deduct gold
			DungeonDiverRunner.playerChar.currGold -= attemptEquipment.equipmentCost;
			//append to inventory
			DungeonDiverRunner.addEquipment(attemptEquipment);
			//update form
			goldLabel.setText("Gold: " + DungeonDiverRunner.playerChar.currGold);
			itemLabel.setText("SOLD OUT");
			itemButton.setEnabled(false);
		}
	}
}