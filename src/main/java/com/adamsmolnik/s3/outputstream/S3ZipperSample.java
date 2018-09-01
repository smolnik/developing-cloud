package com.adamsmolnik.s3.outputstream;

import static com.adamsmolnik.util.Util.copy;
import static com.adamsmolnik.util.Util.generateRandomData;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3ZipperSample {

	private static final int DATA_SIZE = 20 * 1024 * 1024;

	public static void main(String[] args) throws Exception {
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		InputStream is = generateRandomData(DATA_SIZE);
		long then = System.currentTimeMillis();
		try (ZipOutputStream zos = new ZipOutputStream(
				new S3ParallelMultipartOutputStream(6 * 1024 * 1024, 3, "z-mutliupload-test", "data.zip", s3))) {
			zos.putNextEntry(new ZipEntry("data.dat"));
			copy(is, zos);
			zos.closeEntry();
		}
		System.out.println(System.currentTimeMillis() - then);
	}

}
