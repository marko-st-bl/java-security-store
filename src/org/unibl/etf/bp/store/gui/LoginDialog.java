package org.unibl.etf.bp.store.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.store.entity.Zaposleni;
import org.unibl.etf.bp.store.util.Utilities;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textKorisnickoIme;
	private JPasswordField password;
	
	private boolean succeded = false;
	private Zaposleni zaposleni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		this(null);
		setUndecorated(true);
		setTitle("Prijavi se");
		setLocationRelativeTo(null);
	}
	
	public LoginDialog(JFrame frame) {
		super(frame,"Prijavi se",true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime:");
		lblKorisnickoIme.setBounds(70, 93, 87, 16);
		contentPanel.add(lblKorisnickoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setBounds(70, 122, 56, 16);
		contentPanel.add(lblLozinka);
		
		textKorisnickoIme = new JTextField();
		textKorisnickoIme.setBounds(169, 90, 116, 22);
		contentPanel.add(textKorisnickoIme);
		textKorisnickoIme.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(169, 122, 116, 22);
		contentPanel.add(password);
		
		JButton btnPrijaviSe = new JButton("Prijavi se");
		btnPrijaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String korisnickoIme = textKorisnickoIme.getText();
				String lozinka = new String(password.getPassword());
				zaposleni = Utilities.getDataAccessFactory().getZaposleniDataAcces().prijavi(korisnickoIme, lozinka);
				textKorisnickoIme.setText("");
				password.setText("");
				if(zaposleni == null) {
					JOptionPane.showMessageDialog(frame, "Nesupjesna prijava.");
					succeded = false;
				} else {
					succeded = true;
					dispose();
				}
			}
		});
		btnPrijaviSe.setBounds(169, 157, 116, 25);
		contentPanel.add(btnPrijaviSe);
	}
	
	public boolean isSucceded() {
		return succeded;
	}
	
	public Zaposleni getZaposleni() {
		return this.zaposleni;
	}
}
