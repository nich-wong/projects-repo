
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class StaffFrame extends JFrame{
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem records, student, exit, login;
	
	public StaffFrame(String n, String t) {
		
		menubar = new JMenuBar();
		menu = new JMenu("Menu");
		records = new JMenuItem("Infirmary Records");
		student = new JMenuItem("Student Records");
		exit = new JMenuItem("Exit");
		login = new JMenuItem("Login Page");
		
		menu.add(records);
		menu.add(student);
		menu.add(login);
		menu.add(exit);
		menubar.add(menu);
		setJMenuBar(menubar);
		
		StaffPanel staffPanel = new StaffPanel();
	    setTitle("School Infirmary Record");
	    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    getContentPane().add (staffPanel);
	    pack();
	    setVisible (true);
	    setTextLblLogin(staffPanel, n, t);
	    staffPanel.setEventBtnSubmit(this);
	    
	    records.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent a){	
    			new RecordsFrame();
        	}
        });	
	    
	    student.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent a){	
        		StudentDialog sd = new StudentDialog(null);
        		sd.setVisible(true);
        		
        		if (sd.getLogin() == true) {
        			new StudentFrame(sd.getTypedText());
        		}
        		
        	}
        });
	    
	    login.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent a){	
    			new LoginFrame();
    			setVisible(false);
        	}
        });	
	   
	    menuExit(this);
	    
	}
	
	public void setTextLblLogin(StaffPanel s, String n, String t) {
		s.getLblLogin().setText("Logged in as: " + n + " (" + t + ")");
	}
	
	public void menuExit(StaffFrame s) {
	    exit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		int confirmed = JOptionPane.showConfirmDialog(s, 
        			"Are you sure you want to exit the program?", "Exit Program Message Box",
        			JOptionPane.YES_NO_OPTION);
            	if (confirmed == JOptionPane.YES_OPTION) dispose();
        	}
        });
	}
	
}
