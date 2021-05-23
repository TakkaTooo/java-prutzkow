package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.math.BigDecimal;
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

	public OracleDbMonthlyRevenueSheetDao(JdbcClient client) {
		this.client = client;
	}

	public List<MonthlyRevenueSheet> getCostByMonthSheets() {
		List<MonthlyRevenueSheet> result = new ArrayList<MonthlyRevenueSheet>();
		try {
			List<Map<String, Object>> queryResult = this.client.executeQuery(costByMonthSheetsSql);
			Iterator<Map<String, Object>> iterator = queryResult.iterator();
			while (iterator.hasNext()) {
				result.add(extractMonthlyRevenueSheet(iterator.next()));
			}
		} catch (SQLException e) {
		}
		return result;
	}

	private MonthlyRevenueSheet extractMonthlyRevenueSheet(Map<String, Object> row) {
		return new MonthlyRevenueSheet(
				((BigDecimal) row.get(Resourcer.getString("dao.MonthleRevenue.column.month"))).intValueExact(),
				((BigDecimal) row.get(Resourcer.getString("dao.MonthleRevenue.column.cost"))).floatValue());
	}
}