package org.unibl.etf.bp.store.data;

import java.util.Vector;

public interface IzvjestajiDataAccess {
	
	public Vector<Vector<Object>> ukupanPromet();
	public Vector<Vector<Object>> ukupanProdaja();
	public Vector<Vector<Object>> ukupanoIznajmljivanje();
	public Vector<Vector<Object>> plate();

}
