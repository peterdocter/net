package com.java.crownlu.wnys;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by crownlu on 17/2/16.
 */
public class DhidUtils {
    static String getdhid() throws Exception {
        String salt = "LQ9$ne@gH*Jq%KOL";
        Map<String, String> map = new TreeMap<>();

        map.put("capbssid", "d8:86:e6:6f:a8:7c");
        map.put("model", "Nexus+4");
        map.put("och", "wandoujia");
        map.put("appid", "0001");
        map.put("mac", "d8:86:e6:6f:a8:7c");
        map.put("wkver", "2.9.38");
        map.put("lang", "cn");
        map.put("capbssid", "test");
        map.put("uhid", "");
        map.put("st", "m");
        map.put("chanid", "guanwang");
        map.put("dhid", "");
        map.put("os", "android");
        map.put("scrs", "768");
        map.put("imei", "355136052333516");
        map.put("manuf", "LGE");
        map.put("osvercd", "19");
        map.put("ii", "355136052391516");
        map.put("osver", "5.0.2");
        map.put("pid", "initdev:commonswitch");
        map.put("misc", "google/occam/mako:4.4.4/KTU84P/1227136:user/release-keys");
        map.put("sign", "");
        map.put("v", "58");
        map.put("sim", "");
        map.put("method", "getTouristSwitch");
        map.put("scrl", "1184");
        map.put("sign", getSign(map, salt));

        String dhid;

        String response = sendRequest(map);
        JSONObject jsonObject = new JSONObject(response);
        AESUtils.p(jsonObject);
        JSONObject dev = jsonObject.optJSONObject("initdev");
        AESUtils.p(dev);
        dhid = dev.optString("dhid");
        return dhid;

    }

    static public String getSign(Map map, String salt) throws Exception {
        String value = "";
        for (Object o : map.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry) o;
            value += entry.getValue();
        }
        value += salt;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] digest = messageDigest.digest(value.getBytes("UTF-8"));
        BigInteger number = new BigInteger(1, digest);
        String md5 = number.toString(16);
        while (md5.length() < 32) {
            md5 = "0" + md5;
        }

        return md5.toUpperCase();
    }

    static public String sendRequest(Map params) throws Exception {
        URL url = new URL("http://wifiapi02.51y5.net/wifiapi/fa.cmd");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Host", "wifiapi02.51y5.net");
        httpURLConnection.setRequestProperty("Accept", "text/plain");
        httpURLConnection.connect();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
        bufferedWriter.write(getRequestData(params));
        bufferedWriter.flush();
        bufferedWriter.close();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String Line;
        while ((Line = bufferedReader.readLine()) != null) {
            response.append(Line).append("\n");
        }

        bufferedReader.close();
        httpURLConnection.disconnect();
        return response.toString();
    }

    static public String getRequestData(Map params) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : params.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry) o;
            stringBuilder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        int i = 0;
        while (i++ < 1)
            System.out.println(getdhid());
    }
}
