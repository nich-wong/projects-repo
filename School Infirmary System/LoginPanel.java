
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginPanel extends FileUsers{
    private JLabel lblTitle;
    private JLabel lblName;
    private JLabel lblID;
    private JTextField tfName;
    private JTextField tfID;
    private JButton btnLogin;
    private JButton btnRegister;
	
	public LoginPanel() {
		
        //construct components
        lblTitle = new JLabel ("LOGIN PAGE");
        lblName = new JLabel ("Name");
        lblID = new JLabel ("ID");
        tfName = new JTextField (5);
        tfID = new JTextField (5);
        btnLogin = new JButton ("Login");
        btnRegister = new JButton ("Register for new users");

        //adjust size and set layout
        setPreferredSize (new Dimension (297, 190));
        setLayout (null);

        //add components
        add (lblTitle);
        add (lblName);
        add (lblID);
        add (tfName);
        add (tfID);
        add (btnLogin);
        add (btnRegister);

        //set component bounds (only needed by Absolute Positioning)
        lblTitle.setBounds (20, 10, 100, 25);
        lblName.setBounds (20, 40, 60, 25);
        lblID.setBounds (20, 70, 60, 25);
        tfName.setBounds (125, 40, 150, 25);
        tfID.setBounds (125, 70, 150, 25);
        btnLogin.setBounds (20, 110, 100, 25);
        btnRegister.setBounds (20, 150, 170, 25);
        
        
	}
	
	//additional methods
    private ArrayList<String> textUser = new ArrayList<String>();
    private ArrayList<String> textRecord = new ArrayList<String>();
	private String name;
	private String id;
	
	public void setEventBtnLogin(LoginFrame lf) {
		btnLogin.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent a){	
        		try {
					createFiles();
        			loginValidation(lf);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
        		    		
        	}
        });	
		
	}

	public void loginValidation(LoginFrame lf) throws IOException {
		
		name = tfName.getText();
		id = tfID.getText();
		
		if (tfName.getText().equals("") || tfID.getText().contentEquals("")) {								
			JOptionPane.showMessageDialog(this,"Please fill in empty fields.");
		}
			
		else {
			textUser = (ArrayList<String>) getFromFile("Users.txt");


			if (textUser.contains(name)) {
				if (id.equals(textUser.get((textUser.indexOf(name)) + 1))) {
					userType(lf);
					
				}
				
				else 
					JOptionPane.showMessageDialog(this,"Your ID does not match with your name.");					
			} 
				
			else JOptionPane.showMessageDialog(this,"Your name cannot be found in the database.");
				
		}	

	}  

	public JButton getBtnRegister() {
		return btnRegister;
	};
	
	
	public void userType(LoginFrame lf) throws IOException {
		if ((textUser.get((textUser.indexOf(name)) + 2)).equals("Staff")) {
			new StaffFrame((textUser.get((textUser.indexOf(name)))), (textUser.get((textUser.indexOf(name)) + 2)));
			lf.setVisible(false);
		}
		
		else {
			textRecord = (ArrayList<String>) getFromFile("Records.txt");
			
			if (!textRecord.contains(id)) {
				JOptionPane.showMessageDialog(this,"Student has no previous records.");
			}
			else {
				new StudentFrame(id);
				lf.setVisible(false);
			}
		}
		
		
	}
	
	public void createFiles() {
		try {
			
			File file1 = new File("Users.txt");
			boolean exists1 = file1.exists();

			if (exists1 == false) {
		    	file1.createNewFile();
		    } 
			
			File file2 = new File("Records.txt");
			boolean exists2 = file2.exists();
			
			if (exists2 == false) {
		    	file2.createNewFile();
		    } 
		    	
		 } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
	}
}
