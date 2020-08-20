package org.unibl.etf.bp.store.entity;

public class KategorijaProizvoda {
	
	private int id;
	private String naziv;
	
	public KategorijaProizvoda() {
		super();
	}
	public KategorijaProizvoda(int id, String naziv) {
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
	

}
