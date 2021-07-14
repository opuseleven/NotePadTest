
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.undo.UndoManager;
/**
 *
 * @author cody
 */
public class Gui extends JFrame {
    
    private final NotePadTest notePad;
    private static JFrame frame;
    private static JMenuBar menuBar;
    private static JMenu file;
    private static JMenu edit;
    private static JMenu format;
    private static JMenu help;
    private JPopupMenu mouseMenu;
    private final JPanel mainPanel;
    private JTextArea textBox;
    private JScrollPane scrollPane;
    private String text;
    private String fileName = "Untitled";
    
    public Gui(NotePadTest notePad) {
        this.notePad = notePad;
        
        frame = new JFrame("NotePad" + " - " + fileName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        
        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        format = new JMenu("Format");
        help = new JMenu("Help");
        
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(help);
        
        UndoManager manager = new UndoManager();
        
        mouseMenu = new JPopupMenu();
        
        JMenuItem newFileButton = new JMenuItem("New Document");
        JMenuItem openButton = new JMenuItem("Open");
        JMenuItem saveButton = new JMenuItem("Save");
        JMenuItem saveAsButton = new JMenuItem("Save As");
        JMenuItem quitButton = new JMenuItem("Quit");
        JMenuItem copyButton = new JMenuItem("Copy");
        JMenuItem pasteButton = new JMenuItem("Paste");
        JMenuItem undoButton = new JMenuItem("Undo");
        JMenuItem redoButton = new JMenuItem("Redo");
        JMenuItem cutButton = new JMenuItem("Cut");
        JMenuItem bulletButton = new JMenuItem("Add Bullet");
        JMenuItem helpButton = new JMenuItem("Help");
        
        JMenuItem mCopyButton = new JMenuItem("Copy");
        JMenuItem mPasteButton = new JMenuItem("Paste");
        JMenuItem mCutButton = new JMenuItem("Cut");
        JMenuItem mUndoButton = new JMenuItem("Undo");
        JMenuItem mRedoButton = new JMenuItem("Redo");
        
        newFileButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK, true));
        saveButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK, true));
        quitButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK, true));
        copyButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK, true));
        pasteButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK, true));
        undoButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK, true));
        redoButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK, true));
        
        file.add(newFileButton);
        file.add(openButton);
        file.add(saveButton);
        file.add(saveAsButton);
        file.add(quitButton);
        edit.add(copyButton);
        edit.add(pasteButton);
        edit.add(undoButton);
        edit.add(redoButton);
        edit.add(cutButton);
        format.add(bulletButton);
        help.add(helpButton);
        
        mouseMenu.add(mCopyButton);
        mouseMenu.add(mPasteButton);
        mouseMenu.add(mUndoButton);
        mouseMenu.add(mRedoButton);
        mouseMenu.add(mCutButton);
        
        mainPanel = new JPanel();
        text = "";
        textBox = new JTextArea(30, 25);
        textBox.setAutoscrolls(true);
        textBox.setMargin(new Insets(20, 20, 20, 20));
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);
        textBox.getDocument().addUndoableEditListener(manager);
        textBox.setText(text);
        textBox.add(mouseMenu);
        
        mainPanel.add(textBox);
        
        newFileButton.addActionListener(e -> {
            notePad.newDocument(frame, textBox);
        });
        openButton.addActionListener(e-> {
            notePad.open(frame, textBox);
        });
        saveButton.addActionListener(e -> {
            notePad.save(frame, textBox);
        });
        saveAsButton.addActionListener(e -> {
            notePad.saveAs(frame, textBox);
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
        undoButton.addActionListener(e -> {
            manager.undo();
        });
        redoButton.addActionListener(e -> {
            manager.redo();
        });
        cutButton.addActionListener(e -> {
            notePad.cut(textBox);
        });
        bulletButton.addActionListener(e -> {
            notePad.addBullet(textBox);
        });
        helpButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, 
                    "Someday this message will help you.",
                    "Help",
                    JOptionPane.PLAIN_MESSAGE);
        });
        mCopyButton.addActionListener(e -> {
            notePad.copy(textBox);
        });
        mPasteButton.addActionListener(e -> {
            notePad.paste(textBox);
        });
        mUndoButton.addActionListener(e -> {
            manager.undo();
        });
        mRedoButton.addActionListener(e -> {
            manager.redo();
        });
        mCutButton.addActionListener(e -> {
            notePad.cut(textBox);
        });
        textBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
		}
            }
            @Override
            public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
                    showMenu(e);
		}
            }
            private void showMenu(MouseEvent e) {
                mouseMenu.show(e.getComponent(), e.getX(), e.getY());
            }
	});
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        
        frame.setVisible(true);
    }
}
