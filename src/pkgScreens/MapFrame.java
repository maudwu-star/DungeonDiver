package pkgScreens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pkgDungeonDiver.*;

public class MapFrame extends JFrame {	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable mapGrid;

	//set up table
	private DefaultTableModel gridSize = new DefaultTableModel(pkgDungeonDiver.DungeonDiverRunner.mapRows, pkgDungeonDiver.DungeonDiverRunner.mapColumns);

	/**
	 * Create the frame.
	 */
	public MapFrame(JFrame mainFrame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//set up table
		mapGrid = new JTable(gridSize);
		mapGrid.setBounds(10, 11, 239, 543);
		contentPane.add(mapGrid);
        setTitle("Dungeon Map");
        setLocation(mainFrame.getX() + mainFrame.getWidth() + 10, mainFrame.getY());
        setVisible(true);
        //disable editing
        mapGrid.setEnabled(false);
        
        //update map
        updateMap();
	}
	
	public void updateMap() {
        //loop to update the map
        for (int m = 0; m < pkgDungeonDiver.DungeonDiverRunner.mapRows; m++) {
        	for (int n = 0; n < pkgDungeonDiver.DungeonDiverRunner.mapColumns; n++) {
        		//get state from runner
        		String RoomType = pkgDungeonDiver.DungeonDiverRunner.mapFunction[m][n];
        		String displayText = null;
        		
        		//update display text based on type 
        		if (RoomType == "BATTLE") {
        			displayText = "⚔️";
        		} else if (RoomType == "OCCURRENCE") {
        			displayText = "🎲";
        		} else if (RoomType == "HEALING") {
        			displayText = "💊";
        		} else if (RoomType == "BOSS") {
        			displayText = "👑";
        		} else if (RoomType == "VENDOR") {
        			displayText = "💰";
        		}
        		
        		//update current player frame
        		if (m == pkgDungeonDiver.DungeonDiverRunner.playerRow && n == pkgDungeonDiver.DungeonDiverRunner.playerCol) {
        			displayText = "👤";
        		}
        		
        		//update display
        		mapGrid.setValueAt(displayText, m, n);
        	}
        }
	}
	
	public void refresh() {
		updateMap();
	    mapGrid.revalidate();
	    mapGrid.repaint();
	}
}