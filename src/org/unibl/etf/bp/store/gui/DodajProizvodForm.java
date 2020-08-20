package org.unibl.etf.bp.store.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.store.entity.Dobavljac;
import org.unibl.etf.bp.store.entity.Faktura;
import org.unibl.etf.bp.store.entity.Proizvod;
import org.unibl.etf.bp.store.entity.Proizvodjac;
import org.unibl.etf.bp.store.util.Utilities;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class DodajProizvodForm extends JFrame {

	private JPanel contentPane;
	private JTextField textNabavnaCijena;
	private JTextField textKolicina;
	private JTextField textNaziv;
	private JTextField textOpis;
	private JTextField textProdajnaCijena;
	private JComboBox<String> comboProizvodi;
	private JComboBox<String> comboProizvodjaci;
	private JComboBox<String> comboKategorije;
	
	private List<Dobavljac> dobavljaci;
	private List<Proizvod> proizvodi;
	private List<Proizvodjac> proizvodjaci;
	private List<String> kategorije;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajProizvodForm frame = new DodajProizvodForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DodajProizvodForm() {
		dobavljaci=Utilities.getDataAccessFactory().getDobavljacDataAccess().dobavljaci();
		proizvodi= Utilities.getDataAccessFactory().getProizvodDataAccess().proizvodi();
		proizvodjaci = Utilities.getDataAccessFactory().getProizvodjacDataAccess().proizvodjaci();
		kategorije = Utilities.getDataAccessFactory().getProizvodDataAccess().kategorije();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Dodaj proizvod");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 5));
		
		JLabel lblDobavljac = new JLabel("Dobavljac:");
		lblDobavljac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblDobavljac);
		
		List<String> comboBoxDobavljaci = new ArrayList<>();
		for (Dobavljac d : dobavljaci) {
			comboBoxDobavljaci.add(d.getId() + "- " + d.getNaziv());
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(comboBoxDobavljaci.toArray(new String[0]));
		JComboBox<String> comboDobavljaci = new JComboBox<>(model);
		contentPane.add(comboDobavljaci);
		
		JLabel lblPostojeciProizvod = new JLabel("Postojeci proizvod?");
		lblPostojeciProizvod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblPostojeciProizvod);
		
		JRadioButton radioButton = new JRadioButton("");
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioButton.isSelected()) {
					comboProizvodi.setEnabled(true);
					textNaziv.setEnabled(false);
					textOpis.setEnabled(false);
					textProdajnaCijena.setEnabled(false);
					comboProizvodjaci.setEnabled(false);
					comboKategorije.setEnabled(false);
				} else {
					comboProizvodi.setEnabled(false);
					textNaziv.setEnabled(true);
					textOpis.setEnabled(true);
					textProdajnaCijena.setEnabled(true);
					comboProizvodjaci.setEnabled(true);
					comboKategorije.setEnabled(true);
				}
			}
		});
		contentPane.add(radioButton);
		
		JLabel lblProizvod = new JLabel("Proizvod:");
		lblProizvod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblProizvod);
		
		List<String> comboBoxProizvodi = new ArrayList<>();
		for (Proizvod p : proizvodi) {
			comboBoxProizvodi.add(p.getSifra() + "- " + p.getNaziv());
		}
		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>(comboBoxProizvodi.toArray(new String[0]));
		comboProizvodi = new JComboBox<>(model1);
		contentPane.add(comboProizvodi);
		comboProizvodi.setEnabled(false);
		
		JLabel lblNabavnaCijena = new JLabel("Nabavna cijena:");
		lblNabavnaCijena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNabavnaCijena);
		
		textNabavnaCijena = new JTextField();
		contentPane.add(textNabavnaCijena);
		textNabavnaCijena.setColumns(10);
		
		JLabel lblKolicina = new JLabel("Kolicina:");
		lblKolicina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblKolicina);
		
		textKolicina = new JTextField();
		contentPane.add(textKolicina);
		textKolicina.setColumns(10);
		
		JLabel lblNaziv = new JLabel("Naziv:");
		lblNaziv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNaziv);
		
		textNaziv = new JTextField();
		contentPane.add(textNaziv);
		textNaziv.setColumns(10);
		
		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblOpis);
		
		textOpis = new JTextField();
		contentPane.add(textOpis);
		textOpis.setColumns(10);
		
		JLabel lblProizvodjac = new JLabel("Proizvodjac:");
		lblProizvodjac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblProizvodjac);
		
		List<String> comboBoxProizvodjaci = new ArrayList<>();
		for (Proizvodjac p : proizvodjaci) {
			comboBoxProizvodjaci.add(p.getId() + "- " + p.getNaziv());
		}
		DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>(comboBoxProizvodjaci.toArray(new String[0]));
		comboProizvodjaci = new JComboBox<>(model2);
		contentPane.add(comboProizvodjaci);
		
		JLabel lblProdajnaCijena = new JLabel("Prodajna cijena:");
		lblProdajnaCijena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblProdajnaCijena);
		
		textProdajnaCijena = new JTextField();
		contentPane.add(textProdajnaCijena);
		textProdajnaCijena.setColumns(10);
		
		JLabel lblKategorija = new JLabel("Kategorija:");
		lblKategorija.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblKategorija);
		
		DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<>(kategorije.toArray(new String[0]));
		comboKategorije = new JComboBox<>(model3);
		contentPane.add(comboKategorije);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		contentPane.add(label_3);
		
		JLabel label_5 = new JLabel("");
		contentPane.add(label_5);
		
		JButton btnSpremi = new JButton("Spremi");
		btnSpremi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idDobavljaca = Integer.parseInt(comboDobavljaci.getSelectedItem().toString().split("-")[0].trim());
				Dobavljac dobavljac = new Dobavljac();
				for(Dobavljac d: dobavljaci) {
					if(d.getId() == idDobavljaca) {
						dobavljac = d;
						break;
					}
				}
				double nabavnaCijena = Double.parseDouble(textNabavnaCijena.getText());
				int kolicina =Integer.parseInt(textKolicina.getText());
				if(radioButton.isSelected()) {
					Proizvod proizvod = new Proizvod();
					int sifraProizvoda = Integer.parseInt(comboProizvodi.getSelectedItem().toString().split("-")[0].trim());
					for(Proizvod p:proizvodi) {
						if(p.getSifra() == sifraProizvoda) {
							proizvod = p;
							break;
						}
					}
					Faktura faktura = new Faktura(dobavljac, proizvod, nabavnaCijena, kolicina);
					if(Utilities.getDataAccessFactory().getFakturaDataAccess().dodajFakturuZaPostojeciProizvod(faktura)) {
						JOptionPane.showMessageDialog(null, "Faktura uspjesno snimljena");
					} else {
						JOptionPane.showMessageDialog(null, "Faktura nije uspjesno snimljena");
					}
				}else {
					Proizvodjac proizvodjac = new Proizvodjac();
					int idProizvodjaca = Integer.parseInt(comboProizvodjaci.getSelectedItem().toString().split("-")[0].trim());
					for(Proizvodjac p: proizvodjaci) {
						if(p.getId() == idProizvodjaca) {
							proizvodjac = p;
							break;
						}
					}
					Proizvod proizvod = new Proizvod(textNaziv.getText(), textOpis.getText(), Integer.parseInt(textKolicina.getText()),
							Double.parseDouble(textProdajnaCijena.getText()), proizvodjac);
					Faktura faktura = new Faktura(dobavljac, proizvod, nabavnaCijena, kolicina);
					if(Utilities.getDataAccessFactory().getFakturaDataAccess().dodajFakturu(faktura, comboKategorije.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "Faktura uspjesno snimljena");
					} else {
						JOptionPane.showMessageDialog(null, "Faktura nije uspjesno snimljena");
					}
				}
			}
		});
		btnSpremi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnSpremi);
	}
}
