/**
 *
 */
package com.pritam.coding.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern Matcher example
 *
 * @author pribiswas
 *
 */
public class PatternMatcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String text = "{k1: v1, k2: {k3: v3, k4:{k5: v5, k7: {k8: v8}}}, k6: v6}";
		final String regex = "\\w+:\\s*\\{.*?\\}+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

}
