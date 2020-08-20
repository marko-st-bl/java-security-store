package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Proizvod;

public interface ProizvodDataAccess {
	
	Proizvod proizvod(int sifra);
	List<Proizvod> proizvodi();
	boolean dodajProizvod(Proizvod proizvod);
	boolean azurirajProivod(Proizvod proizvod);
	boolean obrisiProizvod(int sifra);
	List<String> kategorije();
}
