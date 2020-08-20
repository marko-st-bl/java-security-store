package org.unibl.etf.bp.store.entity;


public class Proizvod {
	
	private int sifra;
	private String naziv;
	private String opis;
	private int kolicinaNaStanju;
	private double prodajnaCijena;
	private Proizvodjac proizvodjac;
	
	public Proizvod() {
		super();
	}

	public Proizvod(int sifra, String naziv, String opis, int kolicinaNaStanju, double prodajnaCijena,
			Proizvodjac proizvodjac) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.opis = opis;
		this.kolicinaNaStanju = kolicinaNaStanju;
		this.prodajnaCijena = prodajnaCijena;
		this.proizvodjac = proizvodjac;
	}
	
	public Proizvod(String naziv, String opis, int kolicina, double prodajnaCijena, Proizvodjac proizvodjac) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.kolicinaNaStanju = kolicina;
		this.prodajnaCijena = prodajnaCijena;
		this.proizvodjac = proizvodjac;
	}

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getKolicinaNaStanju() {
		return kolicinaNaStanju;
	}

	public void setKolicinaNaStanju(int kolicinaNaStanju) {
		this.kolicinaNaStanju = kolicinaNaStanju;
	}

	public double getProdajnaCijena() {
		return prodajnaCijena;
	}

	public void setProdajnaCijena(double prodajnaCijena) {
		this.prodajnaCijena = prodajnaCijena;
	}

	public Proizvodjac getProizvodjac() {
		return proizvodjac;
	}

	public void setIdProizvodjaca(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sifra;
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
		Proizvod other = (Proizvod) obj;
		if (sifra != other.sifra)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return naziv;
	}
	

}
