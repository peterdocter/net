package com.java.crownlu;


import com.java.crownlu.wnys.AESUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.java.crownlu.wnys.AESUtils.*;

/**
 * Created by crownlu on 17/2/15.
 */
public class Test {
    private static final double PI = Math.PI;
    private static final double a = 6378245.0;
    private static final double ee = 0.00669342162296594323;

    public static String getdhid() throws Exception {
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

//        JSONObject jsonObject = new JSONObject(response);
//
//        dhid = jsonObject.getJSONObject("initdev").getString("dhid");

        return response;

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

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * PI) + 40.0 * Math.sin(y / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * PI) + 320 * Math.sin(y * PI / 30.0)) * 2.0 / 3.0;


        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * PI) + 40.0 * Math.sin(x / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * PI) + 300.0 * Math.sin(x / 30.0 * PI)) * 2.0 / 3.0;

        return ret;
    }

    private static double[] gps2Mars(double[] locs) {
        double wgLat = locs[0];
        double wgLon = locs[1];// out double mgLat, out double mgLon


        double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
        double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
        double radLat = wgLat / 180.0 * PI;
        double magic = Math.sin(radLat);

        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * PI);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * PI);

        locs[0] = wgLat + dLat;
        locs[1] = wgLon + dLon;

        return locs;
    }

    public static double[] convertGps2Mars(double gpslat, double gpslng) {

        double[] gpsCoords = new double[2];
        gpsCoords[0] = gpslat;
        gpsCoords[1] = gpslng;
        double[] coords = gps2Mars(gpsCoords);
        if (coords == null) {
            return gpsCoords;
        } else {
            return coords;
        }
    }

    public static void main(String[] args) throws Exception {
//        double[] d = convertGps2Mars(40.0073, 116.481054);
//        p(d[0] + "," + d[1]);
//        double d = 116.486774;
//        int i = (int) (d * 1000000);
        LocalDate ld = LocalDate.parse("2017-04-02");
        LocalTime lt = LocalTime.parse("10:42:00");
        p(ld.getMonth().getValue() + "  " + lt.getHour());

//        List<String> lines = FileUtils.readLines(new File("/Users/crownlu/Downloads/mac.txt"));
//        List<List<String>> citys = new ArrayList<>();
//        List<String> cl = new ArrayList<>();
//        int sum = 0;
//        citys.add(cl);
//        //Collections.reverse(lines);
//        for (String l : lines) {
//            String [] a = l.split("\t");
//            System.out.print(a[0] + "," + a[1]);
//            Integer ci = Integer.valueOf(a[0].trim());
//            Integer cityID = Integer.valueOf(a[1].trim());
//            if (cityID < 2000) {
//                sum += ci;
//                if (sum > 1000000) {
//                    p(" " + (sum - ci));
//                    sum = ci;
//                    cl = new ArrayList<>();
//                    citys.add(cl);
//                }
//                cl.add(a[1]);
//            }
//        }
//
//        p("-----------------------------");
//        for (List<String> cc : citys) {
//            p(cc);
//        }
    }
}
