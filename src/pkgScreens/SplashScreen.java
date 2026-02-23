package pkgScreens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import pkgDungeonDiver.MusicPlayer;

public class SplashScreen extends JPanel {
    private static final long serialVersionUID = 1L;

    public SplashScreen(MainFrame mainFrame) {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        
        //title label
        JLabel lblTitle = new JLabel("Dungeon Diver");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(118, 11, 200, 30);
        add(lblTitle);
        
        //start button
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//sound for button pressed
        		pkgDungeonDiver.MusicPlayer.playSound("ButtonClick.mp3");
        		//swap to launch screen (hamlet)
        		mainFrame.panelSwapper(new Hamlet(mainFrame));
        	}
        });
        btnStart.setBounds(176, 106, 89, 23);
        add(btnStart);
    }
}
