package org.unibl.etf.bp.store.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.unibl.etf.bp.store.util.Utilities;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PlateIzvjestajGUI extends JFrame {
	
	private Vector<Object> kolone;
	private Vector<Vector<Object>> podaci;

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlateIzvjestajGUI frame = new PlateIzvjestajGUI();
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
	public PlateIzvjestajGUI() {
		setTitle("Izvjestaj o plati");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		podaci = Utilities.getDataAccessFactory().getIzvjestajiDataAccess().plate();
		
		kolone = new Vector<Object>();
		kolone.add("JMB");
		kolone.add("Ime i prezime");
		kolone.add("Bruto");
		kolone.add("PIO");
		kolone.add("DJ");
		kolone.add("NZP");
		kolone.add("Ukupan porez");
		kolone.add("Neto");
		
		table = new JTable(new DefaultTableModel(podaci, kolone) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

}
