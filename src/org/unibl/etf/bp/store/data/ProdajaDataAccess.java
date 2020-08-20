package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Prodaja;

public interface ProdajaDataAccess {
	
	public List<Prodaja> prodaje();
	public boolean dodajProdaju(Prodaja prodaja);
	public boolean azurirajProdaju(Prodaja prodaja);
	public boolean obrisiProdaju(int id);

}
