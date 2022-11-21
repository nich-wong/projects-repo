
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StudentDialog extends JDialog implements ActionListener, PropertyChangeListener {

    private String typedText = null;
    private JTextField textField;
    private JOptionPane optionPane;
    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";
    private boolean login = false;
    
    
    
    public String getValidatedText() {
        return typedText;
    }
    
    public StudentDialog (Frame aFrame) {
    	
        super(aFrame, true);

        setTitle("Enter input");

        textField = new JTextField(10);
        

        //Create an array of the text and components to be displayed.
        String msgString1 = "Enter Student's ID";
        Object[] array = {msgString1, textField};

        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options = {btnString1, btnString2};

        //Create the JOptionPane.
        optionPane = new JOptionPane(array,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                options,
                options[0]);

        //Make this dialog display it.
        setContentPane(optionPane);

        //Handle window closing correctly.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        textField.addActionListener(this);

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        pack();
    }
    

    /**
     * This method handles events for the text field.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    /**
     * This method reacts to state changes in the option pane.
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        

		ArrayList<String> userFile = new ArrayList<String>();
		ArrayList<String> recordFile = new ArrayList<String>();
		FileUsers file1 = new FileUsers();
		try {
			userFile= (ArrayList<String>) file1.getFromFile("Users.txt");
			recordFile = (ArrayList<String>) file1.getFromFile("Records.txt");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		

        if (isVisible()
                && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop)
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }

            //Reset the JOptionPane's value.
            //If you don't do this, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);

            if (btnString1.equals(value)) {
                typedText = textField.getText();
                
                if (userFile.contains(typedText) && userFile.get( (userFile.indexOf(typedText)) + 1 ).equals("Student") ) {
                	if (recordFile.contains(typedText)) {
                    	login = true;
                        exit();
                	}
                	else {
                		textField.selectAll();
                        JOptionPane.showMessageDialog(this,
                                "Student has no previous records",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        typedText = null;
                        textField.requestFocusInWindow();
                	}
                    
                } else {

                    textField.selectAll();
                    JOptionPane.showMessageDialog(this,
                            "ID not found in database",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    typedText = null;
                    textField.requestFocusInWindow();
                }
            } else { //user closed dialog or clicked cancel
                typedText = null;
                exit();
            }
        }
    }

    /**
     * This method clears the dialog and hides it.
     */
    public void exit() {
        dispose();
    }
    
    public boolean getLogin() {
    	return login;
    }
    
    public String getTypedText() {
    	return typedText;
    }
    
    
}
