
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
/**
 *
 * @author cody
 */
public class Gui {
    
    private NotePadTest notePad;
    private static JFrame frame;
    private static JMenuBar menuBar;
    private static JMenu file;
    private static JMenu edit;
    private static JMenu help;
    private final JPanel mainPanel;
    private JTextArea textBox;
    private String text;
    private String clipboard;
    private String fileName = "Untitled";
    
    public Gui(NotePadTest notePad) {
        this.notePad = notePad;
        
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
        // textBox = new JTextArea(text, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textBox = new JTextArea(text);
        mainPanel.add(textBox);
        
        newFileButton.addActionListener(e -> {
            notePad.newDocument();
        });
        saveButton.addActionListener(e -> {
            notePad.save(frame, textBox);
        });
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
        copyButton.addActionListener(e -> {
            notePad.copy(textBox);
        });
        pasteButton.addActionListener(e -> {
            notePad.paste(textBox);
        });
        
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        
        frame.setVisible(true);
    }
}
