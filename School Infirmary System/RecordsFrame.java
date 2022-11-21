
import javax.swing.JFrame;

public class RecordsFrame extends Frames{
	
	public RecordsFrame() {
		initialiseFrame();
	}

	public void initialiseFrame() {
		RecordsPanel recordsPanel = new RecordsPanel();		
		setTitle("School Infirmary Record");
	    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().add (recordsPanel);
	    pack();
	    setVisible (true);
		
	}
	
	
	

}
