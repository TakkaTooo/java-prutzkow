package ru.rsreu.astashkin0804.datalayer.oracledb;

import java.math.BigDecimal;
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

	public OracleDbDetailDealDao(JdbcClient client) {
		this.client = client;
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
				result.add(extractDetailDeal(iterator.next()));
			}
		} catch (SQLException e) {
		}
		return result;
	}

	private DetailDeal extractDetailDeal(Map<String, Object> row) {
		return new DetailDeal(((BigDecimal) row.get(Resourcer.getString("dao.column.id"))).intValueExact(),
				(Date) row.get(Resourcer.getString("dao.detaildeal.column.comissiondate")),
				row.get(Resourcer.getString("dao.detaildeal.column.name")).toString(),
				((BigDecimal) row.get(Resourcer.getString("dao.detaildeal.column.quantity"))).intValueExact(),
				row.get(Resourcer.getString("dao.buyer.column.telephone")).toString(),
				row.get(Resourcer.getString("dao.buyer.column.contactperson")).toString(),
				getBooleanFromInt(((BigDecimal) row.get(Resourcer.getString("dao.dataildeal.column.iswholesale")))
						.intValueExact()));
	}

	private static boolean getBooleanFromInt(int obj) {
		return 1 == obj;
	}
}
