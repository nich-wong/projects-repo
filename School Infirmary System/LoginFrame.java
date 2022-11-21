
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LoginFrame extends Frames {
	
	public LoginFrame() {	
		initialiseFrame();
	}

	public void initialiseFrame() {
		LoginPanel loginPanel = new LoginPanel();
		setTitle("School Infirmary Record");
	    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    getContentPane().add (loginPanel);
	    pack();
	    setVisible (true);
	    
	    //set action listener for register button
	    loginPanel.getBtnRegister().addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent a){	
    			RegisterFrame registerFrame = new RegisterFrame();
    			registerFrame.setVisible(true);
    			setVisible(false);
        	}
        });	
	    
	    loginPanel.setEventBtnLogin(this);
	}
	
	
}
