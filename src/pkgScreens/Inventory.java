package pkgScreens;

//imports
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Arrays;
import pkgDungeonDiver.*;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.text.DecimalFormat;

public class Inventory extends JPanel {
	DefaultListModel<String> displayList = new DefaultListModel<>();
	ArrayList<Equipment> equipmentInList = new ArrayList<>(Arrays.asList());
	private DecimalFormat df = new DecimalFormat("0.#");
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Inventory(MainFrame mainFrame) {
		//display labels
		JLabel lblCurrent = new JLabel("Currently equiped: ");
		lblCurrent.setBounds(263, 68, 158, 14);
		add(lblCurrent);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(263, 89, 158, 14);
		add(lblName);
		
		JLabel lblHP = new JLabel("HP: ");
		lblHP.setBounds(263, 141, 158, 14);
		add(lblHP);
		
		JLabel lblDEF = new JLabel("DEF: ");
		lblDEF.setBounds(263, 125, 158, 14);
		add(lblDEF);
		
		JLabel lblATK = new JLabel("ATK: ");
		lblATK.setBounds(263, 107, 158, 14);
		add(lblATK);
		
		//display inventory 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 62, 129, 161);
		add(scrollPane);
		
		JList lstDisplayInventory = new JList(displayList);
		lstDisplayInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {//check for double-click
					int index = lstDisplayInventory.locationToIndex(e.getPoint());
					//check that the hitbox was on the list
					if (index != -1 && lstDisplayInventory.getCellBounds(index, index).contains(e.getPoint())) {
						//get selected item 
						Equipment selectedEquipment = equipmentInList.get(index);
						//check if player wants to equip the item at the selected index
						int result = JOptionPane.showConfirmDialog(null, "Would you like to equip " + selectedEquipment.name + "?"
													+ "\nStats: \nATK: " + df.format(selectedEquipment.ATKModifier)
													+ "\nDEF: " + df.format(selectedEquipment.DEFModifier) 
													+ "\nHP: " + df.format(selectedEquipment.HPModifier));
						if (result == JOptionPane.YES_OPTION) {//confirmed equip
							//update stats
							updatePlayerStats(selectedEquipment);
							//update internals & display
							if (selectedEquipment.equipPosition.equals("HELMET")) {//equipping helmet
								//equip
								DungeonDiverRunner.playerChar.currHelmet = selectedEquipment;
								//update display
								updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currHelmet);
							} else if (selectedEquipment.equipPosition.equals("BOOTS")) {//equipping boots
								//equip
								DungeonDiverRunner.playerChar.currBoots = selectedEquipment;
								//update display
								updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currBoots);
							}  else if (selectedEquipment.equipPosition.equals("CHARM")) {//equipping charm
								//equip
								DungeonDiverRunner.playerChar.currCharm = selectedEquipment;
								//update display
								updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currCharm);
							}  else if (selectedEquipment.equipPosition.equals("WEAPON")) {//equipping weapon
								//equip
								DungeonDiverRunner.playerChar.currWeapon = selectedEquipment;
								//update display
								updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currWeapon);
							}  else if (selectedEquipment.equipPosition.equals("ARMOR")) {//equipping armor
								//equip
								DungeonDiverRunner.playerChar.currArmor = selectedEquipment;
								//update display
								updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currArmor);
							} 
						}	
					}
				}
			}
		});
		scrollPane.setViewportView(lstDisplayInventory);
		
		//close inventory button
		JButton btnClose = new JButton("x");
		btnClose.setBounds(356, 11, 65, 23);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//close settings
        		mainFrame.returnToPrevFrame();
			}
		});
		setLayout(null);
		add(btnClose);
		
		JLabel lblGold = new JLabel("GOLD: " + DungeonDiverRunner.playerChar.currGold);
		lblGold.setBounds(10, 39, 411, 14);
		add(lblGold);
		
		JButton btnHelmet = new JButton("HELMET");
		btnHelmet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update label boxes
				if (DungeonDiverRunner.playerChar.currHelmet != null) {//currently has a helmet
					lblName.setText("Name: " + DungeonDiverRunner.playerChar.currHelmet.name);
					lblHP.setText("HP: " + df.format(DungeonDiverRunner.playerChar.currHelmet.HPModifier));
					lblDEF.setText("DEF: " + df.format(DungeonDiverRunner.playerChar.currHelmet.DEFModifier));
					lblATK.setText("ATK: " + df.format(DungeonDiverRunner.playerChar.currHelmet.ATKModifier));
				}
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//update display
				updateList(lstDisplayInventory, "HELMET");
				updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currHelmet);
			}
		});
		btnHelmet.setBounds(10, 64, 89, 23);
		add(btnHelmet);
		
		JButton btnWeapon = new JButton("WEAPON");
		btnWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update label boxes
				if (DungeonDiverRunner.playerChar.currWeapon != null) {//currently has a helmet
					lblName.setText("Name: " + DungeonDiverRunner.playerChar.currWeapon.name);
					lblHP.setText("HP: " + df.format(DungeonDiverRunner.playerChar.currWeapon.HPModifier));
					lblDEF.setText("DEF: " + df.format(DungeonDiverRunner.playerChar.currWeapon.DEFModifier));
					lblATK.setText("ATK: " + df.format(DungeonDiverRunner.playerChar.currWeapon.ATKModifier));
				}
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//update display
				updateList(lstDisplayInventory, "WEAPON");
				updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currWeapon);
			}
		});
		btnWeapon.setBounds(10, 98, 89, 23);
		add(btnWeapon);
		
		JButton btnArmor = new JButton("ARMOR");
		btnArmor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update label boxes
				if (DungeonDiverRunner.playerChar.currArmor != null) {//currently has a helmet
					lblName.setText("Name: " + DungeonDiverRunner.playerChar.currArmor.name);
					lblHP.setText("HP: " + df.format(DungeonDiverRunner.playerChar.currArmor.HPModifier));
					lblDEF.setText("DEF: " + df.format(DungeonDiverRunner.playerChar.currArmor.DEFModifier));
					lblATK.setText("ATK: " + df.format(DungeonDiverRunner.playerChar.currArmor.ATKModifier));
				}
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//update display
				updateList(lstDisplayInventory, "ARMOR");
				updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currArmor);
			}
		});
		btnArmor.setBounds(10, 132, 89, 23);
		add(btnArmor);
		
		JButton btnBoots = new JButton("BOOTS");
		btnBoots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update label boxes
				if (DungeonDiverRunner.playerChar.currBoots != null) {//currently has a helmet
					lblName.setText("Name: " + DungeonDiverRunner.playerChar.currBoots.name);
					lblHP.setText("HP: " + df.format(DungeonDiverRunner.playerChar.currBoots.HPModifier));
					lblDEF.setText("DEF: " + df.format(DungeonDiverRunner.playerChar.currBoots.DEFModifier));
					lblATK.setText("ATK: " + df.format(DungeonDiverRunner.playerChar.currBoots.ATKModifier));
				}
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//update display
				updateList(lstDisplayInventory, "BOOTS");
				updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currBoots);
			}
		});
		btnBoots.setBounds(10, 166, 89, 23);
		add(btnBoots);
		
		JButton btnCharm = new JButton("CHARM");
		btnCharm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update label boxes
				if (DungeonDiverRunner.playerChar.currCharm != null) {//currently has a helmet
					lblName.setText("Name: " + DungeonDiverRunner.playerChar.currCharm.name);
					lblHP.setText("HP: " + df.format(DungeonDiverRunner.playerChar.currCharm.HPModifier));
					lblDEF.setText("DEF: " + df.format(DungeonDiverRunner.playerChar.currCharm.DEFModifier));
					lblATK.setText("ATK: " + df.format(DungeonDiverRunner.playerChar.currCharm.ATKModifier));
				}
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//update display
				updateList(lstDisplayInventory, "CHARM");
				updateDisplayStats(lblName, lblATK, lblHP, lblDEF, DungeonDiverRunner.playerChar.currCharm);
			}
		});
		btnCharm.setBounds(10, 200, 89, 23);
		add(btnCharm);
		
		JButton btnShowCurrStats = new JButton("Show Current Stats");
		btnShowCurrStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//display a message with current stats
				JOptionPane.showMessageDialog(null, DungeonDiverRunner.playerChar.getName() + "'s stats: \nATK: " + df.format(DungeonDiverRunner.playerChar.getATK()) + "\nHP: " + df.format(DungeonDiverRunner.playerChar.getCurrHP()) + "/" + df.format(DungeonDiverRunner.playerChar.getMaxHP()) + "\nDEF: " + df.format(DungeonDiverRunner.playerChar.getDEF()));
			}
		});
		btnShowCurrStats.setBounds(10, 235, 239, 23);
		add(btnShowCurrStats);
	}
	
	public void updateList(JList screenDisplay, String equipmentType) {
		//clear list & arraylist model
		displayList.clear();
		equipmentInList.clear();
		//display each equipment in displayList
		for (pkgDungeonDiver.Equipment e: DungeonDiverRunner.playerChar.charInventory){ 		
			//check if equipment is correct type 
			if (e.equipPosition.equals(equipmentType)) {//correct type 
				//parse into equipment display arraylist 
				equipmentInList.add(e);
				//parse into list
				displayList.addElement(e.name);
			}
		}
		//update list
		screenDisplay.setModel(displayList);
	}
	
	public void updateDisplayStats(JLabel nameLabel, JLabel ATKLabel, JLabel HPLabel, JLabel DEFLabel, Equipment newEquip) {
		if (newEquip != null) {//has equip
			nameLabel.setText("Name: " + newEquip.name);
			ATKLabel.setText("ATK: " + df.format(newEquip.ATKModifier));
			HPLabel.setText("HP: " +df.format(newEquip.HPModifier));
			DEFLabel.setText("DEF: " + df.format(newEquip.DEFModifier));
		} else {//null -- clear text
			nameLabel.setText("Name: ");
			ATKLabel.setText("ATK: ");
			HPLabel.setText("HP: ");
			DEFLabel.setText("DEF: ");
		}
	}
	
	public void updatePlayerStats(Equipment newEquip) {
		//check of previous position has a thing -- clear stats from it if so
		if (newEquip.equipPosition.equals("HELMET") && DungeonDiverRunner.playerChar.currHelmet != null) {
			DungeonDiverRunner.playerChar.setMaxHP(DungeonDiverRunner.playerChar.getMaxHP() - DungeonDiverRunner.playerChar.currHelmet.HPModifier);
			DungeonDiverRunner.playerChar.setDEF(DungeonDiverRunner.playerChar.getDEF() - DungeonDiverRunner.playerChar.currHelmet.DEFModifier);
		} else if (newEquip.equipPosition.equals("ARMOR") && DungeonDiverRunner.playerChar.currArmor != null) {
			DungeonDiverRunner.playerChar.setMaxHP(DungeonDiverRunner.playerChar.getMaxHP() - DungeonDiverRunner.playerChar.currArmor.HPModifier);
			DungeonDiverRunner.playerChar.setDEF(DungeonDiverRunner.playerChar.getDEF() - DungeonDiverRunner.playerChar.currArmor.DEFModifier);
		} else if (newEquip.equipPosition.equals("CHARM") && DungeonDiverRunner.playerChar.currCharm != null) {
			DungeonDiverRunner.playerChar.setATK(DungeonDiverRunner.playerChar.getATK() - DungeonDiverRunner.playerChar.currCharm.ATKModifier);
		} else if (newEquip.equipPosition.equals("WEAPON") && DungeonDiverRunner.playerChar.currWeapon != null) {
			DungeonDiverRunner.playerChar.setATK(DungeonDiverRunner.playerChar.getATK() - DungeonDiverRunner.playerChar.currWeapon.ATKModifier);
		} else if (newEquip.equipPosition.equals("BOOTS") && DungeonDiverRunner.playerChar.currBoots != null) {
			DungeonDiverRunner.playerChar.setMaxHP(DungeonDiverRunner.playerChar.getMaxHP() - DungeonDiverRunner.playerChar.currBoots.HPModifier);
			DungeonDiverRunner.playerChar.setDEF(DungeonDiverRunner.playerChar.getDEF() - DungeonDiverRunner.playerChar.currBoots.DEFModifier);
		}
		
		//parse in new stats
		DungeonDiverRunner.playerChar.setMaxHP(DungeonDiverRunner.playerChar.getMaxHP() + newEquip.HPModifier);
		DungeonDiverRunner.playerChar.setATK(DungeonDiverRunner.playerChar.getATK() + newEquip.ATKModifier);
		DungeonDiverRunner.playerChar.setDEF(DungeonDiverRunner.playerChar.getDEF() + newEquip.DEFModifier);
		
		//if current HP is now over max, cut it down
		if (DungeonDiverRunner.playerChar.getCurrHP() > DungeonDiverRunner.playerChar.getMaxHP()) {
			//set current to max
			DungeonDiverRunner.playerChar.setCurrHP(DungeonDiverRunner.playerChar.getMaxHP());
		}
	}
}
