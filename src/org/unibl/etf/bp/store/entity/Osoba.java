package org.unibl.etf.bp.store.entity;

import java.util.Date;

public class Osoba {
	
	private String JMB;
	private String ime;
	private String prezime;
	private String adresa;
	private String telefon;
	private Date datumRodjenja;
	private Pol pol;
	
	public Osoba() {
		super();
	}


	public Osoba(String JMB, String ime, String prezime, String adresa, String telefon, Date datumRodjenja,
			Pol pol) {
		this.datumRodjenja=datumRodjenja;
		this.JMB = JMB;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.telefon = telefon;
		this.pol = pol;
	}

	public Osoba(String JMB, String ime, String prezime) {
		this.JMB=JMB;
		this.ime=ime;
		this.prezime=prezime;
	}


	public Osoba(String JMB, String ime, String prezime, String adresa, String telefon, Date datumRodjenja) {
		this.JMB = JMB;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.telefon = telefon;
		this.datumRodjenja=datumRodjenja;
	}


	public String getJMB() {
		return JMB;
	}

	public void setJMB(String jMB) {
		JMB = jMB;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((JMB == null) ? 0 : JMB.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Osoba other = (Osoba) obj;
		if (JMB == null) {
			if (other.JMB != null)
				return false;
		} else if (!JMB.equals(other.JMB))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ime+" "+ prezime;
	}
	
}
