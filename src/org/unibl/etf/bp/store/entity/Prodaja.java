package org.unibl.etf.bp.store.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prodaja {
	
	private String id;
	private Zaposleni prodavac;
	private Kupac kupac;
	private Date datumProdaje;
	private List<StavkaProdaja> stavke = new ArrayList<>();
	
	public Prodaja(String id, Zaposleni prodavac, Kupac kupac, Date datumProdaje) {
		super();
		this.id = id;
		this.prodavac = prodavac;
		this.kupac = kupac;
		this.datumProdaje = datumProdaje;
	}
	
	public Prodaja() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Zaposleni getProdavac() {
		return prodavac;
	}
	public void setProdavac(Zaposleni prodavac) {
		this.prodavac=prodavac;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Date getDatumProdaje() {
		return datumProdaje;
	}

	public void setDatumProdaje(Date datumProdaje) {
		this.datumProdaje = datumProdaje;
	}

	public List<StavkaProdaja> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaProdaja> stavke) {
		this.stavke = stavke;
	}
	
	public void dodajStavku(StavkaProdaja stavka) {
		this.stavke.add(stavka);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Prodaja other = (Prodaja) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prodaja [id=" + id + "]";
	}

}
