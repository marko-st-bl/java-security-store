package org.unibl.etf.bp.store.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MenadzerMain extends JFrame {

	private JPanel contentPane;
	private DodajZaposlenog dodajZaposlenog;
	private DodajProizvodForm dodajProizvod;
	private DodijeliNalogGUI dodijeliNalog;
	private UkupanPrometIzvjestajGUI izvjestajUkupanPromet;
	private UkupanaProdajaIzvjestajGUI izvjestajProdaja;
	private UkupnoIznajmljivanjeIzvjestajGUI izvjestajIznajmljivanje;
	private PlateIzvjestajGUI izvjestajPlate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenadzerMain frame = new MenadzerMain();
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
	public MenadzerMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setTitle("Security Store- Master");
		
		dodajZaposlenog=new DodajZaposlenog();
		dodajProizvod=new DodajProizvodForm();
		izvjestajUkupanPromet = new UkupanPrometIzvjestajGUI();
		izvjestajPlate = new PlateIzvjestajGUI();
		izvjestajProdaja = new UkupanaProdajaIzvjestajGUI();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAplikacija = new JMenu("Aplikacija");
		menuBar.add(mnAplikacija);
		
		JMenuItem mntmKraj = new JMenuItem("Kraj");
		mnAplikacija.add(mntmKraj);
		
		JMenu mnZaposleni = new JMenu("Zaposleni");
		menuBar.add(mnZaposleni);
		
		JMenuItem mntmDodajZaposlenog = new JMenuItem("Dodaj Zaposlenog");
		mntmDodajZaposlenog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajZaposlenog.setVisible(true);
			}
		});
		mnZaposleni.add(mntmDodajZaposlenog);
		
		JMenuItem mntmDodijeliNalog = new JMenuItem("Dodijeli nalog");
		mntmDodijeliNalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dodijeliNalog == null) {
					dodijeliNalog= new DodijeliNalogGUI();
				}
				dodijeliNalog.setVisible(true);
			}
		});
		mnZaposleni.add(mntmDodijeliNalog);
		
		JMenu mnProizvodi = new JMenu("Proizvodi");
		menuBar.add(mnProizvodi);
		
		JMenuItem mntmDodajProizvod = new JMenuItem("Dodaj Proizvod");
		mntmDodajProizvod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajProizvod.setVisible(true);
			}
		});
		mnProizvodi.add(mntmDodajProizvod);
		
		JMenu mnIzvjestaji = new JMenu("Izvjestaji");
		menuBar.add(mnIzvjestaji);
		
		JMenuItem mntmUkupanPrometPo = new JMenuItem("Ukupna prodaja po poslovnici");
		mntmUkupanPrometPo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				izvjestajProdaja.setVisible(true);
			}
		});
		mnIzvjestaji.add(mntmUkupanPrometPo);
		
		JMenuItem mntmPlate = new JMenuItem("Plate");
		mntmPlate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(izvjestajPlate == null) {
					izvjestajPlate = new PlateIzvjestajGUI();
				}
				izvjestajPlate.setVisible(true);
			}
		});
		
		JMenuItem mntmUkupnoIznajmljivanjePo = new JMenuItem("Ukupno iznajmljivanje po poslovnici");
		mntmUkupnoIznajmljivanjePo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(izvjestajIznajmljivanje == null) {
					izvjestajIznajmljivanje = new UkupnoIznajmljivanjeIzvjestajGUI();
				}
				izvjestajIznajmljivanje.setVisible(true);
			}
		});
		mnIzvjestaji.add(mntmUkupnoIznajmljivanjePo);
		
		JMenuItem mntmUkupanPrometPo_1 = new JMenuItem("Ukupan promet po poslovnici");
		mntmUkupanPrometPo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(izvjestajUkupanPromet == null) {
					izvjestajUkupanPromet = new UkupanPrometIzvjestajGUI();
				}
				izvjestajUkupanPromet.setVisible(true);
			}
		});
		mnIzvjestaji.add(mntmUkupanPrometPo_1);
		mnIzvjestaji.add(mntmPlate);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
