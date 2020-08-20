package org.unibl.etf.bp.store.entity;

public class Poslovnica {
	
	private int idPoslovnica;
	private String adresa;
	private String telefon;
	private Mjesto mjesto;
	
	public Poslovnica() {
		super();	
	}

	public Poslovnica(String adresa, String telefon, Mjesto mjesto) {
		super();
		this.adresa = adresa;
		this.telefon = telefon;
		this.mjesto = mjesto;
	}

	public Poslovnica(int idPoslovnica, String adresa, String telefon, Mjesto mjesto) {
		super();
		this.idPoslovnica = idPoslovnica;
		this.adresa = adresa;
		this.telefon = telefon;
		this.mjesto = mjesto;
	}

	public Poslovnica(int id, String adresa, String telefon) {
		super();
		this.idPoslovnica=id;
		this.adresa=adresa;
		this.telefon=telefon;
	}

	public int getIdPoslovnica() {
		return idPoslovnica;
	}

	public void setIdPoslovnica(int idPoslovnica) {
		this.idPoslovnica = idPoslovnica;
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

	public Mjesto getMjesto() {
		return mjesto;
	}
	
	public void setMjesto(Mjesto mjesto) {
		this.mjesto=mjesto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPoslovnica;
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
		Poslovnica other = (Poslovnica) obj;
		if (idPoslovnica != other.idPoslovnica)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Poslovnica [idPoslovnica=" + idPoslovnica + "]";
	}	

}
