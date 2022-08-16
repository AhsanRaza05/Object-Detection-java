package com.test.jfilepicker;

import java.awt.FlowLayout;
import java.io.File;
 
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
 
public class JFilePickerFrame extends JFrame {
 
    String filePath;
	
	public JFilePickerFrame() {
        super("Test using JFilePicker");
         
        setLayout(new FlowLayout());
 
        // set up a file picker component
        JFilePicker filePicker = new JFilePicker("Pick a file", "Browse...");
        filePicker.setMode(JFilePicker.MODE_OPEN);
        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
        filePicker.addFileTypeFilter(".mp4", "MPEG-4 Videos");
        
        // access JFileChooser class directly
        JFileChooser fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\Users\\AHSAN\\Desktop\\Java Image Testing\\Second Test\\Latest\\src\\com\\images"));
        
        filePath = filePicker.getSelectedFilePath();
        // add the component to the frame
        add(filePicker);
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 100);
        setLocationRelativeTo(null);    // center on screen
    }
	
	public static String getFilePath() {
		
JFrame frame = new JFrame("Test using JFilePicker");
        
        frame.setLayout(new FlowLayout());
        
        // set up a file picker component
        JFilePicker filePicker = new JFilePicker("Pick a file", "Browse...");
        filePicker.setMode(JFilePicker.MODE_OPEN);
        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
        filePicker.addFileTypeFilter(".mp4", "MPEG-4 Videos");
        
        // access JFileChooser class directly
        JFileChooser fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\Users\\AHSAN\\Desktop\\Java Image Testing\\Second Test\\Latest\\src\\com\\images"));
        
        String filePath = filePicker.getSelectedFilePath();
        // add the component to the frame
        frame.add(filePicker);
         
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
		return filePath;
	}
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new JFilePickerFrame().setVisible(true);
            }
        });
    }
 
}