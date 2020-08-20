package org.unibl.etf.bp.store.entity;

public class Mjesto {
	
	private int posta;
	private String naziv;
	
	public Mjesto() {
		super();
	}
	public Mjesto(int posta, String naziv) {
		super();
		this.posta = posta;
		this.naziv = naziv;
	}
	
	public int getPosta() {
		return posta;
	}
	public void setPosta(int posta) {
		this.posta = posta;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
