package com.xiandaojia.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class SendHttp {

	private final static SendHttp instance = new SendHttp();

	private SendHttp() {

	}

	public static SendHttp getInstance() {
		return instance;
	}

	@SuppressWarnings("unused")
	public String sendPost(String tUrl, String param) {
		BufferedWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(tUrl);
			URLConnection conn = realUrl.openConnection();
			if (conn == null)
				return "";
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			out.write(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
