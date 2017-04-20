package com.java.crownlu.wnys;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by crownlu on 17/2/16.
 */
public class AESUtils {

    public static String encrypt(String content) {
        try {
            content = wrapSpace(content);
            SecretKeySpec keyspec = new SecretKeySpec("!I50#LSSciCx&q6E".getBytes("utf-8"), "AES");
            IvParameterSpec ivspec = new IvParameterSpec("$t%s%12#2b474pXF".getBytes("utf-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] result = cipher.doFinal(content.getBytes());
            return bytes2Hex(result); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String content) {
        try {
            return decrypt(Hex.decodeHex(content.toCharArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(byte[] content) {
        try {
            SecretKeySpec keyspec = new SecretKeySpec("!I50#LSSciCx&q6E".getBytes("utf-8"), "AES");
            IvParameterSpec ivspec = new IvParameterSpec("$t%s%12#2b474pXF".getBytes("utf-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] result = cipher.doFinal(content);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dewrap(String content) {
        int j = content.length();
        int i = content.length();
        p(" j=" + j + " " + (16 + j % 16));
        while(i > 16 + j % 16) {
            content = content.substring(0, content.length() - 1);
            i = i - 1;
        }
        return content;
    }

    public static String hex2Str(String hex) throws Exception {
        byte[] output = new byte[hex.length()/2];
        for (int i = 0,j = 0; i < hex.length(); i += 2, j++) {
            String str = hex.substring(i, i + 2);
            output[j] = (byte) Integer.parseInt(str, 16);
        }
        return new String(output, "UTF-8");
    }

    public static String bytes2Hex(byte[] src){
        char[] res = new char[src.length*2];
        final char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        for(int i=0,j=0; i<src.length; i++){
            res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
            res[j++] = hexDigits[src[i] & 0x0f];
        }
        return new String(res);
    }

    public static String wrapSpace(String content) {
        int j = content.length();
        int i = 0;
        p(" j=" + j + " " + (16 - j % 16));
        while(i < 16 - j % 16) {
            content = content + ' ';
            i = i + 1;
        }
        return content;
    }

    public static void p(Object p) {
        System.out.println(p);
    }

    public static void main(String [] args) throws Exception {
        String test = encrypt("%7B%22origChanId%22%3A%22xiaomi%22%2C%22appId%22%3A%22A0008%22%2C%22ts%22%3A%22148723553749" +
                "1%22%2C%22netModel%22%3A%22w%22%2C%22chanId%22%3A%22guanwang%22%2C%22imei%22%3A%22357541051" +
                "318147%22%2C%22qid%22%3A%22%22%2C%22mac%22%3A%2206%3A27%3A22%3Afb%3Ab5%3A63%22%2C%22capSsid" +
                "%22%3A%22hijack%22%2C%22lang%22%3A%22cn%22%2C%22longi%22%3A%22103.985752%22%2C%22nbaps%22%3" +
                "A%22%22%2C%22capBssid%22%3A%22b0%3Ad5%3A9d%3A45%3Ab9%3A85%22%2C%22bssid%22%3A%22bsid%3Amac%" +
                "22%2C%22mapSP%22%3A%22t%22%2C%22userToken%22%3A%22%22%2C%22verName%22%3A%224.1.8%22%2C%22ss" +
                "id%22%3A%22wifiname%22%2C%22verCode%22%3A%223028%22%2C%22uhid%22%3A%22a00000000000000000000" +
                "00000000001%22%2C%22lati%22%3A%2230.579577%22%2C%22dhid%22%3A%22dhid%22%7D");
        String target = "27C6B45D6184F0FF3C8375CE448755C7B2E09E2EB66BBEAEC8029C0C5BB32244E45E6392081FC728E93" +
                "3EE062B9ECA1B8695A40195C0333BFAC531BB9680AC227BA58ED7FEC136DA8155F069A0BA2852EE2C64DDC9C582" +
                "A589E12246666E0AD5E82AA4B0CC29C61035F3CABB00A24A274E55632D7532D0E6B4BCCBC02FA93CA235E2DDB2F" +
                "3BBD09D7ADDF32B74C646099A055FE7FD97DBC87BFD26F14A231CF1E5A8D5445CA782BC2E08EFA1BCEA18C62315" +
                "29616CFE7ADFAE30D86536B8EDF41D3BF1BD733BEAF0BCFBBB57CB3E73878B9C4CA3D76622BBE841AD1CD499C88" +
                "0DF558F9BBD0B8FCFD74F457ABB9F41A10382CD50FC7658B247CF291C948F05954B213757B562A76FD12D010899" +
                "24085AE722BB65581DF27AB469713AD0104FBF354508792D7DF7F785C502D2C67C5697F8AB8F0FA8D09D4A77743" +
                "234C6E17840BACE391FDA0577440129EDF8AFF7C17A4518B306B5E883B9843818D4DDEAD4CC162B0698ADFC3609" +
                "00071ACF4173E40CB482D0FB07C94590B59872CE64E2498AD0A8E349DE91FFEE4117174FB1EB24A4B6DBEFC7880" +
                "6CC7B15CABEA81704A537E6147A42742969049E24B96645D7EAEB941D58FF8BAE0F1AC94C3262F965D45E8088EC" +
                "40EB0723077968DA537C420794CB2019EFFE1DA45A9BB3A00B6F17943D948D290A3564F84A4FB510D2A6AB9519C" +
                "402E0DAECCB52AA0489B1C6F6105B27D862041DC16456DC93E19BC4B3109F1F8CDA6E737F65D32D92B34C562BE7" +
                "A67F5BE67AC579853EBCAD9961A29490EA385612426117C3DF11DA675DD92045C911B6DB6CBD34D47AF24AB7869" +
                "59FC7EE44C71ACABA8AC644B482F851F9E638D46DA1A330B8CA8B117EE0C3C2EC1E8B41D5D88158BD4BD4B41C75" +
                "7F2E4AEF6BDAE51F192FC1D4A5F99F9E334124D769740F9D55CFE2372E64F6FA6600767AA0CAEC3E1E761BC8413" +
                "68A13D685A71EC98C0C57D23FD379F687CF1CE23A21D1E0A82E8ADA0B52453969641E94E2A8C49CDC56";
        p(test.equals(target));

        Map<String, Object> data = new TreeMap<>();
        data.put("appId", "A0008");
        data.put("pid", "00300109");
        data.put("ed", test);
        data.put("st", "m");
        data.put("et", "a");
        String sign = PwdFetch.getSign(data, "*Lm%qiOHVEedH3%A^uFFsZvFH9T8QAZe");
        data.put("sign", sign);
        AESUtils.p(PwdFetch.md5("A000827C6B45D6184F0FF3C8375CE448755C7B2E09E2EB66BBEAEC8029C0C5BB32244E45E6392081FC728E933EE062B9ECA1B8695A40195C0333BFAC531BB9680AC227BA58ED7FEC136DA8155F069A0BA2852640D9A31CCCA98900552072BA689EDE36AAA77380167C13A8B0614600753D091CDBF5AF9319A902FDFE8C2890D13AE971E14B81A0DD70B0876C2854AAE26023CC06B2130D69D9436F9B87D3A0AB3861BA16F5457C4EAEF29135FAF0FFBCD72FAC86114EEBB5540FDD6EDFAC3D04142916E33A381EFD00445555610E255F9D3CEA5089D4B5247EEA8B3B03E081DED36D0B5AEBE136B2D10B09F699DC92C3F0FC585BD90D92EB85350C82D794BC9204668AC490AA71C999F07B4600FFC803C30A9431F3F2D62A0C52B9EDDED0A5E42E765A7A21A088B7D8C23C59183318D137D1B67840C3FC84343197E0B1C878C2E9C9649B1B2687421F353252D3BBAC4CBA2B7D2C993F09229541C88185D28701E27FF6632406364751CF237F9FE38DD42BB1AD209130F30449B76016D38C3C5D073B7DFD6A5BAFE2A805BD08EEBBA8030E80E0B6502BA98BB3F2719368E657DB82256BE562C9EE6EA77EC24D1A81220825FFC50E1B566EE68939E1AE5BCEBBE0710E85F2F421F4F4C1CFE107BCEE33BF757AB0F47B0F2125A81B32D2F5D940CCEA18247C266D66BDB582639E266A482B7C0AE6589DFD058B33D311C406345B3CBB6E5C38A78450748A10A670B94615CE15E5DA6E80154C482CCA984A4969D5DE483C0B3A74E17DF57267B20C7696690B061E816D5E232C526BE3780D0E4CDA31058BA92DFF9D8CBF40895B760785202834582A73F64B6A07EC0B034D64B00DB10FE6EF8A182EBA10506D7ADC9B4E637EF5AD764A27F9A5EDAA5A4D8D3B300D2C8E89D8927C26C2C7F66DE170AECAF6500D8F8117D6FC3285D0EDFE8BBE393AF0048D48F973E9B5A20121C4A138CDCCCCD0EA8BC3C1AA468DCC4CDD3D405F73F7A8CAFAC62C65A8FB1BF8C3E05C1198D4B1614a00300109m*Lm%qiOHVEedH3%A^uFFsZvFH9T8QAZe"));
    }
}
