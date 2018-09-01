package com.adamsmolnik.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class Util {

	public static final String BUCKET_NAME = "developing-cloud";

	public static final AmazonS3 S3 = AmazonS3ClientBuilder.defaultClient();

	public static InputStream generateRandomData(int dataSize) {
		byte[] input = new byte[dataSize];
		new Random().nextBytes(input);
		return new ByteArrayInputStream(input);
	}

	private static final int BUFFER_SIZE = 8 * 1024;

	public static long copy(InputStream in, OutputStream out) throws IOException {
		byte[] buf = new byte[BUFFER_SIZE];
		long count = 0L;
		int n = 0;
		while ((n = in.read(buf)) > -1) {
			out.write(buf, 0, n);
			count += n;
		}
		return count;
	}

	private Util() {

	}

}
