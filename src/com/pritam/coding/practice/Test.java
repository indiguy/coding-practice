package com.pritam.coding.practice;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1101513929);
		list.add(1315634022);

		System.out.println(list.stream().mapToLong(elem -> elem).sum());

		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1059961393);
		list1.add(628175011);

		System.out.println(list1.stream().mapToLong(elem -> elem).sum());
	}

}
