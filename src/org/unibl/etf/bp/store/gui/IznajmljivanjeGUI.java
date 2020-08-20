package org.unibl.etf.bp.store.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.unibl.etf.bp.store.entity.Iznajmljivanje;
import org.unibl.etf.bp.store.entity.Proizvod;
import org.unibl.etf.bp.store.entity.StavkaIznajmljivanje;
import org.unibl.etf.bp.store.util.Utilities;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class IznajmljivanjeGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textKolicina;
	private JTable table;
	private JLabel lblUkupno;
	JComboBox<String> comboBoxKupci;

	private List<Proizvod> proizvodiUPoslovnici;
	private List<String> kupci;
	private JTextField textDatumDo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IznajmljivanjeGUI frame = new IznajmljivanjeGUI();
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
	public IznajmljivanjeGUI() {
		this.proizvodiUPoslovnici = Utilities.getDataAccessFactory().getProizvodUPoslovniciDataAccess()
				.proizvodiUPoslovnici(ZaposleniMain.poslovnica.getIdPoslovnica());
		this.kupci = Utilities.getDataAccessFactory().getKupacDataAccess().kupci();
		setTitle("Iznajmljivanje");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStavka = new JLabel("Stavka:");
		lblStavka.setBounds(12, 13, 56, 16);
		contentPane.add(lblStavka);

		JLabel lblSifra = new JLabel("Proizvod:");
		lblSifra.setBounds(12, 42, 56, 16);
		contentPane.add(lblSifra);

		List<String> comboBoxProizvodi = new ArrayList<>();
		for (Proizvod p : proizvodiUPoslovnici) {
			comboBoxProizvodi.add(p.getSifra() + "-" + p.getNaziv());
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(comboBoxProizvodi.toArray(new String[0]));
		JComboBox<String> proizvodi = new JComboBox<>(model);
		proizvodi.setBounds(108, 39, 367, 22);
		contentPane.add(proizvodi);

		JLabel lblKolicina = new JLabel("Kolicina:");
		lblKolicina.setBounds(504, 82, 56, 16);
		contentPane.add(lblKolicina);

		JButton btnIznajmi = new JButton("Iznajmi");
		btnIznajmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
					SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
					iznajmljivanje.setDatumIznajmljivnja(new Date());
					iznajmljivanje.setKupac(comboBoxKupci.getSelectedItem().toString().split("-")[0]);
					iznajmljivanje.setProdavac(ZaposleniMain.zaposleni);
					Date datumDo = sdf.parse(textDatumDo.getText());
					List<StavkaIznajmljivanje> stavke = new ArrayList<>();
					for (int i = 0; i < model.getRowCount(); i++) {
						for (Proizvod p : proizvodiUPoslovnici) {
							if (p.getSifra() == Integer.parseInt(model.getValueAt(i, 0).toString())) {
								stavke.add(new StavkaIznajmljivanje(ZaposleniMain.poslovnica.getIdPoslovnica(),
										p.getSifra(), new Date(), datumDo, Integer.parseInt(model.getValueAt(i, 2).toString()),
										Double.parseDouble(model.getValueAt(i, 3).toString())));
							}
						}
					}
					iznajmljivanje.setStavke(stavke);
					if(Utilities.getDataAccessFactory().getIznajmljivanjeDataAccess().dodajIznajmljivanje(iznajmljivanje)) {
						JOptionPane.showMessageDialog(null, "Iznajmljivanje uspjesno evidentirano");
						for(int i=model.getRowCount() - 1; i>=0; i--) {
							model.removeRow(i);
						}
						lblUkupno.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Iznajmljivanje nije uspjesno evidentirano");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		btnIznajmi.setBounds(673, 515, 97, 25);
		contentPane.add(btnIznajmi);

		JLabel lblUkupanIznos = new JLabel("Ukupan iznos:");
		lblUkupanIznos.setBounds(12, 444, 85, 16);
		contentPane.add(lblUkupanIznos);

		lblUkupno = new JLabel("");
		lblUkupno.setBounds(108, 444, 56, 16);
		contentPane.add(lblUkupno);

		textKolicina = new JTextField();
		textKolicina.setBounds(584, 79, 75, 22);
		contentPane.add(textKolicina);
		textKolicina.setColumns(10);

		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Utilities.tryParseInt(textKolicina.getText())) {
					int sifraProizvoda = Integer.parseInt(proizvodi.getSelectedItem().toString().split("-")[0]);
					Proizvod stavka = new Proizvod();
					for (Proizvod p : proizvodiUPoslovnici) {
						if (p.getSifra() == sifraProizvoda) {
							stavka = p;
							break;
						}
					}
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[] { stavka.getSifra(), stavka.getNaziv(), textKolicina.getText(),
							stavka.getProdajnaCijena(),
							Integer.parseInt(textKolicina.getText()) * stavka.getProdajnaCijena() });
					if (lblUkupno.getText() == "" || lblUkupno == null) {
						lblUkupno.setText(
								Double.toString(Integer.parseInt(textKolicina.getText()) * stavka.getProdajnaCijena()));
					} else {
						lblUkupno.setText(Double.toString(Double.parseDouble(lblUkupno.getText())
								+ Integer.parseInt(textKolicina.getText()) * stavka.getProdajnaCijena()));
					}
					textKolicina.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Kolicina nije validna!", "Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDodaj.setBounds(673, 78, 97, 25);
		contentPane.add(btnDodaj);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 125, 758, 306);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Sifra", "Naziv", "Kolicina", "Cijena", "Ukupno" });
		table.setModel(tableModel);

		JLabel lblKupac = new JLabel("Kupac:");
		lblKupac.setBounds(12, 82, 56, 16);
		contentPane.add(lblKupac);

		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>(kupci.toArray(new String[0]));
		comboBoxKupci = new JComboBox<>(model1);
		comboBoxKupci.setBounds(108, 79, 367, 22);
		contentPane.add(comboBoxKupci);

		JLabel lblDatumDo = new JLabel("Datum do:");
		lblDatumDo.setBounds(504, 42, 68, 16);
		contentPane.add(lblDatumDo);

		textDatumDo = new JTextField();
		textDatumDo.setBounds(584, 39, 116, 22);
		contentPane.add(textDatumDo);
		textDatumDo.setColumns(10);
	}
}
