package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Poslovnica;
import org.unibl.etf.bp.store.entity.Proizvod;

public interface PoslovnicaDataAccess {
	
	public List<Poslovnica> poslovnice();
	public List<Proizvod> proizvodiUPoslovnici(int idPoslovnica);
	public Poslovnica poslovnicaZaposlenog(String JMB);
	public boolean dodajPoslovnicu(Poslovnica poslovnica);
	public boolean obrisiPoslovnicu(int idPoslovnica);
	public boolean azurirajPoslovnicu(Poslovnica poslovnica);

}
