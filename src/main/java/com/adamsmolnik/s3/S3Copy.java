package com.adamsmolnik.s3;

import static com.adamsmolnik.util.Util.BUCKET_NAME;
import static com.adamsmolnik.util.Util.S3;

public class S3Copy {

	public static void main(String[] args) throws Exception {
		long then = System.currentTimeMillis();
		S3.copyObject(BUCKET_NAME, "data.dat", BUCKET_NAME, "data-copy.dat");
		System.out.println(System.currentTimeMillis() - then);
	}

}
