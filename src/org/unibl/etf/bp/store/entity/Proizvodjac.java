package org.unibl.etf.bp.store.entity;

public class Proizvodjac {
	
	private int id;
	private String naziv;
	
	public Proizvodjac() {
		super();
	}
	
	public Proizvodjac(String naziv) {
		super();
		this.naziv=naziv;
	}

	public Proizvodjac(int id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Proizvodjac other = (Proizvodjac) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return naziv;
	}
	
	
	
	
	
	

}
