package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.store.data.KupacDataAceess;
import org.unibl.etf.bp.store.entity.Osoba;

public class KupacDataAccessImpl implements KupacDataAceess {

	@Override
	public List<String> kupci() {
		List<String> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM kupac_info";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				retVal.add(rs.getString(1) + "-" + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
			MySQLUtilities.getInstance().close(rs);
		}
		return retVal;
	}

	@Override
	public boolean dodajKupca(Osoba o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean obrisiKupca(Osoba o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean azurirajKupca(Osoba o) {
		// TODO Auto-generated method stub
		return false;
	}

}
