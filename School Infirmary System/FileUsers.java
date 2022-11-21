
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class FileUsers extends JPanel{
	
	public void addToUsers(String u, String uId, String type) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("Users.txt", true));
		pw.println(u);
		pw.println(uId);
		pw.println(type);
		pw.close();
	}
	
	public List<String> getFromFile(String filename) throws IOException {
		return Files.readAllLines(Paths.get(filename), StandardCharsets.US_ASCII);	
	}
	
	public void addToRecords(String name, String id, String type, String status,  String med, String time, String notesC, String notesM) throws IOException {
		PrintWriter pw2 = new PrintWriter(new FileWriter("Records.txt", true));
		pw2.println(name);
		pw2.println(id);
		pw2.println(time);
		pw2.println(type);
		pw2.println(status);
		pw2.println(med);
		pw2.println(notesC);
		pw2.println(notesM);
		pw2.println("*");
		pw2.close();
	}
	

	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
		return null;
    }
	
	

}
