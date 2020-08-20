package org.unibl.etf.bp.store.entity;

public class Dobavljac {
	
	private int id;
	private String naziv;
	private String adresa;
	private Mjesto mjesto;

	public Dobavljac(int id, String naziv, String adresa, Mjesto mjesto) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.mjesto = mjesto;
	}
	
	public Dobavljac(String naziv, String adresa, Mjesto mjesto) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.mjesto = mjesto;
	}
	public Dobavljac() {
		super();
	}
	
	public Dobavljac(int id, String naziv, String adresa) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa=adresa;
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
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Mjesto getMjesto() {
		return mjesto;
	}
	public void setMjesto(Mjesto mjesto) {
		this.mjesto = mjesto;
	}
	
}
