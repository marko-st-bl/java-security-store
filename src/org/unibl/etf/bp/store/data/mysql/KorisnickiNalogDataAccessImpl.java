package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.unibl.etf.bp.store.data.KorisnickiNalogDataAccess;

public class KorisnickiNalogDataAccessImpl implements KorisnickiNalogDataAccess {

	@Override
	public String prijavi(String korisnickoIme, String lozinka) {
		String retVal="";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		
		String query="SELECT Id "
				+"FROM korisnicki_nalog "
				+"WHERE KorisnickoIme=? AND Lozinka=?";
		try {
			conn= ConnectionPool.getInstance().checkOut();
			ps=conn.prepareStatement(query);
			ps.setString(1, korisnickoIme );
			ps.setString(2, lozinka);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				retVal=rs.getString(1);
			}
		}
		catch (SQLException e) {
		e.printStackTrace();
		MySQLUtilities.getInstance().showSQLException(e);
		} 
		finally {
		ConnectionPool.getInstance().checkIn(conn);
		MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}
}
