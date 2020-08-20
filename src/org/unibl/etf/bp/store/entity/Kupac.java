package org.unibl.etf.bp.store.entity;

import java.util.Date;

public class Kupac extends Osoba{

	public Kupac() {
		super();
	}

	public Kupac(String JMB, String ime, String prezime, String adresa, String telefon, Date datumRodjenja, Pol pol) {
		super(JMB, ime, prezime, adresa, telefon, datumRodjenja, pol);
	}
	
	
}
