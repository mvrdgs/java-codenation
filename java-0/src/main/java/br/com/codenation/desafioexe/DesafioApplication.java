package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {
	
	private static List<Integer> generateFibonacciList(int limit) {
		List<Integer> fibonacciList = new ArrayList<>();

		fibonacciList.add(0);
		fibonacciList.add(1);
		int nextNumber;

		do {
			int length = fibonacciList.size();

			nextNumber = fibonacciList.get((length - 1)) + fibonacciList.get(length - 2);

			fibonacciList.add(nextNumber);
		} while (nextNumber < limit);

		return fibonacciList;
	}

	public static List<Integer> fibonacci() {
		return generateFibonacciList(350);
	}

	public static Boolean isFibonacci(Integer a) {
		List<Integer> fibonacciList = generateFibonacciList(a);
		boolean result = fibonacciList.contains(a);
		return result;
	}
}
