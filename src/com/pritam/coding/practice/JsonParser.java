package com.pritam.coding.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

	private static final Pattern NESTED = Pattern.compile("[a-z0-9]+:\\s*\\{.*?\\}+");
	private static final Pattern SIMPLE = Pattern.compile("[a-z0-9]+:\\s*[a-z0-9]+");

	/**
	 * Parse the given json string into {@link Map}
	 *
	 * @param json
	 * @return
	 */
	public Map<String, Object> parse(String json) {
		Map<String, Object> result = new HashMap<>();

		Matcher matcher = NESTED.matcher(json);
		boolean found = false;
		String rest = json;
		while (matcher.find()) {
			found = true;
			String subPart = matcher.group();
			int firstIndexOfColon = subPart.indexOf(":");
			String key = subPart.substring(0, firstIndexOfColon).trim();
			String value = subPart.substring(firstIndexOfColon + 1).trim();
			result.put(key, parse(value));
			rest = rest.substring(0, matcher.start()) + rest.substring(matcher.end(), rest.length());
		}
		if (!found) {
			matcher = SIMPLE.matcher(json);
		} else {
			matcher = SIMPLE.matcher(rest);
		}
		while (matcher.find()) {
			String subPart = matcher.group();
			String[] keyVal = subPart.split(":");
			result.put(keyVal[0].trim(), keyVal[1].trim());
		}
		return result;
	}

	public static void main(String[] args) {
		final String input = "{k1: v1, k2: {k3: v3, k4: {k5: v5}}, k6: v6}";
		System.out.println(new JsonParser().parse(input));
	}
}
