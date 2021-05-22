package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0804.datalayer.BuyerDao;
import ru.rsreu.astashkin0804.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.astashkin0804.datalayer.model.Buyer;

public class OracleDbBuyerDao implements BuyerDao {
	private static String buyerByProductIdSql = Resourcer.getString("dao.buyer.sql");
	private JdbcClient client;

	public OracleDbBuyerDao(Connection connection) {
		client = new JdbcClient(connection);
	}

	@Override
	public List<Buyer> getBuyersByProduct(int productId) {
		List<Buyer> result = new ArrayList<Buyer>();
		try {
			List<Map<String, Object>> queryResult = this.client.executeQuery(buyerByProductIdSql,
					((Integer) productId).toString());
			Iterator<Map<String, Object>> iterator = queryResult.iterator();
			while (iterator.hasNext()) {
				Map<String, Object> currentRow = iterator.next();
				result.add(new Buyer(((BigDecimal) currentRow.get(Resourcer.getString("dao.column.id"))).intValueExact(),
						currentRow.get(Resourcer.getString("dao.buyer.column.telephone")).toString(),
						currentRow.get(Resourcer.getString("dao.buyer.column.contactperson")).toString(),
						currentRow.get(Resourcer.getString("dao.buyer.column.address")).toString()));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
