package pkgScreens;

//module inputs
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

//data inputs
import pkgDungeonDiver.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SettingsScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	
    public SettingsScreen(MainFrame mainFrame) {
    	//create panel
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null); 
        //label for panel title
        JLabel lblTitle = new JLabel("Settings");
        lblTitle.setBounds(10, 0, 200, 30);
        add(lblTitle);
        
        //label for volume title
        JLabel lblVolume = new JLabel("Volume: " + Settings.volume + "%");
        lblVolume.setBounds(10, 41, 200, 30);
        add(lblVolume);

        //label for volume slider
        JSlider sldVolume = new JSlider(0, 100);
        //set slider to whatever current volume is 
        sldVolume.setValue(Settings.volume);
        sldVolume.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		//update variable
        	    Settings.volume = sldVolume.getValue();
        	    //update related label
        	    lblVolume.setText("Volume: " + Settings.volume + "%");
        	}
        });
        sldVolume.setBounds(10, 60, 200, 26);
        add(sldVolume);
        
        //label for walkspeed
        JLabel lblWalkSpeed = new JLabel("Walk Speed: " + Settings.walkSpeed + "%");
        lblWalkSpeed.setBounds(10, 87, 200, 30);
        add(lblWalkSpeed);

        //slider for walk speed
        JSlider sldWalkSpeed = new JSlider(0, 100);
        sldWalkSpeed.setValue(Settings.walkSpeed);
        sldWalkSpeed.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		if (sldWalkSpeed.getValue() <= 10) {//stop input
    				//move slider to minimum 
    				sldWalkSpeed.setValue(11);
        		} else {
            		//update variable
            	    Settings.walkSpeed = sldWalkSpeed.getValue();
            	    //update related label
            	    lblWalkSpeed.setText("Walk Speed: " + Settings.walkSpeed + "%");	
        		}
        	}
        });
        sldWalkSpeed.setBounds(10, 106, 200, 26);
        add(sldWalkSpeed);
        
        //button to exit settings
        JButton btnClose = new JButton("x");
        //button press
        btnClose.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//close settings
        		mainFrame.returnToPrevFrame();
        	}
        });
        btnClose.setBounds(361, 4, 65, 23);
        add(btnClose);
        
    }
}
