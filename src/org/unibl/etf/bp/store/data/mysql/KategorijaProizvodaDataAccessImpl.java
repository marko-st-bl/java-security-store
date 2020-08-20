package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.store.data.KategorijaProizvodaDataAccess;
import org.unibl.etf.bp.store.entity.KategorijaProizvoda;

public class KategorijaProizvodaDataAccessImpl implements KategorijaProizvodaDataAccess {

	@Override
	public List<KategorijaProizvoda> kategorije() {
		List<KategorijaProizvoda> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM KATEGORIJA_PROIZVODA";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retVal.add(new KategorijaProizvoda(rs.getInt(1), rs.getString(2)));
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
