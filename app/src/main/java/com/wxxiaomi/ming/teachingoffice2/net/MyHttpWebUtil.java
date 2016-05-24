package com.wxxiaomi.ming.teachingoffice2.net;





import android.util.Log;

import com.wxxiaomi.ming.teachingoffice2.ConstantValue;
import com.wxxiaomi.ming.teachingoffice2.bean.net.NetReceiverData;
import com.wxxiaomi.ming.teachingoffice2.bean.net.NetSendData;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


public class MyHttpWebUtil {

	public static NetReceiverData sendGet(NetSendData sendData) {
		NetReceiverData returnData = new NetReceiverData();
		URL url = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			url = new URL(sendData.getUrl());
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(20000);
			conn.setConnectTimeout(20000);
			conn.setRequestMethod("GET");
			if (sendData.getHeaders() != null
					&& !sendData.getHeaders().isEmpty()) {
				for (Map.Entry<String, String> entry : sendData.getHeaders()
						.entrySet()) {
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
//			conn.setRequestProperty("Cache-Control", "no-cache");
//			conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
//			conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, application/x-ms-application, application/xaml+xml, application/x-ms-xbap, */*");
//			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; InfoPath.3)");
			//
			if (conn.getResponseCode() == 200) {
				processResponseHeader(conn,returnData.getHeaders(),sendData.getHeaders());
				
				is = conn.getInputStream();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] bs = new byte[1024];
				int len = -1;
				while ((len = is.read(bs)) != -1) {
					bos.write(bs, 0, len);

				}
				byte b[] = bos.toByteArray();
				bos.close();
				returnData.setFromUrl(sendData.getUrl());
				returnData.setContent(b);
				returnData.setStateCode(200);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
			}
			try {
				if (baos != null)
					baos.close();
			} catch (IOException e) {
			}
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}

		return returnData;
	}

	public static NetReceiverData sendPost(NetSendData sendData) {
		NetReceiverData returnData = new NetReceiverData();
		Log.i("wang",sendData.toString());
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		HttpURLConnection conn = null;
		try {
			URL realUrl = new URL(sendData.getUrl());
			// 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setReadTimeout(8000);
			conn.setConnectTimeout(8000);
			conn.setInstanceFollowRedirects(false);
			if (sendData.getHeaders() != null
					&& !sendData.getHeaders().isEmpty()) {
				for (Map.Entry<String, String> entry : sendData.getHeaders()
						.entrySet()) {
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			conn.setRequestProperty("Cache-Control", "no-cache");
			if (sendData.getParmars() != null
					&& !sendData.getParmars().isEmpty()) {
				String params = "";
				int i = 0;
				for (Map.Entry<String, String> entry : sendData.getParmars()
						.entrySet()) {
					if (i == 0) {
						params = entry.getKey() + "=" + entry.getValue();
					} else {
						params += "&" + entry.getKey() + "=" + entry.getValue();
					}
					i++;
				}
				out = new PrintWriter(conn.getOutputStream());
				// 发送请求参数
				out.print(params);
				// flush输出流的缓冲
				out.flush();
			}else if(sendData.getPars() !=""){
//				System.out.println("参数:"+sendData.getPars());
				out = new PrintWriter(conn.getOutputStream());
				// 发送请求参数
				out.print(sendData.getPars());
				// flush输出流的缓冲
				out.flush();
			}

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				Log.i("wang","line="+line);
				result += line;
			}
			if (conn.getResponseCode() == 200) {
				Log.i("wang","200");
				processResponseHeader(conn, returnData.getHeaders(),
						sendData.getHeaders());
				returnData.setFromUrl(sendData.getUrl());
				returnData.setContent(result.getBytes("utf-8"));
				returnData.setStateCode(200);
			} else if (conn.getResponseCode() == 302) {
				Log.i("wang","302");
				NetSendData Data302 = new NetSendData();
				processResponseHeader(conn, Data302.getHeaders(),
						sendData.getHeaders());
					Data302.getHeaders().put("Referer", sendData.getUrl());

				returnData.setFromUrl(sendData.getUrl());
				String temp = conn.getHeaderField("Location");
//				temp = URLEncoder.encode(temp,"gb2312");
//				Data302.setUrl(sendData.getHost() + temp);
				Data302.setUrl(ConstantValue.LIBHOST + temp);
				return sendGet(Data302);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
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
			if (conn != null) {
				conn.disconnect();
			}

		}

		return returnData;
	}

	/**
	 * 处理服务器返回的conn中的头部包含Set-Cookie的情况
	 * 要取出并把它封装到returnData的header中，即inMap
	 * @param http
	 * @param returnDataHeader
	 * @param sendDataHeader
	 * @throws UnsupportedEncodingException
	 */
	private static void processResponseHeader(HttpURLConnection http,
			Map<String, String> returnDataHeader, Map<String, String> sendDataHeader)
			throws UnsupportedEncodingException {
		if(sendDataHeader.containsKey("Cookie")){
			returnDataHeader.put("Cookie", sendDataHeader.get("Cookie"));
		}
		Map<String, String> header = getHttpResponseHeader(http);
		for (Map.Entry<String, String> entry : header.entrySet()) {
			String key = entry.getKey() != null ? entry.getKey() : "";
//			key = (key.split(";"))[0];
//			Log.i("wang","key="+key);
			if (key.equals("Set-Cookie")) {
				 String value = entry.getValue();
				value = (value.split(";"))[0];
				returnDataHeader.put("Cookie", returnDataHeader.get("Cookie") == null ? value
						: returnDataHeader.get("Cookie") + "; " + value );
			}
		}
	}

	private static Map<String, String> getHttpResponseHeader(
			HttpURLConnection http) throws UnsupportedEncodingException {
		Map<String, String> header = new LinkedHashMap<String, String>();
		for (int i = 0;; i++) {
			String mine = http.getHeaderField(i);
			if (mine == null)
				break;
			header.put(http.getHeaderFieldKey(i), mine);
		}
		return header;
	}
}
