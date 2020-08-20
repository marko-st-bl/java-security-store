package org.unibl.etf.bp.store.data.mysql;

import org.unibl.etf.bp.store.data.DataAccessFactory;
import org.unibl.etf.bp.store.data.DobavljacDataAccess;
import org.unibl.etf.bp.store.data.FakturaDataAccess;
import org.unibl.etf.bp.store.data.IznajmljivanjeDataAccess;
import org.unibl.etf.bp.store.data.IzvjestajiDataAccess;
import org.unibl.etf.bp.store.data.KategorijaProizvodaDataAccess;
import org.unibl.etf.bp.store.data.KorisnickiNalogDataAccess;
import org.unibl.etf.bp.store.data.KupacDataAceess;
import org.unibl.etf.bp.store.data.PoslovnicaDataAccess;
import org.unibl.etf.bp.store.data.ProdajaDataAccess;
import org.unibl.etf.bp.store.data.ProizvodDataAccess;
import org.unibl.etf.bp.store.data.ProizvodUPoslovniciDataAccess;
import org.unibl.etf.bp.store.data.ProizvodjacDataAccess;
import org.unibl.etf.bp.store.data.ZaposleniDataAccess;

public class MySQLDataAccessFactory extends DataAccessFactory{

	@Override
	public ProizvodDataAccess getProizvodDataAccess() {
		return new ProizvodDataAccessImpl();
	}

	@Override
	public ProizvodjacDataAccess getProizvodjacDataAccess() {
		return new ProizvodjacDataAccessImpl();
	}

	@Override
	public ProizvodUPoslovniciDataAccess getProizvodUPoslovniciDataAccess() {
		
		return new ProizvodUPoslovniciDataAccessImpl();
	}

	@Override
	public ProdajaDataAccess getProdajaDataAccess() {
		return new ProdajaDataAccessImpl();
	}

	@Override
	public PoslovnicaDataAccess getPoslovnicaDataAcces() {
		return new PoslovnicaDataAccessImpl();
	}

	@Override
	public IznajmljivanjeDataAccess getIznajmljivanjeDataAccess() {
		return new IznajmljivanjeDataAccesImpl();
	}


	@Override
	public ZaposleniDataAccess getZaposleniDataAcces() {	
		return new ZaposleniDataAccessImpl();
	}

	@Override
	public IzvjestajiDataAccess getIzvjestajiDataAccess() {
		return new IzvjestajiDataAccessImpl();
	}

	@Override
	public KorisnickiNalogDataAccess getKorisnickiNalogDataAccess() {
		return new KorisnickiNalogDataAccessImpl();
	}

	@Override
	public KategorijaProizvodaDataAccess getKategorijaProizvodaDataAccess() {
		return new KategorijaProizvodaDataAccessImpl();
	}

	@Override
	public FakturaDataAccess getFakturaDataAccess() {
		return new FakturaDataAccessImpl();
	}

	@Override
	public DobavljacDataAccess getDobavljacDataAccess() {
		return new DobavljacDataAccessImpl();
	}

	@Override
	public KupacDataAceess getKupacDataAccess() {
		return new KupacDataAccessImpl();
	}


}
