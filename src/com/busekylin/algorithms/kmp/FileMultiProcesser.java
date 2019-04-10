package com.busekylin.algorithms.kmp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileMultiProcesser {
	private byte[] delimeter;
	private InputStream input;
	private static final int BUFFER_SIZE = 512;

	private boolean parsed;
	private InputStream output;

	public FileMultiProcesser(byte[] delimeter, InputStream input) {
		super();
		this.delimeter = delimeter;
		this.input = input;
		this.parsed = false;
	}

	public InputStream getContent() throws IOException {
		if (!this.parsed) {
			this.parse();
		}

		return this.output;
	}

	private void parse() throws IOException {
		int[] patternArray = FileMultiProcesser.bytePatternArray(this.delimeter);

		OutputStream outputStream = null;
		String tempFileName = "2.txt";
		boolean part = false;

		byte[] buffer = new byte[FileMultiProcesser.BUFFER_SIZE];
		int offset = 0;
		int length = buffer.length;
		int hasRead;

		int delimeterIndex = 0;

		while ((hasRead = this.input.read(buffer, offset, length)) != -1) {
			int end = hasRead + offset;
			boolean cycle = true;

			while (cycle) {
				int i = offset, j = delimeterIndex;

				for (; (i < end) && (j < this.delimeter.length);) {
					if (buffer[i] == this.delimeter[j]) {
						j++;
						i++;
					} else {
						if (j > 0) {
							j = patternArray[j - 1];
						} else {
							i++;
						}
					}
				}

				if (j == this.delimeter.length) {
					if (part) {
						outputStream.write(buffer, offset, i - this.delimeter.length - offset);
						outputStream.close();
						outputStream = null;
						part = false;
						this.parse(new FileInputStream(tempFileName));
					} else {
						this.parse(buffer, offset, i - this.delimeter.length);
					}

					if (i > (buffer.length / 2)) {
						this.byteMove(buffer, i, end - i, 0);
						offset = end - i;
						length = buffer.length - offset;
						cycle = false;
					} else {
						offset = i;
					}

				} else {
					cycle = false;

					if (!part) {
						if (hasRead < (buffer.length / 2)) {
							this.byteMove(buffer, offset, hasRead, 0);
							offset = hasRead;
							length = buffer.length - offset;

							continue;
						}

						part = true;
						outputStream = new FileOutputStream(tempFileName);
					}

					outputStream.write(buffer, offset, end - offset);

					delimeterIndex = j;
					offset = 0;
					length = buffer.length;
				}
			}

		}
	}

	private void parse(byte[] buffer, int start, int end) {
		if (start == end) {
			return;
		}

		System.out.println(new String(buffer, start, 1).equals("\r"));

//		System.out.println(new String(buffer, start, end - start));
	}

	private void parse(InputStream inputStream) {
	}

	private void byteMove(byte[] buffer, int srcPostion, int length, int destPosition) {
		for (int i = srcPostion; i < length; i++) {
			buffer[destPosition] = buffer[i];
		}
	}

	private static int[] bytePatternArray(byte[] pattern) {
		int[] arrays = new int[pattern.length];

		int j = 0;
		int i = 1;

		arrays[0] = 0;

		for (; i < pattern.length; i++) {
			if (pattern[i] == pattern[j]) {
				arrays[i] = j + 1;
				j++;
			} else {
				while (j > 0) {
					j = arrays[j - 1];
					if (pattern[i] == pattern[j]) {
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

}