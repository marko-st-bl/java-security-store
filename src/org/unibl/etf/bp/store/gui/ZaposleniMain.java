package org.unibl.etf.bp.store.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.store.entity.Poslovnica;
import org.unibl.etf.bp.store.entity.Zaposleni;
import org.unibl.etf.bp.store.util.Utilities;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Font;

@SuppressWarnings("serial")
public class ZaposleniMain extends JFrame {
	
	public static Zaposleni zaposleni;
	public static Poslovnica poslovnica;

	private JPanel contentPane;
	
	private ProdajaGUI prodaja;
	private IznajmljivanjeGUI iznajmljivanje;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZaposleniMain frame = new ZaposleniMain();
					LoginDialog login = new LoginDialog(frame);
					login.setVisible(true);
					if(login.isSucceded()) {
						zaposleni = login.getZaposleni();
						poslovnica = Utilities.getDataAccessFactory().getPoslovnicaDataAcces().poslovnicaZaposlenog(zaposleni.getJMB());
						frame.setTitle("Security Store - Zaposleni: " + zaposleni.getIme() + " "
								+ zaposleni.getPrezime());
						login.dispose();
						frame.setVisible(true);
					} else {
						frame.dispose();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ZaposleniMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnProdaja = new JButton("Prodaja");
		btnProdaja.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProdaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(prodaja == null) {
					prodaja = new ProdajaGUI();
				}
				prodaja.setVisible(true);
			}
		});
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		contentPane.add(btnProdaja);
		
		JButton btnIznajmljivanje = new JButton("Iznajmljivanje");
		btnIznajmljivanje.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnIznajmljivanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(iznajmljivanje == null) {
					iznajmljivanje = new IznajmljivanjeGUI();
				}
				iznajmljivanje.setVisible(true);
			}
		});
		contentPane.add(btnIznajmljivanje);
	}
	
}
