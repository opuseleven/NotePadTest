/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
/**
 *
 * @author cody
 */
public class NotePadTest {
    
    private static JFrame frame;
    private static JMenuBar menuBar;
    private static JMenu file;
    private static JMenu edit;
    private static JMenu help;
    private final JPanel mainPanel;
    private TextArea textBox;
    private String fileName = "Untitled";
    private String text;
    private String clipboard;
    
    public NotePadTest() {
        
        frame = new JFrame("NotePad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        
        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);
        
        JMenuItem newFileButton = new JMenuItem("New File");
        JMenuItem saveButton = new JMenuItem("Save");
        JMenuItem quitButton = new JMenuItem("Quit");
        JMenuItem copyButton = new JMenuItem("Copy");
        JMenuItem pasteButton = new JMenuItem("Paste");
        JMenuItem helpButton = new JMenuItem("Help");
        
        newFileButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK,true));
        saveButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK,true));
        quitButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK,true));
        copyButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK,true));
        pasteButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK,true));
        
        file.add(newFileButton);
        file.add(saveButton);
        file.add(quitButton);
        edit.add(copyButton);
        edit.add(pasteButton);
        help.add(helpButton);
        
        mainPanel = new JPanel();
        text = "";
        clipboard = "";
        textBox = new TextArea(text, 0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);

        mainPanel.add(textBox);
        
        newFileButton.addActionListener(e -> {
            newDocument();
        });
        saveButton.addActionListener(e -> {
            save();
        });
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
        copyButton.addActionListener(e -> {
            
        });
        
        
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        
        frame.setVisible(true);
    }
    
    public void newDocument() {
        fileName = "Untitled";
        text = "";
    }
    public void save() {
        JFileChooser saveChooser = new JFileChooser();
        int option = saveChooser.showSaveDialog(frame);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(saveChooser.getSelectedFile().getPath()));
                out.write(this.textBox.getText());
                fileName = saveChooser.getName();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void copy() {
        
    }
}
