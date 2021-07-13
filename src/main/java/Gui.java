
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
/**
 *
 * @author cody
 */
public class Gui extends JFrame {
    
    private NotePadTest notePad;
    private static JFrame frame;
    private static JMenuBar menuBar;
    private static JMenu file;
    private static JMenu edit;
    private static JMenu help;
    private JPopupMenu mouseMenu;
    private final JPanel mainPanel;
    private JTextArea textBox;
    private JScrollPane scrollPane;
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
        
        mouseMenu = new JPopupMenu();
        
        JMenuItem newFileButton = new JMenuItem("New File");
        JMenuItem openButton = new JMenuItem("Open");
        JMenuItem saveButton = new JMenuItem("Save");
        JMenuItem quitButton = new JMenuItem("Quit");
        JMenuItem copyButton = new JMenuItem("Copy");
        JMenuItem pasteButton = new JMenuItem("Paste");
        JMenuItem cutButton = new JMenuItem("Cut");
        JMenuItem helpButton = new JMenuItem("Help");
        
        JMenuItem mCopyButton = new JMenuItem("Copy");
        JMenuItem mPasteButton = new JMenuItem("Paste");
        JMenuItem mCutButton = new JMenuItem("Cut");
        
        newFileButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK,true));
        saveButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK,true));
        quitButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK,true));
        copyButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK,true));
        pasteButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK,true));
        
        file.add(newFileButton);
        file.add(openButton);
        file.add(saveButton);
        file.add(quitButton);
        edit.add(copyButton);
        edit.add(pasteButton);
        edit.add(cutButton);
        help.add(helpButton);
        
        mouseMenu.add(mCopyButton);
        mouseMenu.add(mPasteButton);
        mouseMenu.add(mCutButton);
        
        mainPanel = new JPanel();
        text = "";
        clipboard = "";
        textBox = new JTextArea(30, 25);
        textBox.setAutoscrolls(true);
        textBox.setMargin(new Insets(20, 20, 20, 20));
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);
        textBox.setText(text);
        textBox.add(mouseMenu);
        
        mainPanel.add(textBox);
        
        newFileButton.addActionListener(e -> {
            notePad.newDocument();
        });
        openButton.addActionListener(e-> {
            notePad.open(frame, textBox);
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
        cutButton.addActionListener(e -> {
            notePad.cut(textBox);
        });
        
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        
        frame.setVisible(true);
    }
}
