package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0804.DateStringConverter;
import ru.rsreu.astashkin0804.datalayer.DetailDealDao;
import ru.rsreu.astashkin0804.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.astashkin0804.datalayer.model.DetailDeal;

public class OracleDbDetailDealDao implements DetailDealDao {
	private static String detailDealByDateSql = Resourcer.getString("dao.detaildeal.sql");
	private JdbcClient client;
	private DateStringConverter dateStringConverter = new DateStringConverter(Resourcer.getString("demo.datePattern"));

	public OracleDbDetailDealDao(Connection connection) {
		client = new JdbcClient(connection);
	}

	@Override
	public List<DetailDeal> getDetailDealByDate(Date lowerComissionDate, Date upperComissionDate) {
		List<DetailDeal> result = new ArrayList<DetailDeal>();
		try {
			List<Map<String, Object>> queryResult = this.client.executeQuery(detailDealByDateSql,
					dateStringConverter.convertDateToString(lowerComissionDate),
					dateStringConverter.convertDateToString(upperComissionDate));
			Iterator<Map<String, Object>> iterator = queryResult.iterator();
			while (iterator.hasNext()) {
				Map<String, Object> currentRow = iterator.next();
				result.add(new DetailDeal(((BigDecimal) currentRow.get(Resourcer.getString("dao.column.id"))).intValueExact(),
						(Date) currentRow.get(Resourcer.getString("dao.detaildeal.column.comissiondate")),
						currentRow.get(Resourcer.getString("dao.detaildeal.column.name")).toString(),
						((BigDecimal) currentRow.get(Resourcer.getString("dao.detaildeal.column.quantity"))).intValueExact(),
						currentRow.get(Resourcer.getString("dao.buyer.column.telephone")).toString(),
						currentRow.get(Resourcer.getString("dao.buyer.column.contactperson")).toString(),
						getBooleanFromInt(
								((BigDecimal) currentRow.get(Resourcer.getString("dao.dataildeal.column.iswholesale"))).intValueExact())));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	private static boolean getBooleanFromInt(int obj) {
		return 1 == obj;
	}
}
