package MUSIC;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jl.decoder.JavaLayerException;

public class MainFrame extends JFrame  {
        JButton insertbtn = new JButton ("SELECT PLAYLIST");
	JButton playbtn = new JButton ("ONE WAY PLAY");
	JButton nextBtn = new JButton ("NEXT");
        JButton prevBtn =new JButton("PREV");
        JButton closebtn=new JButton("CLOSE");
        JRadioButton loopbtn =new JRadioButton ("LOOP");
	PlayList playlist = new PlayList(loopbtn);
        boolean loop = true;
	public MainFrame () {
		loopbtn.setSelected(true);
		ImageIcon image = new ImageIcon("MainIcon.png");
		
		this.setSize(600,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("BINARY BEASTS");	
		this.setIconImage(image.getImage());
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		JPanel panel1 = new JPanel();
	        panel1.add(insertbtn);
		panel1.add(playbtn);
                panel1.add(nextBtn);
		panel1.add(prevBtn);
		panel1.add(closebtn);
		
                panel1.add(loopbtn);
		panel1.setBounds(0,0, this.getWidth(),this.getHeight());
                panel1.setBackground(Color.BLACK);
                
                playbtn.setBackground(Color.WHITE);
                closebtn.setBackground(Color.WHITE);
                nextBtn.setBackground(Color.WHITE);
                prevBtn.setBackground(Color.WHITE);
		loopbtn.setBackground(Color.WHITE);
		
		this.add(panel1); 
                
                
		closebtn.addActionListener((e) -> {
                    System.exit(0);
                });
		playbtn.addActionListener((e) -> {
                    try {
                        playlist.topEl(null);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                nextBtn.addActionListener((e) -> {
                    try {
                        playlist.next();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                prevBtn.addActionListener((e) -> {
                    try {
                        playlist.prev();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
		insertbtn.addActionListener((e) -> {
                    	JFileChooser fc = new JFileChooser();
			fc.setMultiSelectionEnabled(true);
                        
			FileNameExtensionFilter filter = new FileNameExtensionFilter( "mp3 & wav audio Files . ", "mp3", "wav");
			fc.setFileFilter(filter);
			fc.setPreferredSize(new Dimension(800,500));
			
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File[] files = fc.getSelectedFiles();
				for(File file : files) {
				    String absolutePath = file.getAbsolutePath();
				   
				    playlist.addToEnd(absolutePath);
				   
				    
				}
				
			}
                });
                
		
                
	
                
	}
	
 	
}

