package org.unibl.etf.bp.store.entity;

import java.util.Date;

public class Zaposleni extends Osoba {
	
	private Pozicija pozicija;
	private Date primljenURadniOdnos;
	private Date krajRadnogOdnosa;
	private KorisnickiNalog korisnickiNalog;

	public Zaposleni() {
		super();
	}
	
	public Zaposleni(String JMB, String ime, String prezime, Pozicija pozicija) {
		super(JMB, ime, prezime);
		this.pozicija=pozicija;
	}
	
	public Zaposleni(String JMB, String ime, String prezime, String adresa, String telefon, Date datumRodjenja, Date primljenURadniOdnos, Pozicija pozicija) {
		super(JMB, ime, prezime, adresa, telefon, datumRodjenja);
		this.primljenURadniOdnos=primljenURadniOdnos;
		this.pozicija=pozicija;
	}
	
	public Zaposleni(String JMB, String ime, String prezime, String adresa, String telefon, Date datumRodjenja, Pol pol, Date primljenURadniOdnos, Pozicija pozicija) {
		super(JMB, ime, prezime, adresa, telefon, datumRodjenja);
		this.primljenURadniOdnos=primljenURadniOdnos;
		this.pozicija=pozicija;
	}

	public Zaposleni(String jMB, String ime, String prezime, String adresa, String telefon, Date datumRodjenja,
			Pol pol) {
		super(jMB, ime, prezime, adresa, telefon, datumRodjenja, pol);
	}

	public Zaposleni(String jMB, String ime, String prezime, String adresa, String telefon, Date datumRodjenja,
			Pol pol, Date primljenURadniOdnos, KorisnickiNalog korisnickiNalog) {
		super(jMB, ime, prezime, adresa, telefon, datumRodjenja, pol);
		this.primljenURadniOdnos = primljenURadniOdnos;
		this.korisnickiNalog = korisnickiNalog;
	}

	public Zaposleni(String jMB, String ime, String prezime, String adresa, String telefon, Date datumRodjenja,
			Pol pol, Date datumPrijema) {
		super(jMB, ime, prezime, adresa, telefon, datumRodjenja, pol);
		this.primljenURadniOdnos=datumPrijema;
	}

	public Zaposleni(String JMB, String ime, String prezime) {
		super(JMB, ime, prezime);
	}

	public Date getDatumPrijema() {
		return primljenURadniOdnos;
	}

	public Date getPrimljenURadniOdnos() {
		return primljenURadniOdnos;
	}

	public void setPrimljenURadniOdnos(Date primljenURadniOdnos) {
		this.primljenURadniOdnos = primljenURadniOdnos;
	}

	public Date getKrajRadnogOdnosa() {
		return krajRadnogOdnosa;
	}

	public void setKrajRadnogOdnosa(Date krajRadnogOdnosa) {
		this.krajRadnogOdnosa = krajRadnogOdnosa;
	}

	public KorisnickiNalog getKorisnickiNalog() {
		return korisnickiNalog;
	}

	public void setKorisnickiNalog(KorisnickiNalog korisnickiNalog) {
		this.korisnickiNalog = korisnickiNalog;
	}

	public Pozicija getPozicija() {
		return pozicija;
	}

	public void setPozicija(Pozicija pozicija) {
		this.pozicija = pozicija;
	}

}
