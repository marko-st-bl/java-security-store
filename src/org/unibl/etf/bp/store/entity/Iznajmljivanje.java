package org.unibl.etf.bp.store.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Iznajmljivanje {
	
	private int id;
	private Zaposleni prodavac;
	private String JMBKupac;
	private Date datumIznajmljivanja;
	private List<StavkaIznajmljivanje> stavke = new ArrayList<>();
	
	public Iznajmljivanje(int id, Zaposleni prodavac, String kupac, Date datumIznajmljivnja) {
		super();
		this.id = id;
		this.prodavac = prodavac;
		this.JMBKupac = kupac;
		this.datumIznajmljivanja = datumIznajmljivnja;
	}
	public Iznajmljivanje() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDatumIznajmljivnja() {
		return datumIznajmljivanja;
	}
	public void setDatumIznajmljivnja(Date datumIznajmljivnja) {
		this.datumIznajmljivanja = datumIznajmljivnja;
	}
	public Zaposleni getProdavac() {
		return prodavac;
	}
	public void setProdavac(Zaposleni prodavac) {
		this.prodavac = prodavac;
	}
	public String getKupac() {
		return JMBKupac;
	}
	public void setKupac(String kupac) {
		this.JMBKupac = kupac;
	}
	public Date getDatumIznajmljivanja() {
		return datumIznajmljivanja;
	}
	public void setDatumIznajmljivanja(Date datumIznajmljivanja) {
		this.datumIznajmljivanja = datumIznajmljivanja;
	}
	public List<StavkaIznajmljivanje> getStavke() {
		return stavke;
	}
	public void setStavke(List<StavkaIznajmljivanje> stavke) {
		this.stavke = stavke;
	}
	public void addStavka(StavkaIznajmljivanje stavka) {
		this.stavke.add(stavka);
	}
	
}
