package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Proizvod;

public interface ProizvodUPoslovniciDataAccess {
	
	public List<Proizvod> proizvodiUPoslovnici(int idPoslovnica);
	public boolean dodajProizvodUPoslovnicu(int sifra, int idPoslovnica);
	public boolean obrisiProizvodUPoslovnici(int sifra, int idPoslovnica);
	

}
