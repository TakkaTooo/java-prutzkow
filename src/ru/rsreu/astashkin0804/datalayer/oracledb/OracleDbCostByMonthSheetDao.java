package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.astashkin0804.datalayer.CostByMonthSheetDao;
import ru.rsreu.astashkin0804.datalayer.jdbc.client.Client;
import ru.rsreu.astashkin0804.datalayer.model.CostByMonthSheet;

public class OracleDbCostByMonthSheetDao implements CostByMonthSheetDao {
	private static String costByMonthSheetsSql = "SELECT month, SUM(cost) as cost\r\n"
											   + " FROM (\r\n"
											   + "        SELECT EXTRACT(month from comissiondate) as month,\r\n"
											   + "               (CASE isWholeSale WHEN 0 THEN (retailPrice * quantity)\r\n"
											   + "                ELSE wholesalePrice * quantity END) as cost\r\n"
											   + "         FROM deals JOIN products on productId = products.id)\r\n"
											   + "         GROUP BY month";
	private Connection connection;
	private Client client;
	
	public OracleDbCostByMonthSheetDao(Connection connection) {
		this.connection = connection;
		client = new Client(this.connection);
	}

	public List<CostByMonthSheet> getCostByMonthSheets() {
		List<CostByMonthSheet> result = new ArrayList<CostByMonthSheet>();
		try {
			ResultSet resultSet = client.executeQuery(costByMonthSheetsSql);
			while (resultSet.next()) {
				result.add(new CostByMonthSheet(resultSet.getInt("MONTH"), resultSet.getFloat("COST")));
			}
		} catch(SQLException e) {
			
		}
		return result;
	}
}
