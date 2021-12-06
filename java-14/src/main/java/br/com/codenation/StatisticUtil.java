package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static void main(String[] args) {
		System.out.println(median(new int[] {1,2,3,4,5,6}));
	}

	public static int average(int[] elements) {
		int allNumbersSum = Arrays.stream(elements)
				.reduce(0, Integer::sum);

		return allNumbersSum / elements.length;
	}

	public static int mode(int[] elements) {
		int repetitionsCounter = 1;
		int maxRepetions = 1;
		int mode = elements[0];

		Arrays.sort(elements);

		for (int index = 1; index < elements.length; index += 1) {
			if (elements[index] == elements[index - 1]) {
				repetitionsCounter += 1;

				if (repetitionsCounter > maxRepetions) {
					maxRepetions = repetitionsCounter;
					mode = elements[index];
				}
			} else {
				repetitionsCounter = 1;
			}
		}

		return mode;
	}

	public static int median(int[] elements) {
		boolean isEven = elements.length % 2 == 0;
		int median;

		Arrays.sort(elements);

		if (isEven) {
			int secondMiddleNumber = elements.length / 2;
			int firstMiddleNumber = secondMiddleNumber - 1;

			median = (elements[secondMiddleNumber] + elements[firstMiddleNumber]) / 2;
		} else {
			int middleNumber = elements.length / 2;
			System.out.println(middleNumber);
			median = elements[middleNumber];
		}

		return median;
	}
}