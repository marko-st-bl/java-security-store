package org.unibl.etf.bp.store.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.unibl.etf.bp.store.data.ProdajaDataAccess;
import org.unibl.etf.bp.store.entity.Prodaja;
import org.unibl.etf.bp.store.entity.StavkaProdaja;

public class ProdajaDataAccessImpl implements ProdajaDataAccess{

	@Override
	public List<Prodaja> prodaje() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dodajProdaju(Prodaja prodaja) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		
		String query="INSERT INTO prodaja (DatumProdaje, JMBProdavac) VALUES (?,?)";
		String query1="INSERT INTO stavka_prodaja (IdProdaja, Sifra, IdPoslovnica, Kolicina, Cijena) VALUES (?, ?, ?, ?, ?)";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, new java.sql.Date(prodaja.getDatumProdaje().getTime()));
			ps.setString(2, prodaja.getProdavac().getJMB());
			retVal = ps.executeUpdate() == 1;
			rs = ps.getGeneratedKeys();
			rs.next();
			ps1 = conn.prepareStatement(query1);
			for(StavkaProdaja sp:prodaja.getStavke()) {
				ps1.setInt(1, rs.getInt(1));
				ps1.setInt(2, sp.getProizvod().getSifra());
				ps1.setInt(3, sp.getPoslovnica().getIdPoslovnica());
				ps1.setInt(4, sp.getKolicina());
				ps1.setDouble(5, sp.getCijena());
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
	public boolean azurirajProdaju(Prodaja prodaja) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean obrisiProdaju(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
