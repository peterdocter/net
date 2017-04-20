package com.java.crownlu.wnys;


import com.java.crownlu.Test;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.python.util.PythonInterpreter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

import static com.java.crownlu.wnys.AESUtils.*;

/**
 * Created by crownlu on 17/2/16.
 */
public class PwdFetch {

    public static String getpwd(String bssid, String ssid) throws Exception{

        Map<String, String> map = new LinkedHashMap<>();
        map.put("origChanId", "xiaomi");
        map.put("appId", "A0008");
        map.put("ts", System.currentTimeMillis() + "");
        map.put("netModel", "w");
        map.put("chanId", "gssnwang11");
        map.put("imei", "497541051318147");
        map.put("qid", "");
        map.put("mac", "06:27:22:fb:b5:66");
        map.put("capSsid", "中午");
        map.put("lang", "cn");
        map.put("longi", "103.9857521");
        map.put("nbaps", "");
        map.put("capBssid", "b0:d5:9d:45:b9:87");
        map.put("bssid", bssid);
        map.put("mapSP", "t");
        map.put("userToken", "");
        map.put("verName", "4.1.8");
        map.put("ssid", ssid);
        map.put("verCode", "3028");
        map.put("uhid", "00000000130000000000000000000002");
        map.put("lati", "30.5795779");
        map.put("dhid", DhidUtils.getdhid());
        JSONObject dtJson = new JSONObject(map);
        String _dt = dtJson.toString();
        System.out.println(_dt);
        String dt = URLEncoder.encode(_dt, "utf-8");
        System.out.println(dt);
        p("%7B%22origChanId%22%3A%22xiaomi%22%2C%22appId%22%3A%22A0008%22%2C%22ts%22%3A%221487318147064%22%2C%22netModel%22%3A%22w%22%2C%22chanId%22%3A%22guanwang%22%2C%22imei%22%3A%22357541051318147%22%2C%22qid%22%3A%22%22%2C%22mac%22%3A%2206%3A27%3A22%3Afb%3Ab5%3A63%22%2C%22capSsid%22%3A%22hijack%22%2C%22lang%22%3A%22cn%22%2C%22longi%22%3A%22103.985752%22%2C%22nbaps%22%3A%22%22%2C%22capBssid%22%3A%22b0%3Ad5%3A9d%3A45%3Ab9%3A85%22%2C%22bssid%22%3A%2206%3A69%3A6c%3A8f%3Ac2%3Aed%22%2C%22mapSP%22%3A%22t%22%2C%22userToken%22%3A%22%22%2C%22verName%22%3A%224.1.8%22%2C%22ssid%22%3A%22Motel168%22%2C%22verCode%22%3A%223028%22%2C%22uhid%22%3A%22a0000000000000000000000000000001%22%2C%22lati%22%3A%2230.579577%22%2C%22dhid%22%3A%2274d19ed078f04a4689606be1ecc99fce%22%7D".equals(dt));
//
        String ed = AESUtils.encrypt(dt);
        p(ed.equals("27C6B45D6184F0FF3C8375CE448755C7B2E09E2EB66BBEAEC8029C0C5BB32244E45E6392081FC728E933EE062B9ECA1B8695A40195C0333BFAC531BB9680AC227BA58ED7FEC136DA8155F069A0BA28528051AE76E4B1C72BC12230320BBA6CF8B23061AE0DCFE7319A7AEDD3D97A0AF14C0C83B762D467421313CED14ABB715C9A1094CEBB90C83AF320A95F4B3AB75139F5DDD8C4B6FEB8497BC2261853E530F05A3A099DE506B68FF755DF5147174B851215EA9ADCA66C7403AD1269F5BF993F8E1F30C62F0CECF40C9419D9D35BA42E9EB6517FD2CF07AEBE65749D890727BBFF6F21827DF82B8BB074D629E8C69598C3F9F2825A99A7CEB1308C39A12ED15190B8AC991EC52918C62884790404F0934D6ECC71E91472DA60436C22F4F7FB63818949D9FDD2153C132D38E860E7EED81A75333DDC1B2144C378F966B480C7EA47A574CBA073FC1686A34919D1E6B2D4B29BE025CA708001CA2149D92B4735E1F121B3EA348B4175DC9BBAD10B24D363055C15A961A0BD01841CD9FD8A90AD94F2D84207B192655574D1AD57008C081F5BFA920BE8E4541BC1502DC4283E512CBD14400534493F637C6ED1C829279FBB54412A2DB8A65D61F85D31E14C0B089B3A1C4C723E41E215787CBFC9B38B22A1C691C4BBA95BB4A6B4D62DE0AF9A9BAD20043B48D2EBD6C40A5A66A223F109457399A3762AEBDB3BE3E031B6FFEEB1C9A6B120C223855E0261A4F58B93B7D9D982EBDDE5B2637EE69E857050522A135E5051980E3BCB16B869B6CE40529A6AA1518E5F74C2DB969DE59370EC934D92B1845D1F84146A6F4331936DABC7184ED443A846B672E650E327BAB6306B9877F3C0A25FC01B8EFCA10534E33BAE9CF216EC3D24DA8201DA9BBE9D33D94EDFD5BEB1F71677AC78C169F9B28ED7D48968622806430B2313AF33BDBBD1F77760D6C7B14A8C7DE545CF41FDB044A23DEAD1654C187068E58422E817D6B21D84224A011DEFFF46C079DCB85F70E59372B9F2DEDAAA846537AC9205B44B44C372A5FD2B9144791A0F000708EE601C82B78CA7090971257E62FA41BB02E7407582531B"));
        Map<String, Object> data = new TreeMap<>();
        data.put("appId", "A0008");
        data.put("pid", "00300109");
        data.put("ed", ed);
        data.put("st", "m");
        data.put("et", "a");
        String sign = getSign(data, "*Lm%qiOHVEedH3%A^uFFsZvFH9T8QAZe");
        data.put("sign", sign);
        p("sign=" + sign);
        String response = sendRequest(data);
        String pwd = getPwd(response);
        p("pwd=" + pwd);
        pwd = parsePwd(AESUtils.decrypt(pwd));
        p("final pwd=" + pwd);
        return pwd;
    }

    public static String parsePwd(String pwd) {
        try {
            p("pwddecode=" + pwd);
            String lenStr = pwd.substring(0, 3);
            int len = Integer.parseInt(lenStr);
            p(len);
            return pwd.substring(3, len + 3);
        } catch (Exception E) {

        }
        return null;
    }

    public static String getPwd(String response) throws Exception {
        p("resp=" + response);
        if (response != null && !response.isEmpty()) {
            JSONObject ret = new JSONObject(response);
            if (ret != null) {
                JSONArray aps = ret.optJSONArray("aps");
                if (aps != null) {
                    JSONObject ele = aps.optJSONObject(0);
                    if (ele != null) {
                        return ele.optString("pwd");
                    }
                }
            }
        }
        return null;
    }

    public static String getSign(Map<String, Object> map, String salt) throws Exception {
        String value = "";
        List<String> l = new ArrayList<>(map.keySet());
        Collections.sort(l, (x1, x2) -> x1.compareTo(x2));
        for (String o : l) {
            value += map.get(o);
        }
        AESUtils.p("value=" + value);
        value += salt;
        return md5(value);
    }

    public static String md5(String str) {
        AESUtils.p(str);
        MessageDigest md;
        StringBuffer sb = new StringBuffer();
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] data = md.digest();
            AESUtils.p(new String(data, "UTF-8"));
            int index;
            for(byte b : data) {
                index = b;
                if(index < 0) index += 256;
                if(index < 16) sb.append("0");
                sb.append(Integer.toHexString(index));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String sendRequest(Map params) throws Exception{

        URL url = new URL("http://ap.51y5.net/ap/fa.sec");//"http://wifiapi02.51y5.net/wifiapi/fa.cmd");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
//        httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
//        httpURLConnection.setRequestProperty("Host", "wifiapi02.51y5.net");
//        httpURLConnection.setRequestProperty("Accept", "text/plain");
        httpURLConnection.connect();


        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
        bufferedWriter.write(getRequestData(params));
        bufferedWriter.flush();
        bufferedWriter.close();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String Line;
        while ((Line = bufferedReader.readLine())!=null){
            response.append(Line).append("\n");
        }
        bufferedReader.close();
        httpURLConnection.disconnect();
        return response.toString();
    }

    public static String getRequestData(Map params) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : params.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry) o;
            stringBuilder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();

    }

    public static void main(String [] args) throws  Exception {
        p(URLDecoder.decode(getpwd("84:82:F4:28:32:F1", "都小火锅"), "UTF-8"));
        //p(new String("E40256939ABE47E2152A83124852B1E88C37F87083A39770C483A8EBC54F93B2".getBytes(), "UTF-8"));
//        String s = "010W2Y6G442841487246451054";
//        p(parsePwd(s));
//        String content = "99791067EC54DFE030DD8C673E9CB83BE713F38068C3CA3AE9CE06374AF1D417";
//        byte[] pwd = Hex.decodeHex(content.toCharArray());
//        p(new String(pwd, "UTF-8"));
//        byte[] str = FileUtils.readFileToByteArray(new File("/Users/crownlu/Downloads/t_w.txt"));
//        p(str.length + " " + new String(str));
//        String s = AESUtils.decrypt(str);
//        p(s.length() + " " + s);
        //        AESUtils.p("?yg?T??0\u074Cg>??;??h??:??7J??");
//        AESUtils.p(new String(hex2Bytes1(content)));
//        AESUtils.p(AESUtils.hex2Str(content));
//        AESUtils.p(decrypt(content));
//        AESUtils.p(AESUtils.decrypt("?yg?T??0\u074Cg>??;??h??:??7J??"));
//        AESUtils.p(AESUtils.decrypt(AESUtils.hex2Str(content)));
        //interpreter.exec("");
        //interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
    }
}
