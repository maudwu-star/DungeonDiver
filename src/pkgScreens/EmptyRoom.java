package pkgScreens;

//import modules
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

//import util java files
import pkgDungeonDiver.*;

public class EmptyRoom extends JPanel {
	private static final long serialVersionUID = 1L;
	//establish player
    private JLabel lblPlayerPhoto;
    //establish map & mainframe
    private MapFrame mapFrame;
    private MainFrame mainFrame;

	//position variables
    private int playerX = 184;
    private int playerY = 11;
    
    //size variables
    private static final int playerWidth = 64;
    private static final int playerHeight = 64;
    
	/**
	 * Create the panel.
	 */
	public EmptyRoom(MainFrame mainFrame, MapFrame mapFrame) {
	    this.mapFrame = mapFrame;
	    this.mainFrame = mainFrame;
		setLayout(null);
		//set up required focus for movement
        setFocusable(true);
        requestFocusInWindow();
        
        //use key bindings for movement
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke("D"), "moveRight");
        im.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        im.put(KeyStroke.getKeyStroke("W"), "moveUp");
        im.put(KeyStroke.getKeyStroke("S"), "moveDown");

        am.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRight();
            }
        });

        am.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveLeft();
            }
        });
        
        am.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveUp();
            }
        });
        
        am.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown();
            }
        });
        
		//background image label
		ImageIcon backgroundIcon = new ImageIcon(Battle.class.getResource("/Images/GrassBG.jpeg"));		
		JLabel lblBackground = new JLabel(backgroundIcon);
		lblBackground.setBounds(0, -11, 450, 300);
		
		//player character image label
        ImageIcon playerIcon = new ImageIcon(Battle.class.getResource("/Images/StickMan.png"));
        lblPlayerPhoto = new JLabel(playerIcon);
        //change spawn location based on walk direction 
        if (pkgDungeonDiver.DungeonDiverRunner.walkDirection.equals("RIGHT")) {
			//position variables
			playerX = 10;
			playerY = 109;
        } else if (pkgDungeonDiver.DungeonDiverRunner.walkDirection.equals("LEFT")) {
			//position variables
			playerX = 376;
			playerY = 109;
        } else if (pkgDungeonDiver.DungeonDiverRunner.walkDirection.equals("DOWN")) {
			//position variables
		    playerX = 184;
		    playerY = 11;
        } else if (pkgDungeonDiver.DungeonDiverRunner.walkDirection.equals("UP")) {
			//position variables
		    playerX = 184;
		    playerY = 214;
        }
        lblPlayerPhoto.setBounds(playerX, playerY, 64, 64);
		
		//add photos
		add(lblPlayerPhoto);
		add(lblBackground);
	}
	
	//function to attempt to move on the map
	public void attemptMovement(String side) {
		//update runner walk direction
		pkgDungeonDiver.DungeonDiverRunner.walkDirection = side;
		
		//check if valid movement
		if (side.equals("RIGHT")) {
			if (DungeonDiverRunner.playerCol < DungeonDiverRunner.mapColumns - 1) { 
				//slide right
				DungeonDiverRunner.playerCol += 1;
				//play movement sfx
				MusicPlayer.playSound("door.mp3");
				//position variables
				playerX = 10;
				playerY = 109;
			}
		} else if (side.equals("LEFT")) {
			if (DungeonDiverRunner.playerCol > 0) { 
				//slide left
				DungeonDiverRunner.playerCol -= 1;
				//play movement sfx
				MusicPlayer.playSound("door.mp3");
				//position variables
				playerX = 376;
				playerY = 109;
			}
		} else if (side.equals("DOWN")) {
			if (DungeonDiverRunner.playerRow < DungeonDiverRunner.mapRows - 1) { 
				//slide down
				DungeonDiverRunner.playerRow += 1;
				//play movement sfx
				MusicPlayer.playSound("door.mp3");
				//position variables
			    playerX = 184;
			    playerY = 11;
			}
		} else if (side.equals("UP")) {
			if (DungeonDiverRunner.playerRow > 0) { 
				//slide up
				DungeonDiverRunner.playerRow -= 1;
				//play movement sfx
				MusicPlayer.playSound("door.mp3");
				//position variables
			    playerX = 184;
			    playerY = 214;
			}
		}
		
		//generate new mapframe 
		if (mapFrame != null) {
		    mapFrame.refresh();
		}
		
		//reset position
        lblPlayerPhoto.setBounds(playerX, playerY, 64, 64);
        
        //if next room contains a function, launch
        if (DungeonDiverRunner.mapFunction[DungeonDiverRunner.playerRow][DungeonDiverRunner.playerCol].equals("BATTLE")) {
        	//roomswap to battle
        	mainFrame.panelSwapper(new Battle(mainFrame, mapFrame));
        } else if (DungeonDiverRunner.mapFunction[DungeonDiverRunner.playerRow][DungeonDiverRunner.playerCol].equals("HEALING")) {
        	//roomswap to healing
        	mainFrame.panelSwapper(new Healing(mainFrame, mapFrame));
        } else if (DungeonDiverRunner.mapFunction[DungeonDiverRunner.playerRow][DungeonDiverRunner.playerCol].equals("BOSS")) {
        	//roomswap to boss
        	mainFrame.panelSwapper(new Battle(mainFrame, mapFrame));
        } else if (DungeonDiverRunner.mapFunction[DungeonDiverRunner.playerRow][DungeonDiverRunner.playerCol].equals("OCCURRENCE")) {
        	//roomswap to occurrence
        	mainFrame.panelSwapper(new Occurrence(mainFrame, mapFrame));
        } else if (DungeonDiverRunner.mapFunction[DungeonDiverRunner.playerRow][DungeonDiverRunner.playerCol].equals("VENDOR")) {
        	//roomswap to vendor
        	mainFrame.panelSwapper(new Vendor(mainFrame, mapFrame));
        }
	}
	
    private static int getPlayerSpeed() {
    	int playerSpeed = (int)(10*(Settings.walkSpeed/100.00));
    	return playerSpeed;
    };
	
	private void moveRight() {
		//establish next position
	    int nextX = playerX + getPlayerSpeed();
	    
	    //check bounds on next position
	    if (nextX + playerWidth <= getWidth()) {//not out of bounds: allow
	        playerX = nextX;
	        lblPlayerPhoto.setLocation(playerX, playerY);
	    } else {//movement attempt
	    	attemptMovement("RIGHT");
	    }
	}
	
	private void moveLeft() {
		//establish next position
	    int nextX = playerX - getPlayerSpeed();
	    
	    //check bounds on next position
	    if (nextX >= 0) {//not out of bounds: allow
	        playerX = nextX;
	        lblPlayerPhoto.setLocation(playerX, playerY);
	    }else {//movement attempt
	    	attemptMovement("LEFT");
	    }
	}
	
	private void moveUp() {
		//establish next position
		int nextY = playerY - getPlayerSpeed(); 
		
	    //check bounds on next position
		if (nextY >= 0) {
			playerY = nextY;
			lblPlayerPhoto.setLocation(playerX, playerY);
		} else {//movement attempt
	    	attemptMovement("UP");
	    }
	}
	
	private void moveDown() {
		//establish next position
		int nextY = playerY + getPlayerSpeed(); 
		
	    //check bounds on next position
		if (nextY + playerHeight <= getHeight()) {
			playerY = nextY;
			lblPlayerPhoto.setLocation(playerX, playerY);
		} else {//movement attempt
	    	attemptMovement("DOWN");
	    }
	}

}

