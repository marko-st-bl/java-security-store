package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.store.data.ProizvodUPoslovniciDataAccess;
import org.unibl.etf.bp.store.entity.Proizvod;
import org.unibl.etf.bp.store.util.Utilities;

public class ProizvodUPoslovniciDataAccessImpl implements ProizvodUPoslovniciDataAccess{

	@Override
	public List<Proizvod> proizvodiUPoslovnici(int idPoslovnica) {
		List<Proizvod> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT Sifra, Kolicina "
				+ "FROM proizvod_u_poslovnici "
				+ "WHERE idPoslovnica=?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idPoslovnica);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(Utilities.getDataAccessFactory().getProizvodDataAccess().proizvod(rs.getInt(1)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}

	@Override
	public boolean dodajProizvodUPoslovnicu(int sifra, int idPoslovnica) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean obrisiProizvodUPoslovnici(int sifra, int idPoslovnica) {
		// TODO Auto-generated method stub
		return false;
	}	

}
