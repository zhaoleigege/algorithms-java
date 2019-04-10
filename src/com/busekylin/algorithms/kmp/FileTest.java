package com.busekylin.algorithms.kmp;

import java.io.FileInputStream;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) throws IOException {
		new FileMultiProcesser("--------------------------124629655929752871088454".getBytes(),
				new FileInputStream("/Users/buse/Documents/JAVA/eclipse/algorithms/1.txt")).getContent();
	}

}
