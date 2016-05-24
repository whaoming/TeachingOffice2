package com.wxxiaomi.ming.teachingoffice2.net;

import android.util.Log;


import com.wxxiaomi.ming.teachingoffice2.bean.net.NetReceiverData;
import com.wxxiaomi.ming.teachingoffice2.bean.net.NetSendData;
import com.wxxiaomi.ming.teachingoffice2.exception.MyException;

import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;


public class MyHttpUtil {
    public static NetReceiverData sendGet(NetSendData sendData) throws Exception {
        Log.i("wang", "get()方法中，sendData = " + sendData.toString());
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
//					Log.i("wang",Value());
                }
            }
            if (conn.getResponseCode() == 200) {
                Log.i("wang", "get()->200");
                processResponseHeader(conn, returnData.getHeaders(), sendData.getHeaders());

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
            Log.i("wang", "sendGet()方法捕获异常");
//			throw new OfficeException(OfficeException.CONNECT_ERROR);
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

    public static NetReceiverData sendPost(NetSendData sendData) throws Exception {
//		System.out.println("post的地址："+sendData.getUrl());
        Log.i("wang", "post请求中：" + sendData.toString());
        //Log.i("wang","post的地址："+sendData.getUrl());
        NetReceiverData returnData = new NetReceiverData();
        PrintWriter out = null;
        BufferedReader in = null;
        //String result = "";
        HttpURLConnection conn = null;
        try {

            URL realUrl = new URL(sendData.getUrl());
            Log.i("wang", "url=" + realUrl);
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
            //conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
            if (sendData.getHeaders() != null
                    && !sendData.getHeaders().isEmpty()) {
                for (Map.Entry<String, String> entry : sendData.getHeaders()
                        .entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                    //Log.i("wang","header中包含："+entry.getKey()+":"+entry.getValue());
                }
            }

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
                conn.getOutputStream().write(params.getBytes());
                conn.getOutputStream().flush();
            } else if (sendData.getPars() != null) {
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(sendData.getPars());
                // flush输出流的缓冲
                out.flush();
            } else {
                conn.setRequestProperty("Content-Length", "0");
            }

            int code = conn.getResponseCode();
            Log.i("wang", "code=" + code);

            if (code == 200) {

                InputStream is = (InputStream) conn.getContent();
                java.io.ByteArrayOutputStream baos =
                        new java.io.ByteArrayOutputStream();

                int buffer = 1024;
                byte[] b = new byte[buffer];
                int n = 0;
                while ((n = is.read(b, 0, buffer)) > 0) {
                    baos.write(b, 0, n);
                }
                String result = new String(baos.toByteArray(), "gb2312");
                Log.i("wang", "result=" + result);
                processResponseHeader(conn, returnData.getHeaders(),
                        sendData.getHeaders());
                returnData.setFromUrl(sendData.getUrl());
                returnData.setContent(result.getBytes("gb2312"));
                returnData.setStateCode(200);
            } else if (code == 302) {
                //System.out.println(302);
                Log.i("wang", "302");
                NetSendData Data302 = new NetSendData();
                processResponseHeader(conn, Data302.getHeaders(),
                        sendData.getHeaders());
                Data302.getHeaders().put("Referer", sendData.getUrl());
                returnData.setFromUrl(sendData.getUrl());
                String temp = conn.getHeaderField("Location");
                Data302.setUrl(sendData.getHost() + temp);
                return sendGet(Data302);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("MyHttpUtil,sendPost", MyException.CONNECT_ERROR);
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
//				System.out.println("ioexception");
                Log.i("wang", "访问网络的过程发生ioexception");
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
     *
     * @param http
     * @param returnDataHeader
     * @param sendDataHeader
     * @throws UnsupportedEncodingException
     */
    private static void processResponseHeader(HttpURLConnection http,
                                              Map<String, String> returnDataHeader, Map<String, String> sendDataHeader)
            throws UnsupportedEncodingException {
        if (sendDataHeader.containsKey("Cookie")) {
            returnDataHeader.put("Cookie", sendDataHeader.get("Cookie"));
        }
        Map<String, String> header = getHttpResponseHeader(http);
        for (Map.Entry<String, String> entry : header.entrySet()) {
            String key = entry.getKey() != null ? entry.getKey() : "";
            if (key.equals("Set-Cookie")) {
                returnDataHeader.put("Cookie", returnDataHeader.get("Cookie") == null ? entry.getValue()
                        : returnDataHeader.get("Cookie") + ";" + entry.getValue() + ";");
            }
        }
    }

    private static Map<String, String> getHttpResponseHeader(
            HttpURLConnection http) throws UnsupportedEncodingException {
        Map<String, String> header = new LinkedHashMap<String, String>();
        for (int i = 0; ; i++) {
            String mine = http.getHeaderField(i);
            if (mine == null)
                break;
            header.put(http.getHeaderFieldKey(i), mine);
        }
        return header;
    }
}
