package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.math.BigDecimal;
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
	private static final String BUYER_BY_PRODUCT_ID_SQL = Resourcer.getString("dao.buyer.sql");
	private JdbcClient client;

	public OracleDbBuyerDao(JdbcClient client) {
		this.client = client;
	}

	@Override
	public List<Buyer> getBuyersByProduct(int productId) {
		List<Buyer> result = new ArrayList<Buyer>();
		try {
			List<Map<String, Object>> queryResult = this.client.executeQuery(BUYER_BY_PRODUCT_ID_SQL,
					((Integer) productId).toString());
			Iterator<Map<String, Object>> iterator = queryResult.iterator();
			while (iterator.hasNext()) {
				result.add(extractBuyer(iterator.next()));
			}
		} catch (SQLException e) {
		}
		return result;
	}

	private Buyer extractBuyer(Map<String, Object> row) {
		return new Buyer(((BigDecimal) row.get(Resourcer.getString("dao.column.id"))).intValueExact(),
				row.get(Resourcer.getString("dao.buyer.column.telephone")).toString(),
				row.get(Resourcer.getString("dao.buyer.column.contactperson")).toString(),
				row.get(Resourcer.getString("dao.buyer.column.address")).toString());
	}
}
