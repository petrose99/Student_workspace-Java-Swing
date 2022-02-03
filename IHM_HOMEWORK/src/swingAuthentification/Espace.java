package swingAuthentification;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Espace extends JFrame {

	private JTable tab= new JTable();
	DefaultTableModel tabModel= new DefaultTableModel();
	private JPanel contentPane;
	private JTextField nom,prenom,matricule,adresse,note;


	public static void main(String[] args) {
			Espace frame = new Espace();
			frame.setVisible(true);
	}

	//constructor
	public Espace() {
		setTitle("Espace");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 469);
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(3, 1));
		setContentPane(contentPane);
		//tabModel =(DefaultTableModel) tab;
		JPanel menupan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(menupan);
		
		JButton btnNewButton = new JButton("Nouveau");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nouveau();
			}
		});
		menupan.add(btnNewButton);
		//button declared
		JButton btnEnregistrer = new JButton("Enregistrer");
		//ActionListener added to the button flags on click
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					enregistrer();
			}
		});
		menupan.add(btnEnregistrer);
		
		JButton btnSupprimer = new JButton("Supprimer");
		menupan.add(btnSupprimer);
		btnSupprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			supprimer();
			}
			
		});

		//Adds composants to the panel
		JPanel genPanel = new JPanel();
		contentPane.add(genPanel);
		genPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel labelspanel = new JPanel(new GridLayout(5, 1, 10, 10));
		genPanel.add(labelspanel);
		
		JLabel matLabel = new JLabel("Matricule");
		labelspanel.add(matLabel);
		
		JLabel nomLabel = new JLabel("Nom");
		labelspanel.add(nomLabel);
		
		JLabel prenomLabel = new JLabel("Prenom");
		labelspanel.add(prenomLabel);
		
		JLabel adrsLabel = new JLabel("Adresse");
		labelspanel.add(adrsLabel);
		
		JLabel noteLbl = new JLabel("Note");
		labelspanel.add(noteLbl);
		
		JPanel txtfldpanel = new JPanel();
		genPanel.add(txtfldpanel);
		txtfldpanel.setLayout(new GridLayout(5, 1, 5, 5));
		
		matricule = new JTextField();
		txtfldpanel.add(matricule);
		matricule.setColumns(30);
		
		nom = new JTextField();
		txtfldpanel.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		txtfldpanel.add(prenom);
		prenom.setColumns(10);
		
		adresse = new JTextField();
		txtfldpanel.add(adresse);
		adresse.setColumns(10);
		
		note = new JTextField();
		txtfldpanel.add(note);
		note.setColumns(30);
		
			
		String[] attributs= {"Matricule","Nom","Prenom","Adresse","Note"};
		tabModel.setColumnIdentifiers(attributs);
		tab.setModel(tabModel);
		
		
		JScrollPane scrollPane = new JScrollPane(tab);
		contentPane.add(scrollPane);

		
	}

	protected void supprimer() {
		if(tabModel.getRowCount()>0 && tab.getSelectedRow()>=0) {
			tabModel.removeRow(tab.getSelectedRow());
			//suppression dans le fichier 
		}
		else  JOptionPane.showMessageDialog(null, "Aucune ligne séléctionnée !");
	
	}

	protected void enregistrer() {
		if(tabModel.getRowCount()>0 && tab.getSelectedRow()>=0) {
			try {
				FileWriter fichierEtudiant = new FileWriter("Etudiants.txt",true);
				for(int i=0;i<5;i++) {
					fichierEtudiant.write((tab.getValueAt(tab.getSelectedRow(), i).toString()));
					if(i<4) fichierEtudiant.write("---");
					if(i==4)fichierEtudiant.write("\n");
				}
				JOptionPane.showMessageDialog(null, "Les données enregistrées dans le fichier (Etudiants.txt)!");
				fichierEtudiant.close();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erreur d'ouverture fichier !");
			}
		}
		else  JOptionPane.showMessageDialog(null, "Aucune ligne séléctionnée !");	

		
	}

	protected void nouveau(){
		//Revoir les cas impossible
		String[] info= {matricule.getText(),nom.getText(),prenom.getText(),adresse.getText(),note.getText()};
		if(!this.matricule.getText().isEmpty() && !this.nom.getText().isEmpty() && !this.prenom.getText().isEmpty() && !this.adresse.getText().isEmpty() && !this.note.getText().isEmpty()) {
			//si aucune case n'est vide on ajoute a la table
			tabModel.addRow(info);
			vider();
		}
		else JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs");
	}

	//Ensures the textfields are emptied after each input of information
	private void vider() {
		this.nom.setText("");
		this.matricule.setText("");
		this.prenom.setText("");
		this.adresse.setText("");
		this.note.setText("");
		
	}

}
