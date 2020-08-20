package org.unibl.etf.bp.store.entity;

public class Status {
	
	private int id;
	private String naziv;
	
	public Status(String naziv) {
		super();
		this.naziv = naziv;
	}
	
	public Status() {
		super();
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

}
