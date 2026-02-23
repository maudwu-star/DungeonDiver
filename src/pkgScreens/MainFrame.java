package pkgScreens;

//imports
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
	public ArrayList<JPanel> panelTracker = new ArrayList<>(Arrays.asList(null, new SplashScreen(MainFrame.this)));
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		super("Dungeon Diver");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLayout(new GridBagLayout());
		//open on splash screen
		setContentPane(new SplashScreen(this));
		
		//call button functionality 
		escOpensSettings();
		iOpensInventory();
	}
	
	//escape for pause/settings
	private void escOpensSettings() {
		//set up maps
	    InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap am = getRootPane().getActionMap();

	    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escPressed");

	    am.put("escPressed", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            //do nothing if already in settings
	            if (panelTracker.get(1) instanceof SettingsScreen) {
	                return;
	            }
	            
	            //do not open settings from inventory
	            if (panelTracker.get(1) instanceof Inventory) {
	            	return;
	            }

	            //swap to settings
	            panelSwapper(new SettingsScreen(MainFrame.this));

	            revalidate();
	            repaint();
	        }
	    });
	}
	
	//escape for pause/settings
	private void iOpensInventory() {
		//set up maps
	    InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap am = getRootPane().getActionMap();

	    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), "iPressed");

	    am.put("iPressed", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            //do nothing if already in inventory
	            if (panelTracker.get(1) instanceof Inventory) {
	                return;
	            }
	            
	            //do not open inventory from settings
	            if (panelTracker.get(1) instanceof SettingsScreen) {
	            	return;
	            }

	            //swap to settings
	            panelSwapper(new Inventory(MainFrame.this));

	            revalidate();
	            repaint();
	        }
	    });
	}
	
	//function to swap panel
	public void panelSwapper(JPanel newPanel) {
		//set current frame to past frame
		panelTracker.set(0, panelTracker.get(1));
		//update current frame
		panelTracker.set(1, newPanel);
		
		//swap panel
        setContentPane(newPanel);
        revalidate();
        repaint();
	}
	
	//function to return to previous frame
	public void returnToPrevFrame() {
		//catcher
		if (panelTracker.get(0) == null) {//no panel to switch into
			//print error message (for easier debug)
			System.out.println("Attempting to return to nothing!");
			//stop
			return;
		}
		
		//store prev panel
		JPanel prevPanel = panelTracker.get(0);
		
		//update display
		setContentPane(prevPanel);
        revalidate();
        repaint();
        
        //update frame tracker
        panelTracker.set(0, panelTracker.get(1));
        panelTracker.set(1, prevPanel);
	}
}
