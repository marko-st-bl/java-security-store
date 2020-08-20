package org.unibl.etf.bp.store.entity;

public class StavkaProdaja {
	
	private Proizvod proizvod;
	private Poslovnica poslovnica;
	private int kolicina;
	private double cijena;
	
	public StavkaProdaja(Proizvod proizvod, Poslovnica poslovnica, int kolicina, double cijena) {
		super();
		this.proizvod = proizvod;
		this.poslovnica = poslovnica;
		this.kolicina = kolicina;
		this.cijena = cijena;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public Poslovnica getPoslovnica() {
		return poslovnica;
	}

	public void setPoslovnica(Poslovnica poslovnica) {
		this.poslovnica = poslovnica;
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
