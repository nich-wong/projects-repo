
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class RecordsPanel extends FileUsers implements ButtonDetails{
    private JTextArea taTime;
    private JTextArea taName;
    private JTextArea taComplaint;
    private JTextArea taMed;
    private JLabel lblTime;
    private JLabel lblName;
    private JLabel lblComplaint;
    private JLabel lblMed;
    private ArrayList<String> textRecord = new ArrayList<String>();
    private int btnCount = 0;
    private ArrayList<JButton> btnDetails = new ArrayList<JButton>();

	public RecordsPanel() {
        //construct components
        taTime = new JTextArea (5, 5);
        taName = new JTextArea (5, 5);
        taComplaint = new JTextArea (5, 5);
        taMed = new JTextArea (5, 5);
        lblTime = new JLabel ("Date & Time");
        lblName = new JLabel ("Student");
        lblComplaint = new JLabel ("Complaint");
        lblMed = new JLabel ("Medicine");
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        
        //adjust size and set layout
        setPreferredSize (new Dimension (758, 563));
        setLayout (null);
        
        //add components
        add (taTime);
        add (taName);
        add (taComplaint);
        add (taMed);
        add (lblTime);
        add (lblName);
        add (lblComplaint);
        add (lblMed);

        //set component bounds (only needed by Absolute Positioning)
        taTime.setBounds (25, 70, 140, 470);
        taName.setBounds (165, 70, 250, 470);
        taComplaint.setBounds (415, 70, 160, 470);
        taMed.setBounds (575, 70, 65, 470);
        lblTime.setBounds (60, 45, 120, 25);
        lblName.setBounds (265, 45, 55, 25);
        lblComplaint.setBounds (465, 45, 115, 25);
        lblMed.setBounds (580, 45, 55, 25);
        
        //Set border
        taTime.setBorder(border);
        taName.setBorder(border);
        taComplaint.setBorder(border);
        taMed.setBorder(border);
        
        //Set Font
        Font font = new Font("Consolas", Font.PLAIN, 14);
        taTime.setFont(font);
        taName.setFont(font);
        taComplaint.setFont(font);
        taMed.setFont(font);
        
        //Get text from Records File
        try {
			textRecord = (ArrayList<String>) getFromFile("Records.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //Set text areas
        int nextLine = 0;
        int yPosition = 0;
		for(int i = 0; i < textRecord.size(); i += nextLine) {
	        taTime.append(textRecord.get(i+2) + "\n");
	        taName.append(textRecord.get(i) + "\n");
	        taComplaint.append(textRecord.get(i+3) + " (" + textRecord.get(i+4) + ")\n");
	        taMed.append(textRecord.get(i+5) + "\n");
	        
	        int iTemp = (i+6);
	        int index = 0;
	        
	        while (!(textRecord.get(iTemp).equals("*"))) {
				iTemp++;
				index++;
	        }
	        
	        makeBtn(btnCount++, i, yPosition);
	        yPosition += 17;
	        
	        nextLine = (7 + index);
	        
		}
		
	}
	

	public void printDetails(int nextLine) {
		String textDialog = "";
		String name = textRecord.get(nextLine) + "'s Details";
		
		int index = nextLine + 6;
		
		while (!(textRecord.get(index).equals("*"))) {
			textDialog += textRecord.get(index) + "\n";
			index++; 
		}
		
		JOptionPane.showMessageDialog(this, textDialog, name, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void btnEvent(int btnIndex, int nextLine) {

		btnDetails.get(btnIndex).addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent a){	
        		printDetails(nextLine);
        	}
        });	
	}
	
	public void makeBtn(int btnIndex, int nextLine, int yPosition) {
		
		btnDetails.add(new JButton("Details"));
		add(btnDetails.get(btnIndex));
		btnDetails.get(btnIndex).setBounds (650, (72 + yPosition), 100, 15);
		btnEvent(btnIndex, nextLine);

	}

	
}
