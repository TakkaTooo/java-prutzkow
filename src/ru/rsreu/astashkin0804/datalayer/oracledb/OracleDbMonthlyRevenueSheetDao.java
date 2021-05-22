package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0804.datalayer.MonthlyRevenueSheetDao;
import ru.rsreu.astashkin0804.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.astashkin0804.datalayer.model.MonthlyRevenueSheet;

public class OracleDbMonthlyRevenueSheetDao implements MonthlyRevenueSheetDao {
	private static String costByMonthSheetsSql = Resourcer.getString("dao.MonthleRevenue.sql");
	private JdbcClient client;

	public OracleDbMonthlyRevenueSheetDao(Connection connection) {
		client = new JdbcClient(connection);
	}

	public List<MonthlyRevenueSheet> getCostByMonthSheets() {
		List<MonthlyRevenueSheet> result = new ArrayList<MonthlyRevenueSheet>();
		try {
			List<Map<String, Object>> queryResult = this.client.executeQuery(costByMonthSheetsSql);
			Iterator<Map<String, Object>> iterator = queryResult.iterator();
			while (iterator.hasNext()) {
				Map<String, Object> currentRow = iterator.next();
				result.add(new MonthlyRevenueSheet(
						((BigDecimal) currentRow.get(Resourcer.getString("dao.MonthleRevenue.column.month"))).intValueExact(),
						((BigDecimal) currentRow.get(Resourcer.getString("dao.MonthleRevenue.column.cost"))).floatValue()));
			}
		} catch (SQLException e) {

		}
		return result;
	}
}