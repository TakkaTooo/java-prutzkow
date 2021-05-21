package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.astashkin0804.datalayer.BuyerDao;
import ru.rsreu.astashkin0804.datalayer.jdbc.client.Client;
import ru.rsreu.astashkin0804.datalayer.model.Buyer;

public class OracleDbBuyerDao implements BuyerDao {
	private static String buyerByProductIdSql = "SELECT * FROM buyers WHERE buyers.id in (SELECT deals.buyerid FROM deals WHERE productId = ?)";
	private Connection connection;
	private Client client;

	public OracleDbBuyerDao(Connection connection) {
		this.connection = connection;
		client = new Client(this.connection);
	}

	@Override
	public List<Buyer> getBuyersByProduct(int productId) {
		List<Buyer> result = new ArrayList<Buyer>();
		try {
			ResultSet resultSet = client.executeQuery(buyerByProductIdSql, ((Integer) productId).toString());
			while (resultSet.next()) {
				result.add(new Buyer(resultSet.getInt("ID"), resultSet.getString("TELEPHONENUMBER"),
						resultSet.getString("CONTACTPERSON"), resultSet.getString("ADDRESS")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
