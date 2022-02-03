package swingAuthentification;
//Import of java packages
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;


//declaration of the class SwingDemo that implements  ActionListener class
public class SwingDemo implements ActionListener {
	private JFrame window;
	private JPanel controlPanel,MPanel;
	private JTextField userfield;
	private JPasswordField passwordfield;
	private JButton loginButton;
	
	//class constructor
	public SwingDemo() {
		createFrame();
		
	}
	
	//
	public static void main(String[] args) {		
      SwingDemo Test = new SwingDemo();
      Test.createFrame();
		
      
      
	}

	
	
	 private void createFrame() {
//Instanciating the Jframe class		 
		 window = new JFrame("Authentification");
//size of the Jframe popup		 
		 window.setSize(500, 350);
		
		 //Container for the elements being added to the window
		 controlPanel = new JPanel();
		 MPanel = new JPanel();
		 
		 
//Layout Manager		 
		 GridBagLayout disposition = new GridBagLayout();
	
		//adding the layout manager to the created container 
		  controlPanel.setLayout(disposition);
		  window.setLocationRelativeTo(null);
		  MPanel.setBackground(Color.BLUE);
		  MPanel.setLayout(disposition);
		  MPanel.add(controlPanel);
      	 window.add(MPanel);
		 window.setVisible(true);
		 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 JLabel login = new JLabel("USERNAME: ");
		 JLabel password = new JLabel("PASSWORD: ");
		 userfield = new JTextField(8);
		 passwordfield = new JPasswordField(8);
		
		loginButton = new JButton("Login");
		
		//adds a listener to the button
		loginButton.addActionListener(this);
		 
		
		GridBagConstraints constraints = new GridBagConstraints();
		 constraints.fill = GridBagConstraints.BOTH;
		 
		 constraints.insets = new Insets(10,10,10,10);
	
//The following code will specify the points of the components and add them to the panel		 
		constraints.gridx = 0;
		constraints.gridy = 0;
		controlPanel.add(login,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		controlPanel.add(userfield,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		controlPanel.add(password,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		controlPanel.add(passwordfield,constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 2;
		controlPanel.add(loginButton,constraints);
		
	 }
	 
//method actionperformed flags when the button is clicked	 
	public void actionPerformed(ActionEvent e) {

		JButton button1=(JButton)e.getSource();
		
		//Verifies that the button clicked is login
		if(button1.getText().equals("Login")) {
			
			
//Try...Catch used to handle exceptions and abnormal termination of programme		
		try {
			//Creation of a file called compt.txt
			File myObj = new File("compt.txt");
			Scanner read = new Scanner(myObj);
		
			 read.useDelimiter("[,\n]");
			 
//Loops through the file while it has a next line			
			while(read.hasNextLine()) {
				System.out.println("le while  marche  mais il y a souci dans ce code");
				String user = read.next();
				String pass = read.next();
				
				if(user.trim().equals(userfield.getText().trim()) && pass.trim().equals(passwordfield.getText().trim() ) ) {
					disp();
				}
			
			else {
				JOptionPane.showMessageDialog(null, "Incorrect data");
			}
				}
			read.close();
			
		}catch (FileNotFoundException qz) {
			JOptionPane.showMessageDialog(null, "Invalid Credentials");
		}
	}
		}
			

//function that calls the class Espace
	private void disp() {
		new Espace();
		
	}


}
