/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
/**
 *
 * @author cody
 */
public class NotePadTest {
    
    private String fileName = "Untitled";
    private String text;
    private String clipboard;
    
    public NotePadTest() {
       
         newDocument();
    }
    
    public void newDocument() {
        fileName = "Untitled";
        text = "";
    }
    public void save(JFrame frame, JTextArea textBox) {
        JFileChooser saveChooser = new JFileChooser();
        int option = saveChooser.showSaveDialog(frame);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(saveChooser.getSelectedFile().getPath()));
                out.write(textBox.getText());
                fileName = saveChooser.getName();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void copy(JTextArea textBox) {
        textBox.copy();
    }
    public void paste(JTextArea textBox) {
        textBox.paste();
    }
}
