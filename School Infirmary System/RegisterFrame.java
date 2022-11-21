
import javax.swing.JFrame;

public class RegisterFrame extends Frames{
	
	public RegisterFrame() {
		initialiseFrame();
	}

	public void initialiseFrame() {
		RegisterPanel registerPanel = new RegisterPanel();
	    setTitle("School Infirmary Record");
	    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    getContentPane().add (registerPanel);
	    pack();
	    setVisible (true);
	    
	    registerPanel.setEventBtnRegister(this);
	}
	
	
}
