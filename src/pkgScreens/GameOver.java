package pkgScreens;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import pkgDungeonDiver.*;

public class GameOver extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public GameOver(MainFrame mainFrame) {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        
        //retry button
        JButton btnRetry = new JButton("Retry?");
        btnRetry.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//reset game
        		DungeonDiverRunner.resetGame();
        		//switch into char. select screen 
				mainFrame.panelSwapper(new Hamlet(mainFrame));
        	}
        });
        btnRetry.setBounds(143, 11, 170, 23);
        add(btnRetry);
	}
}
