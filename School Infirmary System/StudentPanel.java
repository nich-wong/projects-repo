
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class StudentPanel extends FileUsers implements ButtonDetails{
    private JLabel lblName;
    private JLabel lblID;
    private JTextField tfName;
    private JTextField tfID;
    private JLabel lblTime;
    private JTextArea taTime;
    private JLabel lblComplaint;
    private JTextArea taComp;
    private JLabel lblMed;
    private JTextArea taMed;
    private ArrayList<String> textRecord = new ArrayList<String>();
    private ArrayList<JButton> btnDetails = new ArrayList<JButton>();
    private int btnCount = 0;

	public StudentPanel(String typedText) {
        //construct components
        lblName = new JLabel ("Student's Name");
        lblID = new JLabel ("Student's ID");
        tfName = new JTextField (5);
        tfID = new JTextField (5);
        lblTime = new JLabel ("Date & Time");
        taTime = new JTextArea (5, 5);
        lblComplaint = new JLabel ("Complaint");
        taComp = new JTextArea (5, 5);
        lblMed = new JLabel ("Medicine");
        taMed = new JTextArea (5, 5);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        Font font = new Font("Consolas", Font.PLAIN, 14);

        //adjust size and set layout
        setPreferredSize (new Dimension (498, 452));
        setLayout (null);

        //add components
        add (lblName);
        add (lblID);
        add (tfName);
        add (tfID);
        add (lblTime);
        add (taTime);
        add (lblComplaint);
        add (taComp);
        add (lblMed);
        add (taMed);

        //set component bounds (only needed by Absolute Positioning)
        lblName.setBounds (35, 45, 100, 25);
        lblID.setBounds (35, 75, 100, 25);
        tfName.setBounds (175, 45, 210, 25);
        tfID.setBounds (175, 75, 210, 25);
        lblTime.setBounds (73, 145, 100, 25);
        taTime.setBounds (35, 170, 140, 230);
        lblComplaint.setBounds (225, 145, 75, 25);
        taComp.setBounds (175, 170, 155, 230);
        lblMed.setBounds (333, 145, 55, 25);
        taMed.setBounds (330, 170, 60, 230);
        
        //format border & font
        taTime.setBorder(border);
        taTime.setFont(font);
        taComp.setBorder(border);
        taComp.setFont(font);
        taMed.setBorder(border);
        taMed.setFont(font);
        
        //Get text from Records File
        
        try {
			textRecord = (ArrayList<String>) getFromFile("Records.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        //set textField
        String id = typedText;
        tfID.setText(id);
        String name = textRecord.get(textRecord.indexOf(id) - 1);
        tfName.setText(name);
        
        //set textArea
        
        int yPosition = 0;
        
        for (int i = 0; i < textRecord.size(); i++) {
        	if (textRecord.get(i).equals(name)) {
        		taTime.append(textRecord.get(i+2) + "\n");
    	        taComp.append(textRecord.get(i+3) + " (" + textRecord.get(i+4) + ")\n");
    	        taMed.append(textRecord.get(i+5) + "\n");	
    	        
    	        makeBtn(btnCount, i, yPosition);
    	        btnCount++;
    	        yPosition += 17;
        	}
        }
        
        
	}
	
	public void makeBtn(int btnIndex, int i, int yPosition) {

		btnDetails.add(new JButton("Details"));
		add(btnDetails.get(btnIndex));
		btnDetails.get(btnIndex).setBounds (405, (172 + yPosition), 75, 15);
		btnEvent(btnIndex, i);
		
	}
	
	public void btnEvent(int btnIndex, int i) {

		btnDetails.get(btnIndex).addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent a){	
        		printDetails(i);
        	}
        });	
	}
	
	public void printDetails(int i) {
		
		String textDialog = "";
		String name = textRecord.get(i) + "'s Details";
		
		int index = i + 6;
		
		while (!(textRecord.get(index).equals("*"))) {
			textDialog += textRecord.get(index) + "\n";
			index++; 
		}
		JOptionPane.showMessageDialog(this, textDialog, name, JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	
	
}
