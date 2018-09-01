package com.adamsmolnik.s3;

import static com.adamsmolnik.util.Util.BUCKET_NAME;
import static com.adamsmolnik.util.Util.S3;
import static com.adamsmolnik.util.Util.copy;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.amazonaws.services.s3.model.CSVInput;
import com.amazonaws.services.s3.model.CSVOutput;
import com.amazonaws.services.s3.model.ExpressionType;
import com.amazonaws.services.s3.model.InputSerialization;
import com.amazonaws.services.s3.model.OutputSerialization;
import com.amazonaws.services.s3.model.SelectObjectContentRequest;

public class S3Select {

	public static void main(String[] args) throws Exception {
		String key = "sql.txt";
		String content = "julia;smolnik;20\nalicja;smolnik;25\nalpi;smolnik;11";
		S3.putObject(BUCKET_NAME, key, content);
		SelectObjectContentRequest sql = new SelectObjectContentRequest().withBucketName(BUCKET_NAME).withKey(key)
				.withExpression("SELECT s._1, s._2 , s._3 FROM S3Object s WHERE cast(s._3 as int) > 20")
				.withExpressionType(ExpressionType.SQL)
				.withInputSerialization(new InputSerialization().withCsv(new CSVInput().withFieldDelimiter(";")))
				.withOutputSerialization(new OutputSerialization().withCsv(new CSVOutput()));

		try (InputStream is = S3.selectObjectContent(sql).getPayload().getRecordsInputStream()) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			copy(is, os);
			System.out.println(os.toString(StandardCharsets.UTF_8.name()));
		}

	}

}
