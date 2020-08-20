package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Proizvodjac;

public interface ProizvodjacDataAccess {
	
	public Proizvodjac getProizvodjacById(int id);
	public List<Proizvodjac> proizvodjaci();
	public boolean dodajProivodjaca(Proizvodjac proizvodjac);
	public boolean obrisiProizvodjaca(int id);
	public boolean azurirajProizvodjaca(Proizvodjac proivodjac);
}
