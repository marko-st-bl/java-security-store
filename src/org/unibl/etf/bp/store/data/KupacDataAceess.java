package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Osoba;

public interface KupacDataAceess {
	
	public List<String> kupci();
	public boolean dodajKupca(Osoba o);
	public boolean obrisiKupca(Osoba o);
	public boolean azurirajKupca(Osoba o);
	

}
