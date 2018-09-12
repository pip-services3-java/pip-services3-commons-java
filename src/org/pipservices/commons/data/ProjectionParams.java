package org.pipservices.commons.data;

import java.util.*;

public class ProjectionParams extends ArrayList<String> {

	private static final long serialVersionUID = 5876557837753631885L;
	private static char defaultDelimiter = ',';

	public ProjectionParams() {
	}

	public ProjectionParams(String[] values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++)
				add(values[i]);
		}
	}

	public ProjectionParams(AnyValueArray array) {
		if (array == null) {
			return;
		}

		for (int index = 0; index < array.size(); index++) {
			String value = array.getAsString(index);
			if (value != null && value.length() > 0) {
				add(value);
			}
		}
	}

	public static ProjectionParams fromValue(Object value) {
		if (value instanceof ProjectionParams) //// value.getClass() == ProjectionParams.class
		{
			return (ProjectionParams) value;
		}

		AnyValueArray array = value != null ? AnyValueArray.fromValue(value) : new AnyValueArray();
		return new ProjectionParams(array);
	}

	public static ProjectionParams fromValues(String... values) {
		return fromValues(defaultDelimiter, values);
	}

	public static ProjectionParams fromValues(char delimiter, String... values) {
		return new ProjectionParams(parse(delimiter, values));
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < builder.length(); index++) {
			if (index > 0) {
				builder.append(',');
			}

			builder.append(super.get(index));
		}
		return builder.toString();
	}

	private static String[] parse(char delimiter, String[] values) {
		List<String> result = new ArrayList<String>();
		String prefix = "";

		for (String value : values) {
			parseValue(prefix, result, value.trim(), delimiter);
		}
		String[] arrayResult = result.toArray(new String[result.size()]);
		return arrayResult;
	}

	private static void parseValue(String prefix, List<String> result, String value, char delimiter) {
		value = value.trim();

		int openBracket = 0;
		int openBracketIndex = -1;
		int closeBracketIndex = -1;
		int commaIndex = -1;

		boolean breakCycleRequired = false;

		for (int index = 0; index < value.length(); index++) {
			Character valueChar = value.charAt(index);
			if (valueChar.equals('(')) {
				if (openBracket == 0) {
					openBracketIndex = index;
				}

				openBracket++;
			} else if (valueChar.equals(')')) {
				openBracket--;

				if (openBracket == 0) {
					closeBracketIndex = index;

					if (openBracketIndex >= 0 && closeBracketIndex > 0) {
						String previousPrefix = prefix;

						if (prefix != null && prefix.length() > 0) {
							prefix = prefix + "." + value.substring(0, openBracketIndex);
						} else {
							prefix = value.substring(0, openBracketIndex);
						}

						String subValue = value.substring(openBracketIndex + 1, closeBracketIndex);

						parseValue(prefix, result, subValue, delimiter);

						subValue = value.substring(closeBracketIndex + 1);
						parseValue(previousPrefix, result, subValue, delimiter);
						breakCycleRequired = true;
					}
				}
			} else if (valueChar.equals(delimiter)) {
				if (openBracket == 0) {
					commaIndex = index;

					String subValue = value.substring(0, commaIndex);
					if (subValue != null && subValue.length() > 0) {
						if (prefix != null && prefix.length() > 0) {
							result.add(prefix + "." + subValue);
						} else {
							result.add(subValue);
						}
					}

					subValue = value.substring(commaIndex + 1);

					if (subValue != null && subValue.length() > 0) {
						parseValue(prefix, result, subValue, delimiter);
						breakCycleRequired = true;
					}
				}
			}

			if (breakCycleRequired) {
				break;
			}
		}

		if (value != null && value.length() > 0 && openBracketIndex == -1 && commaIndex == -1) {
			if (prefix != null && prefix.length() > 0) {
				result.add(prefix + "." + value);
			} else {
				result.add(value);
			}
		}
	}
}
