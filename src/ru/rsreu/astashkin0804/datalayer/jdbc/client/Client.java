package ru.rsreu.astashkin0804.datalayer.jdbc.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Client {
	private Connection connection;

	public Client(Connection connection) {
		this.connection = connection;
	}

	public ResultSet executeQuery(String sql, String... arguments) throws SQLException {
		Map<Integer, String> argumentsForStatement = getArgumentsForStatement(arguments);
		PreparedStatement statement = connection.prepareStatement(sql);
		setStringForPreparedStatement(statement, argumentsForStatement);
		return statement.executeQuery();
	}

	private static Map<Integer, String> getArgumentsForStatement(String[] arguments) {
		Map<Integer, String> argumentsForStatement = new HashMap<Integer, String>();
		for (int i = 0; i < arguments.length; i++) {
			argumentsForStatement.put(i + 1, arguments[i]);
		}
		return argumentsForStatement;
	}

	private static void setStringForPreparedStatement(PreparedStatement statement, Map<Integer, String> arguments)
			throws SQLException {
		Iterator<Map.Entry<Integer, String>> iterator = arguments.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, String> next = iterator.next();
			statement.setString(next.getKey(), next.getValue());
		}
	}
}
