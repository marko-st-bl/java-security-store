package org.unibl.etf.bp.store.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.unibl.etf.bp.store.entity.Prodaja;
import org.unibl.etf.bp.store.entity.Proizvod;
import org.unibl.etf.bp.store.entity.StavkaProdaja;
import org.unibl.etf.bp.store.util.Utilities;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ProdajaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textKolicina;
	private JTable table;
	private JLabel lblUkupno;
	private List<Proizvod> proizvodiUPoslovnici;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdajaGUI frame = new ProdajaGUI();
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
	public ProdajaGUI() {
		this.proizvodiUPoslovnici = Utilities.getDataAccessFactory().getProizvodUPoslovniciDataAccess()
				.proizvodiUPoslovnici(ZaposleniMain.poslovnica.getIdPoslovnica());
		setTitle("Prodaja");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStavka = new JLabel("Stavka:");
		lblStavka.setBounds(12, 13, 56, 16);
		contentPane.add(lblStavka);

		JLabel lblProizvod = new JLabel("Proizvod:");
		lblProizvod.setBounds(12, 42, 61, 16);
		contentPane.add(lblProizvod);

		List<String> comboBoxProizvodi = new ArrayList<>();
		for (Proizvod p : proizvodiUPoslovnici) {
			comboBoxProizvodi.add(p.getSifra() + "- " + p.getNaziv());
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(comboBoxProizvodi.toArray(new String[0]));
		JComboBox<String> proizvodi = new JComboBox<>(model);
		proizvodi.setBounds(108, 39, 367, 22);
		contentPane.add(proizvodi);

		JLabel lblKolicina = new JLabel("Kolicina:");
		lblKolicina.setBounds(513, 42, 56, 16);
		contentPane.add(lblKolicina);

		textKolicina = new JTextField();
		textKolicina.setBounds(581, 39, 80, 22);
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
		btnDodaj.setBounds(673, 38, 97, 25);
		contentPane.add(btnDodaj);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 758, 343);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Sifra", "Naziv", "Kolicina", "Cijena", "Ukupno" });
		table.setModel(tableModel);

		JButton btnProdaj = new JButton("Prodaj");
		btnProdaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prodaja prodaja = new Prodaja();
				prodaja.setDatumProdaje(new Date());
				prodaja.setProdavac(ZaposleniMain.zaposleni);
				List<StavkaProdaja> stavke = new ArrayList<>();
				for(int i=0; i<tableModel.getRowCount(); i++) {
					for(Proizvod p:proizvodiUPoslovnici) {
						if(p.getSifra() == Integer.parseInt(tableModel.getValueAt(i, 0).toString())) {
							stavke.add(new StavkaProdaja(p, ZaposleniMain.poslovnica, Integer.parseInt(tableModel.getValueAt(i, 2).toString()),
									Double.parseDouble(tableModel.getValueAt(i, 3).toString())));
						}
					}
				}
				prodaja.setStavke(stavke);
				if(Utilities.getDataAccessFactory().getProdajaDataAccess().dodajProdaju(prodaja)) {
					JOptionPane.showMessageDialog(null, "Prodaja uspjesno evidentirana");
					for(int i=tableModel.getRowCount() - 1; i>=0; i--) {
						tableModel.removeRow(i);
					}
					lblUkupno.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Prodaja nije uspjesno evidentirana");
				}
			}
		});
		btnProdaj.setBounds(673, 515, 97, 25);
		contentPane.add(btnProdaj);

		JLabel lblUkupanIznos = new JLabel("Ukupan iznos:");
		lblUkupanIznos.setBounds(12, 444, 85, 16);
		contentPane.add(lblUkupanIznos);

		lblUkupno = new JLabel("");
		lblUkupno.setBounds(108, 444, 56, 16);
		contentPane.add(lblUkupno);
	}
}
