
import javax.swing.JFrame;

public class StudentFrame extends JFrame{
	
	public StudentFrame(String s) {

		StudentPanel studentPanel = new StudentPanel(s);
	    setTitle("Student Records");
	    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().add (studentPanel);
	    pack();
	    setVisible (true);
	    
	}
	
}
