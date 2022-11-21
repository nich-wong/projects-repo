
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.Border;

public class StaffPanel extends FileUsers{
    private JLabel lblLogin;
	private JLabel lblName;
    private JLabel lblID;
    private JTextField tfName;
    private JTextField tfID;
    private JLabel lblComplaint;
    private JComboBox cbComplaint;
    private JLabel lblStatus;
    private JRadioButton rbtnMinor;
    private JRadioButton rbtnEmergency;
    private JLabel lblNotes;
    private JTextArea taNotes;
    private JLabel lblMedicine;
    private JCheckBox chkYes;
    private JCheckBox chkNo;
    private JTextArea taMedicine;
    private ButtonGroup bgrpStatus;
    private ButtonGroup bgrpMedicine;
    private JButton btnSubmit;

    
	public StaffPanel() {
        //construct preComponents
        String[] cbComplaintItems = {"Illness", "Injury", "Other"};
        
        //construct components
        lblLogin = new JLabel ();
        lblName = new JLabel ("Student's Name");
        lblID = new JLabel ("Student's ID");
        tfName = new JTextField (5);
        tfID = new JTextField (5);
        lblComplaint = new JLabel ("Type of Complaint");
        cbComplaint = new JComboBox (cbComplaintItems);
        lblStatus = new JLabel ("Status");
        rbtnMinor = new JRadioButton ("Minor");
        rbtnEmergency = new JRadioButton ("Emergency");
        lblNotes = new JLabel ("Notes");
        taNotes = new JTextArea (5, 5);
        lblMedicine = new JLabel ("Medicine Prescription");
        chkYes = new JCheckBox ("Yes");
        chkNo = new JCheckBox ("No");
        taMedicine = new JTextArea (5, 5);
        btnSubmit = new JButton ("Submit");
        
        bgrpStatus = new ButtonGroup();
        bgrpMedicine = new ButtonGroup();
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        //adjust size and set layout
        setPreferredSize (new Dimension (401, 650));
        setLayout (null);

        //add components
        add (lblLogin);
        add (lblName);
        add (lblID);
        add (tfName);
        add (tfID);
        add (lblComplaint);
        add (cbComplaint);
        add (lblStatus);
        add (rbtnMinor);
        add (rbtnEmergency);
        add (lblNotes);
        add (taNotes);
        add (lblMedicine);
        add (chkYes);
        add (chkNo);
        add (taMedicine);
        add (btnSubmit);
        
        bgrpStatus.add(rbtnMinor);
        bgrpStatus.add(rbtnEmergency);
        bgrpMedicine.add(chkYes);
        bgrpMedicine.add(chkNo);

        //set component bounds (only needed by Absolute Positioning)
        lblLogin.setBounds (45, 10, 310, 25);
        lblName.setBounds (45, 45, 100, 25);
        lblID.setBounds (45, 75, 100, 25);
        tfName.setBounds (205, 45, 150, 25);
        tfID.setBounds (205, 75, 150, 25);
        lblComplaint.setBounds (45, 150, 120, 25);
        cbComplaint.setBounds (205, 150, 150, 25);
        lblStatus.setBounds (45, 180, 100, 25);
        rbtnMinor.setBounds (200, 180, 65, 25);
        rbtnEmergency.setBounds (265, 180, 90, 25);
        lblNotes.setBounds (45, 210, 100, 25);
        taNotes.setBounds (45, 240, 311, 140);
        lblMedicine.setBounds (45, 410, 130, 25);
        chkYes.setBounds (210, 410, 50, 25);
        chkNo.setBounds (275, 410, 50, 25);
        taMedicine.setBounds (45, 440, 311, 140);   
        btnSubmit.setBounds (45, 595, 100, 25);
        
        //setBorder
        taNotes.setBorder(border);
        taMedicine.setBorder(border);
        
        
	}
	
	private ArrayList<String> fileTextUsers = new ArrayList<String>();

	
	public JLabel getLblLogin() {
		return lblLogin;
	}
	
	public void setEventBtnSubmit(StaffFrame s) {
		btnSubmit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent a){	
        		try {
        			checkName(s);
					
				} catch (IOException e) {
					e.printStackTrace();
				}

        	}
        });	
	}
	
	public void checkName(StaffFrame s) throws IOException {
		
		fileTextUsers = (ArrayList<String>) getFromFile("Users.txt");
		
		String name = tfName.getText();
		String id = tfID.getText();
		
		boolean tfEmpty = false; 
		boolean boxEmpty = false;
		
		if (name.equals("") || id.contentEquals("")) {
			tfEmpty = true;	
		}
		
		if (!rbtnMinor.isSelected() && !rbtnEmergency.isSelected() || !chkYes.isSelected() && !chkNo.isSelected()) {
			boxEmpty = true;
		}
		
		if (tfEmpty == true || boxEmpty == true) {
			if(tfEmpty == true)
				JOptionPane.showMessageDialog(this,"Please fill in the Student's Name & ID.");
			
			if(boxEmpty == true)
				JOptionPane.showMessageDialog(this,"Please check the empty boxes.");
		}
		
		else {
			
			if (!fileTextUsers.contains(name)) {
				addToUsers(name, id, "Student");
			}
			
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  HH:mm");  
		    Date date = new Date();  
			
			addToRecords(name, id, (String)cbComplaint.getSelectedItem(), getSelectedButtonText(bgrpStatus), getSelectedButtonText(bgrpMedicine),  formatter.format(date), taNotes.getText(), taMedicine.getText());
			
			JOptionPane.showMessageDialog(this,"Records added to database");
			
		}
		
		
	}
	
	
	
	
}
