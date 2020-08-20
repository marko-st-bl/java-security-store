package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Iznajmljivanje;

public interface IznajmljivanjeDataAccess {
	
	public List<Iznajmljivanje> iznajmljivanja();
	public boolean dodajIznajmljivanje(Iznajmljivanje iznajmljivanje);
	public boolean azurirajIznamljivanje(Iznajmljivanje iznajmljivanje);
	public boolean obrisiIznajmljivanje(int id);
}
