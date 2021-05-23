package ru.rsreu.astashkin0804;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.prutzkow.resourcer.Resourcer;

public class ReflectionTableGenerator<T> {

	private List<T> body;
	private Map<String, Integer> fieldsValueMaxLengths = new HashMap<String, Integer>();

	public ReflectionTableGenerator(List<T> body) {
		this.body = body;
	}

	public String generateTable() {
		this.getFieldsValueMaxLength();
		StringBuilder output = new StringBuilder();
		output.append(getTableHeader()).append(getTableBody());
		return output.toString();
	}

	private String getTableHeader() {
		StringBuilder output = new StringBuilder();
		setTableHeaderBorder(output);
		output.append("\n");
		for (Field field : getFields(body.get(0))) {
			output.append(formTableString(field.getName(), field));
		}
		output.append("|\n");
		setTableHeaderBorder(output);
		return output.append("\n").toString();
	}

	private void setTableHeaderBorder(StringBuilder output) {
		int entrieLength = getStringEntrieLength();
		for (int i = 0; i <= entrieLength; i++) {
			output.append("-");
		}
	}

	private int getStringEntrieLength() {
		Iterator<Integer> iterator = fieldsValueMaxLengths.values().iterator();
		int result = 0;
		while (iterator.hasNext()) {
			result += iterator.next();
		}
		return result + fieldsValueMaxLengths.values().size();
	}

	private String getTableBody() {
		StringBuilder output = new StringBuilder();
		for (T object : body) {
			output.append(convertBodyElementToString(object));
		}
		return output.toString();
	}

	private String convertBodyElementToString(T object) {
		StringBuilder output = new StringBuilder();
		List<Field> fields = getFields(object);
		for (Field field : fields) {
			output.append(formTableString(getFieldValueStringRepresentation(field, object), field));
		}
		return output.append("|\n").toString();
	}

	private String formTableString(String value, Field field) {
		StringBuilder output = new StringBuilder();
		if (!Modifier.isStatic(field.getModifiers())) {
			output.append("|").append(adjustToMaxFieldLength(new StringBuilder().append(value), field));
		}
		return output.toString();
	}

	private String adjustToMaxFieldLength(StringBuilder value, Field field) {
		while (value.length() < this.fieldsValueMaxLengths.get(field.getName())) {
			value.append(" ");
		}
		return value.toString();
	}

	private Map<String, Integer> getFieldsValueMaxLength() {
		for (T element : body) {
			setFieldsValueMaxLength(getFields(element), element);
		}
		updateFieldsValueMaxLengthsWithFieldNameLengths();
		return this.fieldsValueMaxLengths;
	}

	private void updateFieldsValueMaxLengthsWithFieldNameLengths() {
		Iterator<Map.Entry<String, Integer>> iter = getFieldsNameLengths(getFields(body.get(0))).entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Integer> currentPair = iter.next();
			if (currentPair.getValue() > this.fieldsValueMaxLengths.get(currentPair.getKey())) {
				this.fieldsValueMaxLengths.replace(currentPair.getKey(), currentPair.getValue());
			}
		}
	}

	private void setFieldsValueMaxLength(List<Field> fields, T object) {
		for (Field field : fields) {
			String fieldName = field.getName();
			int fieldValuelength = getFieldValueStringRepresentation(field, object).length();
			if (this.fieldsValueMaxLengths.keySet().contains(field.getName())) {
				if (fieldValuelength > this.fieldsValueMaxLengths.get(fieldName)) {
					this.fieldsValueMaxLengths.replace(fieldName, fieldValuelength);
				}
			} else {
				this.fieldsValueMaxLengths.put(fieldName, fieldValuelength);
			}
		}
	}

	private static Map<String, Integer> getFieldsNameLengths(List<Field> fields) {
		Map<String, Integer> fieldNamesLengths = new HashMap<String, Integer>();
		for (Field field : fields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				fieldNamesLengths.put(field.getName(), field.getName().length());
			}
		}
		return fieldNamesLengths;
	}

	private List<Field> getFields(T object) {
		return Arrays.asList(object.getClass().getDeclaredFields());
	}

	private String getFieldValueStringRepresentation(Field field, T object) {
		String getter = getGetterName(field);
		try {
			return getObjectStringValue(getter, object);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		}
		return "";
	}

	private String getGetterName(Field field) {
		StringBuilder getter = new StringBuilder();
		if (!field.getType().equals(Boolean.TYPE)) {
			getter.append("get").append(field.getName().substring(0, 1).toUpperCase())
					.append(field.getName().substring(1));
		} else {
			getter.append(field.getName());
		}
		return getter.toString();
	}

	private String getObjectStringValue(String getter, T object) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		StringBuilder value = new StringBuilder();
		Object obj = object.getClass().getDeclaredMethod(getter).invoke(object);
		if (obj instanceof Date) {
			DateStringConverter dateStringConverter = new DateStringConverter(Resourcer.getString("demo.datePattern"));
			value.append(dateStringConverter.convertDateToString((Date) obj));
		} else {
			value.append(obj.toString());
		}
		return value.toString();
	}
}