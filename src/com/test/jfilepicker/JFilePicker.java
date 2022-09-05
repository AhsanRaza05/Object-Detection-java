package com.test.jfilepicker;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Application.ObjectDetection;
 
public class JFilePicker extends JPanel {
    private String textFieldLabel;
    private String buttonLabel;
     
    private JLabel label;
    private JTextField textField;
    private JButton button;
     
    private JFileChooser fileChooser;
     
    private int mode;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;
     
    public JFilePicker(String textFieldLabel, String buttonLabel) {
        this.textFieldLabel = textFieldLabel;
        this.buttonLabel = buttonLabel;
         
        fileChooser = new JFileChooser();
         
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
 
        // creates the GUI
        label = new JLabel(textFieldLabel);
         
        textField = new JTextField(30);
        button = new JButton(buttonLabel);
         
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed(evt);            
            }
        });
         
        add(label);
        add(textField);
        add(button);
         
    }
     
    private void buttonActionPerformed(ActionEvent evt) {
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println("HIUU");
                
             // create a JTextArea
        	   	JTextArea textArea = new JTextArea(38, 45);
        	   	textArea.setEditable(false);
        	
        	   	JDialog dialgoue;
        	
        	   	JOptionPane pane;
        	
        	   	JScrollPane scrollPane;
          	
        	    // Display Result in Dialogue Box
        	   	
        	   	String objectColor = "-1";
        	   	objectColor = JOptionPane.showInputDialog("Enter Color of the object:\n1 --> Black Colored Objects\n2 --> White colored objects ");
        	   	
        	   	System.out.println( "Entered Color: "+ objectColor);
        	   	
        	   	if(objectColor != null && !objectColor.isEmpty()) {
        	   		

        	   		if(objectColor.equals("1") || objectColor.equals("2")) {
        	   			
        	   			textArea.setText(ObjectDetection.result(textField.getText().trim(), objectColor));
                	   	
                		// wrap a scrollpane around it
                		scrollPane = new JScrollPane(textArea);

                		// display them in a message dialog
                		pane = new JOptionPane(scrollPane);

                		dialgoue = pane.createDialog(null,
                				"***************************************** Object Detection in Java ***************************************** ");
                		// dialgoue.setLocation(0,0);
                		dialgoue.setVisible(true);
                		
        	   		}
        	   		else {
        	   			
        	   			JOptionPane.showMessageDialog(this, "Invalid Input!");
        	   		}
        	   	}
        	   	else {
        	   	
        	   		JOptionPane.showMessageDialog(this, "No Input!");
        	   	}
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }
 
    public void addFileTypeFilter(String extension, String description) {
        FileTypeFilter filter = new FileTypeFilter(extension, description);
        fileChooser.addChoosableFileFilter(filter);
    }
     
    public void setMode(int mode) {
        this.mode = mode;
    }
     
    public String getSelectedFilePath() {
        return textField.getText();
    }
     
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
}