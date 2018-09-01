package com.adamsmolnik.s3;

import static com.adamsmolnik.util.Util.BUCKET_NAME;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

public class S3TransferManagerBuilderSample {

	private static final int DATA_SIZE = 200 * 1024 * 1024;

	public static void main(String[] args) throws Exception {
		long then = System.currentTimeMillis();
		ObjectMetadata om = new ObjectMetadata();
		om.setContentLength(DATA_SIZE);
		TransferManager tm = TransferManagerBuilder.defaultTransferManager();
		Upload upload = tm.upload(BUCKET_NAME, "data.dat", generateRandomData(), om);
		upload.waitForCompletion();
		tm.shutdownNow();
		System.out.println(System.currentTimeMillis() - then);
	}

	private static InputStream generateRandomData() {
		byte[] input = new byte[DATA_SIZE];
		new Random().nextBytes(input);
		return new ByteArrayInputStream(input);
	}

}
