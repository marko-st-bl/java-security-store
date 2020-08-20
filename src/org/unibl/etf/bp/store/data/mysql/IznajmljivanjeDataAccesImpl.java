package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.unibl.etf.bp.store.data.IznajmljivanjeDataAccess;
import org.unibl.etf.bp.store.entity.Iznajmljivanje;
import org.unibl.etf.bp.store.entity.StavkaIznajmljivanje;

public class IznajmljivanjeDataAccesImpl implements IznajmljivanjeDataAccess{

	@Override
	public List<Iznajmljivanje> iznajmljivanja() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dodajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		
		String query="INSERT INTO iznajmljivanje (JMBKupac, JMBProdavac, DatumIznajmljivanja) VALUES (?, ?, ?)";
		String query1="INSERT INTO stavka_iznajmljivanje (IdIznajmljivanja, Sifra, IdPoslovnica, DatumOd, DatumDo, Kolicina, Cijena) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, iznajmljivanje.getKupac());
			ps.setString(2, iznajmljivanje.getProdavac().getJMB());
			ps.setDate(3, new java.sql.Date(iznajmljivanje.getDatumIznajmljivanja().getTime()));
			retVal = ps.executeUpdate() == 1;
			rs = ps.getGeneratedKeys();
			rs.next();
			ps1 = conn.prepareStatement(query1);
			for(StavkaIznajmljivanje si:iznajmljivanje.getStavke()) {
				ps1.setInt(1, rs.getInt(1));
				ps1.setInt(2, si.getSifra());
				ps1.setInt(3, si.getIdPoslovnica());
				ps1.setDate(4, new java.sql.Date(si.getDatumOd().getTime()));
				ps1.setDate(5, new java.sql.Date(si.getDatumDo().getTime()));
				ps1.setInt(6, si.getKolicina());
				ps1.setDouble(7, si.getCijena());
				ps1.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
			MySQLUtilities.getInstance().close(ps1);
		}
		return retVal;
	}

	@Override
	public boolean azurirajIznamljivanje(Iznajmljivanje iznajmljivanje) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean obrisiIznajmljivanje(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
