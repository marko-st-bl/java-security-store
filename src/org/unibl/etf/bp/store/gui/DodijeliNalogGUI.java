package org.unibl.etf.bp.store.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.unibl.etf.bp.store.entity.Zaposleni;
import org.unibl.etf.bp.store.util.Utilities;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DodijeliNalogGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<Zaposleni> zaposleniBezNaloga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodijeliNalogGUI frame = new DodijeliNalogGUI();
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
	public DodijeliNalogGUI() {
		zaposleniBezNaloga = Utilities.getDataAccessFactory().getZaposleniDataAcces().zaposleniBezNaloga();
		if(zaposleniBezNaloga.size() == 0) {
			JOptionPane.showMessageDialog(null, "Nema zaposlenih bez naloga!");
			dispose();
		}
		setBounds(100, 100, 800, 600);
		setTitle("Dodijeli nalog zaposlenom");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 758, 367);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "JMB", "Ime", "Prezime", "Pozcija"}));
		
		for(Zaposleni z:zaposleniBezNaloga) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[]{z.getJMB(), z.getIme(), z.getPrezime(), z.getPozicija()});
		}
		
		JButton btnDodijeli = new JButton("Dodijeli");
		btnDodijeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(Utilities.getDataAccessFactory().getZaposleniDataAcces().dodijeliNalogZaposlenom(zaposleniBezNaloga.get(i))) {
					JOptionPane.showMessageDialog(null, "Nalog uspjesno dodijeljen!");
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(i);
				} else {
					JOptionPane.showMessageDialog(null, "Nalog nije uspjesno dodijeljen!");
				}
			}
		});
		btnDodijeli.setBounds(5, 523, 772, 25);
		contentPane.add(btnDodijeli);
		
		JLabel lblRadniciBezNaloga = new JLabel("Radnici bez naloga:");
		lblRadniciBezNaloga.setBounds(12, 35, 121, 16);
		contentPane.add(lblRadniciBezNaloga);
	}
}
