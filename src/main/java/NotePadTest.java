/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author cody
 */
public class NotePadTest {
    
    private String fileName = "Untitled";
    private String filePath;
    private String text;
    private String clipboard;
    
    public NotePadTest() {
       
         text = "";
    }
    
    public void newDocument(JFrame frame, JTextArea textBox) {
        fileName = "Untitled";
        text = "";
        frame.setTitle("NotePad" + " - " + fileName);
        textBox.setText(text);
        
    }
    public void open(JFrame frame, JTextArea textBox) {
        JFileChooser openChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt File", "txt");
        openChooser.setFileFilter(filter);
        int option = openChooser.showOpenDialog(frame);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(openChooser.getSelectedFile().getPath()));
                ArrayList<String> lines = new ArrayList<>();
                String line;
                text = "";
                while ((line = in.readLine()) != null) {
                    lines.add(line);
                }
                in.close();
                for (int i = 0; i < lines.size(); i++) {
                    text = text + lines.get(i) + "\n";
                }
                textBox.setText(text);
                fileName = openChooser.getSelectedFile().getName();
                filePath = openChooser.getSelectedFile().getPath();
                frame.setTitle("NotePad" + " - " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void saveAs(JFrame frame, JTextArea textBox) {
        JFileChooser saveChooser = new JFileChooser();
        int option = saveChooser.showSaveDialog(frame);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(saveChooser.getSelectedFile().getPath() + ".txt"));
                out.write(textBox.getText());
                fileName = saveChooser.getSelectedFile().getName();
                filePath = saveChooser.getSelectedFile().getPath();
                frame.setTitle("NotePad" + " - " + fileName);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void save(JFrame frame, JTextArea textBox){
        if (fileName.equals("Untitled")) {
            saveAs(frame, textBox);
        } else {
            try{
                BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
                out.write(textBox.getText());
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
    public void cut(JTextArea textBox) {
        textBox.cut();
    }
}
