package com.busekylin.algorithms.kmp;

public class KMP {
	private static int[] buildPatternArray(String pattern) {
		int[] arrays = new int[pattern.length()];

		int j = 0;
		int i = 1;

		arrays[0] = 0;

		for (; i < pattern.length(); i++) {
			if (pattern.charAt(i) == pattern.charAt(j)) {
				arrays[i] = j + 1;
				j++;
			} else {
				while (j > 0) {
					j = arrays[j - 1];
					if (pattern.charAt(i) == pattern.charAt(j)) {
						break;
					}
				}
				if (j > 0) {
					j = j + 1;
				}

				arrays[i] = j;
			}
		}

		return arrays;
	}

	public static int mactch(String text, String pattern) {
		int[] patternArrays = KMP.buildPatternArray(pattern);

		int i = 0, j = 0;

		for (; (i < text.length()) && (j < pattern.length());) {
			if (text.charAt(i) == pattern.charAt(j)) {
				j++;
				i++;
			} else {
				if (j > 0) {
					j = patternArrays[j - 1];
				} else {
					i++;
				}
			}
		}

		if (j == pattern.length()) {
			return i - j;
		}

		return -1;
	}

	public static void main(String[] args) {
		String text = "ababababca";
		String pattern = "abababca";

		System.out.println(KMP.mactch(text, pattern));
	}
}
