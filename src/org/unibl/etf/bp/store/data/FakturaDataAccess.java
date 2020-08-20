package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Faktura;

public interface FakturaDataAccess {
	
	public boolean dodajFakturu(Faktura faktura, String kategorija);
	public boolean dodajFakturuZaPostojeciProizvod(Faktura faktura);
	public List<Faktura> fakture();
	public List<Faktura> getFakturaBySifraProizvoda(int sifra);

}
