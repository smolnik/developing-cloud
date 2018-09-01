package com.adamsmolnik.s3;

import static com.adamsmolnik.util.Util.BUCKET_NAME;
import static com.adamsmolnik.util.Util.S3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class S3GetRangeBytes {

	public static void main(String[] args) throws Exception {
		GetObjectRequest getReq02 = new GetObjectRequest(BUCKET_NAME, "get-test");
		getReq02.setRange(0, 2);
		S3Object obj02 = S3.getObject(getReq02);
		String chunk02 = getString(obj02);
		System.out.println("chars range 0-2: " + chunk02);

		GetObjectRequest getReq35 = new GetObjectRequest("aws-dev-v2-resources", "get-test");
		getReq35.setRange(3, 5);
		S3Object obj35 = S3.getObject(getReq35);
		String chunk35 = getString(obj35);
		System.out.println("chars range 3-5: " + chunk35);
	}

	private static String getString(S3Object obj) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(obj.getObjectContent()))) {
			return br.readLine();
		}
	}

}
