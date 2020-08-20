package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.store.data.DobavljacDataAccess;
import org.unibl.etf.bp.store.entity.Dobavljac;

public class DobavljacDataAccessImpl implements DobavljacDataAccess {

	@Override
	public List<Dobavljac> dobavljaci() {
		List<Dobavljac> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query="SELECT IdDobavljac, Naziv, Adresa FROM dobavljac";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new Dobavljac(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		
		return retVal;
	}

	@Override
	public Dobavljac getDobavljac(int idDobavljac) {
		Dobavljac retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query="SELECT * FROM DOBAVLJAC"
				+ "WHERE IdDobavljac=?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idDobavljac);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retVal = new Dobavljac(rs.getInt(1), rs.getString(2), rs.getString(3));
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

}
