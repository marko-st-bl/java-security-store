package org.unibl.etf.bp.store.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import org.unibl.etf.bp.store.entity.Poslovnica;
import org.unibl.etf.bp.store.entity.Pozicija;
import org.unibl.etf.bp.store.entity.Zaposleni;
import org.unibl.etf.bp.store.util.Utilities;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class DodajZaposlenog extends JFrame {

	private JPanel contentPane;
	private JTextField textJMB;
	private JTextField textIme;
	private JTextField textPrezime;
	private JTextField textAdresa;
	private JTextField textTelefon;
	private JTextField textDatumRodjenja;
	private JTextField textDatumPrijema;
	private JComboBox<String> pozicija;
	private JButton btnDodaj;
	
	private List<Poslovnica> poslovnice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajZaposlenog frame = new DodajZaposlenog();
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
	public DodajZaposlenog() {
		poslovnice = Utilities.getDataAccessFactory().getPoslovnicaDataAcces().poslovnice();
		setTitle("Dodaj Zaposlenog");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(10, 4, 20, 20));

		JLabel lblJmb = new JLabel("JMB:");
		contentPane.add(lblJmb);

		textJMB = new JTextField();
		contentPane.add(textJMB);
		textJMB.setColumns(10);

		JLabel label = new JLabel("");
		contentPane.add(label);

		JLabel lblIme = new JLabel("Ime:");
		contentPane.add(lblIme);

		textIme = new JTextField();
		contentPane.add(textIme);
		textIme.setColumns(10);

		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);

		JLabel lblPrezime = new JLabel("Prezime:");
		contentPane.add(lblPrezime);

		textPrezime = new JTextField();
		contentPane.add(textPrezime);
		textPrezime.setColumns(10);

		JLabel label_2 = new JLabel("");
		contentPane.add(label_2);

		JLabel lblTelefon = new JLabel("Telefon:");
		contentPane.add(lblTelefon);

		textTelefon = new JTextField();
		contentPane.add(textTelefon);
		textTelefon.setColumns(10);

		JLabel label_3 = new JLabel("");
		contentPane.add(label_3);

		JLabel lblAdresa = new JLabel("Adresa:");
		contentPane.add(lblAdresa);

		textAdresa = new JTextField();
		contentPane.add(textAdresa);
		textAdresa.setColumns(10);

		JLabel label_4 = new JLabel("");
		contentPane.add(label_4);

		JLabel lblDatumRodjenja = new JLabel("Datum rodjenja:");
		contentPane.add(lblDatumRodjenja);

		textDatumRodjenja = new JTextField();
		contentPane.add(textDatumRodjenja);
		textDatumRodjenja.setColumns(10);

		JLabel label_5 = new JLabel("");
		contentPane.add(label_5);

		JLabel lblPol = new JLabel("Poslovnica:");
		contentPane.add(lblPol);

		List<String> comboBoxPoslovnice = new ArrayList<>();
		for (Poslovnica p : poslovnice) {
			comboBoxPoslovnice.add(p.getIdPoslovnica() + "- " + p.getMjesto().getNaziv());
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(comboBoxPoslovnice.toArray(new String[0]));
		JComboBox<String> comboBox = new JComboBox<>(model);
		contentPane.add(comboBox);

		JLabel labelDatum = new JLabel("");
		contentPane.add(labelDatum);

		JLabel lblPrimljendatum = new JLabel("Primljen(datum):");
		contentPane.add(lblPrimljendatum);

		textDatumPrijema = new JTextField();
		contentPane.add(textDatumPrijema);
		textDatumPrijema.setColumns(10);

		JLabel label_6 = new JLabel("");
		contentPane.add(label_6);

		JLabel lblPozicija = new JLabel("Pozicija:");
		contentPane.add(lblPozicija);

		pozicija = new JComboBox<>(new DefaultComboBoxModel(Pozicija.values()));
		contentPane.add(pozicija);

		btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
					String JMB = textJMB.getText();
					String ime = textIme.getText();
					String prezime = textPrezime.getText();
					String adresa = textAdresa.getText();
					String telefon = textTelefon.getText();
					Date datumRodjenja = sdf.parse(textDatumRodjenja.getText());
					Date datumPrijema = sdf.parse(textDatumPrijema.getText());
					int idPoslovnica = Integer.parseInt(comboBox.getSelectedItem().toString().split("-")[0].trim());
						if(Utilities.getDataAccessFactory().getZaposleniDataAcces().dodajZaposlenog(new Zaposleni(JMB, ime,
								prezime, adresa, telefon, datumRodjenja, datumPrijema, (Pozicija) pozicija.getSelectedItem()), idPoslovnica)) {
							JOptionPane.showMessageDialog(null, "Zaposleni uspjesno dodan.");
							ocistiPolja();
						} else {
							JOptionPane.showMessageDialog(null, "Zaposleni nije uspjesno dodan.");
						} 
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}

		});

		JLabel label_7 = new JLabel("");
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("");
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("");
		contentPane.add(label_9);
		contentPane.add(btnDodaj);
		
	}
	
	public void ocistiPolja() {
		textJMB.setText("");
		textIme.setText("");
		textPrezime.setText("");
		textAdresa.setText("");
		textTelefon.setText("");
		textDatumRodjenja.setText("");
		textDatumPrijema.setText("");
	}

}
