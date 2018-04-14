package com.honeylovely.center.common.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
	private final static int CONNECT_TIMEOUT = 10000; // in milliseconds
	private final static String DEFAULT_ENCODING = "UTF-8";

	public static String postData(String urlStr, String data) {
		return postData(urlStr, data, null);
	}

	public static String postData(String urlStr, String data, String contentType) {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setReadTimeout(CONNECT_TIMEOUT);
			if (contentType != null)
				conn.setRequestProperty("content-type", contentType);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
			if (data == null)
				data = "";
			writer.write(data);
			writer.flush();
			writer.close();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			return sb.toString();
		} catch (IOException e) {
			// logger.error("Error connecting to " + urlStr + ": " +
			// e.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
			}
		}
		return null;
	}

	public static byte[] getBytesFromFile(File file) throws IOException {

		InputStream is = new FileInputStream(file);

		// 获取文件大小

		long length = file.length();

		if (length > Integer.MAX_VALUE) {

			// 文件太大，无法读取

			throw new IOException("File is to large " + file.getName());

		}

		// 创建一个数据来保存文件数据

		byte[] bytes = new byte[(int) length];

		// 读取数据到byte数组中

		int offset = 0;

		int numRead = 0;

		while (offset < bytes.length

				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {

			offset += numRead;

		}

		// 确保所有数据均被读取

		if (offset < bytes.length) {

			throw new IOException("Could not completely read file " + file.getName());

		}

		// Close the input stream and return bytes

		is.close();

		return bytes;

	}
}
