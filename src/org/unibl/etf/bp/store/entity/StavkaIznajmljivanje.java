package org.unibl.etf.bp.store.entity;

import java.util.Date;

public class StavkaIznajmljivanje {
	
	private int idIznajmljivanja;
	private int idPoslovnica;
	private int sifra;
	private Date datumOd;
	private Date datumDo;
	private int kolicina;
	private double cijena;
	
	public StavkaIznajmljivanje(int idIznajmljivanja, int idPoslovnica, int sifra, Date datumOd, Date datumDo,
			int kolicina, double cijena) {
		super();
		this.idIznajmljivanja = idIznajmljivanja;
		this.idPoslovnica = idPoslovnica;
		this.sifra = sifra;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.kolicina = kolicina;
		this.cijena = cijena;
	}
	
	public StavkaIznajmljivanje(int idPoslovnica, int sifra, Date datumOd, Date datumDo,
			int kolicina, double cijena) {
		super();
		this.idPoslovnica = idPoslovnica;
		this.sifra = sifra;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.kolicina = kolicina;
		this.cijena = cijena;
	}

	public int getIdIznajmljivanja() {
		return idIznajmljivanja;
	}

	public void setIdIznajmljivanja(int idIznajmljivanja) {
		this.idIznajmljivanja = idIznajmljivanja;
	}

	public int getIdPoslovnica() {
		return idPoslovnica;
	}

	public void setIdPoslovnica(int idPoslovnica) {
		this.idPoslovnica = idPoslovnica;
	}

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	public Date getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	public Date getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	
}
