package org.unibl.etf.bp.store.entity;

import java.util.Date;

public class Faktura {
	
	private int idFaktura;
	private Proizvod proizvod;
	private Dobavljac dobavljac;
	private double nabavnaCijena;
	private int kolicna;
	private Date datumNabavke;

	public Faktura(int idFaktura, Proizvod proizvod, Dobavljac dobavljac, double nabavnaCijena, int kolicna,
			Date datumNabavke) {
		super();
		this.idFaktura = idFaktura;
		this.proizvod = proizvod;
		this.dobavljac = dobavljac;
		this.nabavnaCijena = nabavnaCijena;
		this.kolicna = kolicna;
		this.datumNabavke = datumNabavke;
	}
	
	public Faktura() {
		super();
	}

	public Faktura(Dobavljac dobavljac, Proizvod proizvod, double nabavnaCijena, int kolicina) {
		this.dobavljac = dobavljac;
		this.proizvod = proizvod;
		this.nabavnaCijena = nabavnaCijena;
		this.kolicna = kolicina;
	}

	public int getIdFaktura() {
		return idFaktura;
	}

	public void setIdFaktura(int idFaktura) {
		this.idFaktura = idFaktura;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public Dobavljac getDobavljac() {
		return dobavljac;
	}

	public void setDobavljac(Dobavljac dobavljac) {
		this.dobavljac = dobavljac;
	}

	public double getNabavnaCijena() {
		return nabavnaCijena;
	}

	public void setNabavnaCijena(double nabavnaCijena) {
		this.nabavnaCijena = nabavnaCijena;
	}

	public int getKolicna() {
		return kolicna;
	}

	public void setKolicna(int kolicna) {
		this.kolicna = kolicna;
	}

	public Date getDatumNabavke() {
		return datumNabavke;
	}

	public void setDatumNabavke(Date datumNabavke) {
		this.datumNabavke = datumNabavke;
	}
	
}
