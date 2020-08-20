package org.unibl.etf.bp.store.data;

import org.unibl.etf.bp.store.data.mysql.MySQLDataAccessFactory;

public abstract class DataAccessFactory {
	
	public abstract ProizvodDataAccess getProizvodDataAccess();
	public abstract ProizvodjacDataAccess getProizvodjacDataAccess();
	public abstract ProizvodUPoslovniciDataAccess getProizvodUPoslovniciDataAccess();
	public abstract ProdajaDataAccess getProdajaDataAccess();
	public abstract PoslovnicaDataAccess getPoslovnicaDataAcces();
	public abstract IznajmljivanjeDataAccess getIznajmljivanjeDataAccess();
	public abstract ZaposleniDataAccess getZaposleniDataAcces();
	public abstract IzvjestajiDataAccess getIzvjestajiDataAccess();
	public abstract KorisnickiNalogDataAccess getKorisnickiNalogDataAccess();
	public abstract KategorijaProizvodaDataAccess getKategorijaProizvodaDataAccess();
	public abstract FakturaDataAccess getFakturaDataAccess();
	public abstract DobavljacDataAccess getDobavljacDataAccess();
	public abstract KupacDataAceess getKupacDataAccess();
	
	public static DataAccessFactory getFactory(DataAccessFactoryType type) {
		if (DataAccessFactoryType.MySQL.equals(type)) {
			return new MySQLDataAccessFactory();
		}
		throw new IllegalArgumentException();
	}

}
