package org.unibl.etf.bp.store.data;

import org.unibl.etf.bp.store.entity.Osoba;

public interface  OsobaDataAccess {
	
	public boolean dodajOsobu(Osoba o);
	public boolean azurirajOsobu(Osoba o);
	public boolean obrisiOsobu(String JMB);
	

}
