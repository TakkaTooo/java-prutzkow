package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.astashkin0804.DateStringConverter;
import ru.rsreu.astashkin0804.datalayer.DetailDealDao;
import ru.rsreu.astashkin0804.datalayer.jdbc.client.Client;
import ru.rsreu.astashkin0804.datalayer.model.DetailDeal;

public class OracleDbDetailDealDao implements DetailDealDao {
	private static String detailDealByDate = "SELECT deals.id, comissionDate, buyers.contactperson, buyers.telephonenumber, products.name, quantity, isWholeSale\r\n"
			+ " FROM (deals JOIN products on deals.productId = products.id) JOIN buyers on buyerId = buyers.id\r\n"
			+ " WHERE TO_DATE(?, 'dd/mm/yyyy') < comissionDate AND TO_DATE(?, 'dd/mm/yyyy') > comissionDate";
	private Connection connection;
	private Client client;

	public OracleDbDetailDealDao(Connection connection) {
		this.connection = connection;
		client = new Client(this.connection);
	}

	@Override
	public List<DetailDeal> getDetailDealByDate(Date lowerComissionDate, Date upperComissionDate) {
		List<DetailDeal> result = new ArrayList<DetailDeal>();
		try {
			ResultSet set = client.executeQuery(detailDealByDate,
					DateStringConverter.convertDateToString(lowerComissionDate),
					DateStringConverter.convertDateToString(upperComissionDate));
			while (set.next()) {
				result.add(new DetailDeal(set.getInt("ID"), set.getDate("COMISSIONDATE"), set.getString("NAME"),
						set.getInt("QUANTITY"), set.getString("TELEPHONENUMBER"), set.getString("CONTACTPERSON"),
						getBooleanFromInt(set.getInt("ISWHOLESALE"))));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private static boolean getBooleanFromInt(int obj) {
		return ((Integer) 1).equals(obj);
	}
}
