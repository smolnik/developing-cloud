package com.adamsmolnik.s3;

import static com.adamsmolnik.util.Util.BUCKET_NAME;
import static com.adamsmolnik.util.Util.S3;

import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;

public class S3ListKeys {

	public static void main(String[] args) {
		ListObjectsV2Request r = new ListObjectsV2Request().withBucketName(BUCKET_NAME).withPrefix("2014/score/")
				.withDelimiter("/");
		ListObjectsV2Result resp = S3.listObjectsV2(r);
		resp.getObjectSummaries().forEach(s -> {
			System.out.println("key = " + s.getKey());
		});
		resp.getCommonPrefixes().forEach(cp -> {
			System.out.println("cp = " + cp);
		});
		test();
	}

	private static void test() {
		listObject("2014/");
		listObject("2014/score/", "/");
		listCommonPrefixes("2014/score/", "/");
	}

	private static void listObject(String prefix) {
		S3.listObjects(BUCKET_NAME, prefix).getObjectSummaries().forEach(s -> {
			System.out.println("key for prefix " + prefix + ": " + s.getKey());
		});
		System.out.println();
	}

	private static void listObject(String prefix, String delimeter) {
		ListObjectsV2Request r = new ListObjectsV2Request().withBucketName(BUCKET_NAME).withPrefix(prefix)
				.withDelimiter(delimeter);
		S3.listObjectsV2(r).getObjectSummaries().forEach(s -> {
			System.out.println("key for prefix " + prefix + " and delimeter " + delimeter + ": " + s.getKey());
		});
		System.out.println();
	}

	private static void listCommonPrefixes(String prefix, String delimeter) {
		ListObjectsV2Request r = new ListObjectsV2Request().withBucketName(BUCKET_NAME).withPrefix(prefix)
				.withDelimiter(delimeter);
		S3.listObjectsV2(r).getCommonPrefixes().forEach(s -> {
			System.out.println("common prefix for prefix " + prefix + " and delimeter " + delimeter + ": " + s);
		});
		System.out.println();
	}

}
