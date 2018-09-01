package com.adamsmolnik.s3.lambda;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class S3EncryptionTest implements RequestHandler<Map<String, ?>, String> {

	@Override
	public String handleRequest(Map<String, ?> input, Context context) {

		return null;
	}

}
