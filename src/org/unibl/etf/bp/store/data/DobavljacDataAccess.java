package org.unibl.etf.bp.store.data;

import java.util.List;

import org.unibl.etf.bp.store.entity.Dobavljac;

public interface DobavljacDataAccess {
	
	public List<Dobavljac> dobavljaci();
	public Dobavljac getDobavljac(int idDobavljac);

}
