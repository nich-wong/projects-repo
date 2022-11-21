
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class RegisterPanel extends FileUsers{
    private JLabel lblTitle;
    private JLabel lblName;
    private JLabel lblID;
    private JTextField tfName;
    private JTextField tfID;
    private JButton btnRegister;
    private JLabel lblRegister;
    private JRadioButton rbtnStaff;
    private JRadioButton rbtnStudent;
    private ButtonGroup bgrpRegisterAs;
	
	public RegisterPanel() {
        //construct components
        lblTitle = new JLabel ("REGISTER PAGE");
        lblName = new JLabel ("Name");
        lblID = new JLabel ("ID");
        tfName = new JTextField (5);
        tfID = new JTextField (5);
        btnRegister = new JButton ("Register");
        lblRegister = new JLabel ("Register as");
        rbtnStaff = new JRadioButton ("Staff");
        rbtnStudent = new JRadioButton ("Student");
        bgrpRegisterAs = new ButtonGroup ();
        

        //adjust size and set layout
        setPreferredSize (new Dimension (297, 190));
        setLayout (null);

        //add components
        add (lblTitle);
        add (lblName);
        add (lblID);
        add (tfName);
        add (tfID);
        add (btnRegister);
        add (lblRegister);
        add (rbtnStaff);
        add (rbtnStudent);
        
        bgrpRegisterAs.add(rbtnStaff); 
        bgrpRegisterAs.add(rbtnStudent); 

        //set component bounds (only needed by Absolute Positioning)
        lblTitle.setBounds (20, 10, 100, 25);
        lblName.setBounds (20, 40, 60, 25);
        lblID.setBounds (20, 70, 60, 25);
        tfName.setBounds (125, 40, 150, 25);
        tfID.setBounds (125, 70, 150, 25);
        btnRegister.setBounds (20, 150, 100, 25);
        lblRegister.setBounds (20, 100, 70, 25);
        rbtnStaff.setBounds (210, 100, 70, 25);
        rbtnStudent.setBounds (125, 100, 70, 25);		
		
	}
	

	//Additional methods
	
	public void setEventBtnRegister(RegisterFrame s) {
		btnRegister.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent a){	
        		try {
					checkFields(s);
					
				} catch (IOException e) {
					e.printStackTrace();
				}

        	}
        });	
	}
	
	public void checkFields(RegisterFrame s) throws IOException {
		
		if (tfName.getText().equals("") || tfID.getText().contentEquals("") 
		|| (rbtnStudent.isSelected() == false && rbtnStaff.isSelected() == false)) {			
			
			JOptionPane.showMessageDialog(this,"Please fill in empty fields.");
		}
		
		else {
			addToUsers(tfName.getText(), tfID.getText(), getSelectedButtonText(bgrpRegisterAs));			
			new LoginFrame();
			s.setVisible(false);	
			
		}
			
	}
	
	
}
