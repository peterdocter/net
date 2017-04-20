package com.java.crownlu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lizhimin on 16/1/20.
 */
public class InteligentShowModel implements Comparable{
    //字段含义WIKI http://wiki.sankuai.com/pages/viewpage.action?pageId=400207322

    public static final String PAYBUTTON = "1";
    public static final String QRCODE = "2";
    public static final String WAIMAI_A = "31"; //时间加文字
    public static final String WAIMAI_B = "32"; //配送超时
    public static final String SMTAGURL = "http://p1.meituan.net/groupop/43a59410808bcaa77c18441754b59c8b4282.png";
    public static final String POITAGURL = "http://p0.meituan.net/groupop/88bb357244df1b7f38fc125843435fd03059.png";
    public static final String MOVIETAGURL = "http://p1.meituan.net/groupop/2cb150d0287c196bbb51c3e51d1cda922099.png";
    public static final String WAIMAITAGURL = "http://p1.meituan.net/groupop/b7069e8643c9b9bd4960c2ed7c52ec312337.png";
    public static final String SMTAGURLV1 = "http://p1.meituan.net/groupop/7dbd111000ffa9af59c482c08781b3492964.png";
    public static final String POITAGURLV1 = "http://p1.meituan.net/groupop/e5d0f27a370e952c4b54911ad36e69fb2798.png";
    public static final String MOVIETAGURLV1 = "http://p1.meituan.net/groupop/f5d35dc914ec9e218ca67f06299695381958.png";
    public static final String WAIMAITAGURLV1 = "http://p0.meituan.net/groupop/63aedd88e83322c6ff3cd27d972011972038.png";
    public static final String ICON3URL = "http://p0.meituan.net/groupop/aead06d007c0f53f976367dd6deba24215271.png";
    private String type;
    private String id;
    private String title;
    private String titleColor;
    private String subtitle1;
    private String subtitle1Color;
    private String subtitle2;
    private String subtitle2Color;
    private String subtitle3;
    private String subtitle3Color;
    private String subtitle4;
    private String subtitle4Color;
    private String subtitle5;
    private String subtitle5Color;
    private String subtitle6;
    private String subtitle6Color;
    private String attachedInfo;
    private String attachedInfoColor;
    private String text1;
    private String text1Color;
    private String text2;
    private String text2Color;
    private String text3;
    private String text3Color;
    private String icon3Url;
    private String icon4Url;
    private String icon4Color;
    private String icon4Text;
    private String icon4TextColor;
    private String tagImgUrl;
    private String frontImgUrl;
    private String leftClickUrl;
    private String rightClickUrl;
    private String rightClickOpt = "1";
    private String rightClickContent;
    private int poiDistance;
    private int courierDistance;
    private String iconShowType;
    private String animationType;
    private String poiPhoneNum;
    private String QRIconUrl; //er wei ma de icon
    private String QRIconText; //er wei ma di bu de wen zi
    private transient Integer inMallId;
    private int slideTime = 6000;
    private transient int weight;  //每一种业务的权重，需要根据权重进行排序。电影>外卖>shoppingmall>商家
    private transient boolean isConnWifi = false;
    private transient String lat;
    private transient String lng;
    private transient String poiType = "";
    private String reason = "";
    private String reasonColor = "#999999";//"#FF9E05";
    private transient long elapseSeconds = 0;
    private transient int wifi_strength = 0;
    private transient String wifi_name = "";
    private transient String wifi_mac = "";
    private transient double rating;
    private transient double avg_price;
    private transient int sales;
    private transient String hotelpoiid;
    private transient Double poiScore = -100.0;
    private transient int card_pos = 1;
    private transient Integer appear_times = 0;
    private transient Integer mark_numbers = 0;
    private String _imgShowCtr = "leftTopTag";
    private String leftTopTagUrl = "";
    List<Icon4V2> icon4V2List = new ArrayList<>();


    public InteligentShowModel(String type, boolean isNewTag) {
        this.type = type;
        this.iconShowType = "0";
        setTagUrl(isNewTag);
    }

    public void setTagUrl(boolean isNewTag) {
        switch (type) {
            case "sm" : {
                this.tagImgUrl = SMTAGURL;
                if(isNewTag) {
                    this.tagImgUrl = SMTAGURLV1;
                }
                this.weight = 200;
                break;
            }
            case "poi" : {
                this.tagImgUrl = POITAGURL;
                if(isNewTag) {
                    this.tagImgUrl = POITAGURLV1;
                }
                this.weight = 100;
                break;
            }
            case "movie" : {
                this.tagImgUrl = MOVIETAGURL;
                if(isNewTag) {
                    this.tagImgUrl = MOVIETAGURLV1;
                }
                this.weight = 900;
                break;
            }
            case "waimai" : {
                this.tagImgUrl = WAIMAITAGURL;
                if(isNewTag) {
                    this.tagImgUrl = WAIMAITAGURLV1;
                }
                this.iconShowType = WAIMAI_A; //暂时不会显示配送超时
                this.weight = 600;
                break;
            }
            case "coupon" : {
                this.tagImgUrl = "http://p0.meituan.net/groupop/1d197cff0bc9256697611ade134473852527.png";
                this.weight = 400;
                this.iconShowType = "4";
                rightClickOpt = "2";
                break;
            }
            case "hotelroute" : {
                this.tagImgUrl = "http://p1.meituan.net/feop/0e5f255364daf16546770751c9350dce2191.png";
                this.weight = -2000;
                this.iconShowType = "4";
                rightClickOpt = "1";
                break;
            }
            case "recwm" : {
                this.tagImgUrl = WAIMAITAGURL;
                if(isNewTag) {
                    this.tagImgUrl = "http://p0.meituan.net/feop/559a6a910fddee474cf2adf0b60edfcd782.png";
                }
                this.iconShowType = WAIMAI_A; //暂时不会显示配送超时
                this.weight = -1000;
                break;
            }
            case "okwifi" : {
                this.tagImgUrl = "";
                this.iconShowType = "4"; //暂时不会显示配送超时
                this.weight = 100;
                break;
            }
            case "tktrain" : {
                this.tagImgUrl = "";
                this.iconShowType = "4"; //暂时不会显示配送超时
                this.weight = -2000;
                break;
            }
            default:
                this.tagImgUrl = "";
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setIconShowType(String iconShowType) {
        this.iconShowType = iconShowType;
    }

    public String getIconShowType() {
        return iconShowType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public String getSubtitle1() {
        return subtitle1;
    }

    public String getSubtitle1Color() {
        return subtitle1Color;
    }

    public String getSubtitle2() {
        return subtitle2;
    }

    public String getSubtitle2Color() {
        return subtitle2Color;
    }

    public String getSubtitle3() {
        return subtitle3;
    }

    public String getSubtitle3Color() {
        return subtitle3Color;
    }

    public String getSubtitle4() {
        return subtitle4;
    }

    public String getSubtitle4Color() {
        return subtitle4Color;
    }

    public String getSubtitle5() {
        return subtitle5;
    }

    public String getSubtitle5Color() {
        return subtitle5Color;
    }

    public String getSubtitle6() {
        return subtitle6;
    }

    public String getSubtitle6Color() {
        return subtitle6Color;
    }

    public String getAttachedInfo() {
        return attachedInfo;
    }

    public String getAttachedInfoColor() {
        return attachedInfoColor;
    }

    public String getText1() {
        return text1;
    }

    public String getText1Color() {
        return text1Color;
    }

    public String getText2() {
        return text2;
    }

    public String getText2Color() {
        return text2Color;
    }

    public String getText3() {
        return text3;
    }

    public String getText3Color() {
        return text3Color;
    }

    public String getIcon3Url() {
        return icon3Url;
    }

    public String getIcon4Url() {
        return icon4Url;
    }

    public String getIcon4Color() {
        return icon4Color;
    }

    public String getIcon4Text() {
        return icon4Text;
    }

    public String getIcon4TextColor() {
        return icon4TextColor;
    }

    public String getTagImgUrl() {
        return tagImgUrl;
    }

    public String getFrontImgUrl() {
        return frontImgUrl;
    }

    public String getRightClickContent() {
        return rightClickContent;
    }

    public int getCourierDistance() {
        return courierDistance;
    }

    public String getPoiPhoneNum() {
        return poiPhoneNum;
    }

    public String getQRIconUrl() {
        return QRIconUrl;
    }

    public String getQRIconText() {
        return QRIconText;
    }

    public int getSlideTime() {
        return slideTime;
    }

    public boolean isConnWifi() {
        return isConnWifi;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public void setSubtitle1(String subtitle1) {
        this.subtitle1 = subtitle1;
    }

    public void setSubtitle1Color(String subtitle1Color) {
        this.subtitle1Color = subtitle1Color;
    }

    public void setSubtitle2(String subtitle2) {
        this.subtitle2 = subtitle2;
    }

    public void setSubtitle2Color(String subtitle2Color) {
        this.subtitle2Color = subtitle2Color;
    }

    public void setSubtitle3(String subtitle3) {
        this.subtitle3 = subtitle3;
    }

    public void setSubtitle3Color(String subtitle3Color) {
        this.subtitle3Color = subtitle3Color;
    }

    public void setSubtitle4(String subtitle4) {
        this.subtitle4 = subtitle4;
    }

    public void setSubtitle4Color(String subtitle4Color) {
        this.subtitle4Color = subtitle4Color;
    }

    public void setSubtitle5(String subtitle5) {
        this.subtitle5 = subtitle5;
    }

    public void setSubtitle5Color(String subtitle5Color) {
        this.subtitle5Color = subtitle5Color;
    }

    public void setSubtitle6(String subtitle6) {
        this.subtitle6 = subtitle6;
    }

    public void setSubtitle6Color(String subtitle6Color) {
        this.subtitle6Color = subtitle6Color;
    }

    public void setAttachedInfo(String attachedInfo) {
        this.attachedInfo = attachedInfo;
    }

    public void setAttachedInfoColor(String attachedInfoColor) {
        this.attachedInfoColor = attachedInfoColor;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public void setText1Color(String text1Color) {
        this.text1Color = text1Color;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public void setText2Color(String text2Color) {
        this.text2Color = text2Color;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public void setText3Color(String text3Color) {
        this.text3Color = text3Color;
    }

    public void setIcon3Url(String icon3Url) {
        this.icon3Url = icon3Url;
    }

    public void setFrontImgUrl(String frontImgUrl) {
        this.frontImgUrl = frontImgUrl;
    }

    public void setLeftClickUrl(String leftClickUrl) {
        this.leftClickUrl = leftClickUrl;
    }

    public void setRightClickUrl(String rightClickUrl) {
        this.rightClickUrl = rightClickUrl;
    }

    public void setRightClickOpt(String rightClickOpt) {
        this.rightClickOpt = rightClickOpt;
    }

    public String getLeftClickUrl() {
        return leftClickUrl;
    }

    public String getRightClickUrl() {
        return rightClickUrl;
    }

    public String getRightClickOpt() {
        return rightClickOpt;
    }

    public void setRightClickContent(String rightClickContent) {
        this.rightClickContent = rightClickContent;
    }

    public void setPoiDistance(int poiDistance) {
        this.poiDistance = poiDistance;
    }

    public int getPoiDistance() {
        return poiDistance;
    }

    public void setCourierDistance(int courierDistance) {
        this.courierDistance = courierDistance;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAnimationType(String animationType) {
        this.animationType = animationType;
    }

    public String getAnimationType() {
        return animationType;
    }

    public void setPoiPhoneNum(String poiPhoneNum) {
        this.poiPhoneNum = poiPhoneNum;
    }

    public void setQRIconUrl(String QRIconUrl) {
        this.QRIconUrl = QRIconUrl;
    }

    public void setQRIconText(String QRIconText) {
        this.QRIconText = QRIconText;
    }

    public void setIcon4Color(String icon4Color) {
        this.icon4Color = icon4Color;
    }

    public void setIcon4Text(String icon4Text) {
        this.icon4Text = icon4Text;
    }

    public void setIcon4TextColor(String icon4TextColor) {
        this.icon4TextColor = icon4TextColor;
    }

    public void setIcon4Url(String icon4Url) {
        this.icon4Url = icon4Url;
    }

    public Integer getInMallId() {
        return inMallId;
    }

    public void setInMallId(Integer inMallId) {
        this.inMallId = inMallId;
    }

    public boolean getIsConnWifi() {
        return isConnWifi;
    }

    public void setIsConnWifi(boolean isConnWifi) {
        this.isConnWifi = isConnWifi;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonColor() {
        return reasonColor;
    }

    public void setReasonColor(String reasonColor) {
        this.reasonColor = reasonColor;
    }

    public long getElapseSeconds() {
        return elapseSeconds;
    }

    public void setElapseSeconds(long elapseSeconds) {
        this.elapseSeconds = elapseSeconds;
    }

    public int getWifi_strength() {
        return wifi_strength;
    }

    public void setWifi_strength(int wifi_strength) {
        this.wifi_strength = wifi_strength;
    }

    public String getWifi_name() {
        return wifi_name;
    }

    public void setWifi_name(String wifi_name) {
        this.wifi_name = wifi_name;
    }

    public String getWifi_mac() {
        return wifi_mac;
    }

    public void setWifi_mac(String wifi_mac) {
        this.wifi_mac = wifi_mac;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(double avg_price) {
        this.avg_price = avg_price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getHotelpoiid() {
        return hotelpoiid;
    }

    public void setHotelpoiid(String hotelpoiid) {
        this.hotelpoiid = hotelpoiid;
    }

    public Double getPoiScore() {
        return poiScore;
    }

    public void setPoiScore(Double poiScore) {
        this.poiScore = poiScore;
    }

    public int getCard_pos() {
        return card_pos;
    }

    public void setCard_pos(int card_pos) {
        this.card_pos = card_pos;
    }

    public Integer getAppear_times() {
        return appear_times;
    }

    public void setAppear_times(Integer appear_times) {
        this.appear_times = appear_times;
    }

    public Integer getMark_numbers() {
        return mark_numbers;
    }

    public void setMark_numbers(Integer mark_numbers) {
        this.mark_numbers = mark_numbers;
    }

    public void initReason() {
        if (type != null) {
            switch (type) {
                case "movie" : case "waimai" : {
                    this.reason = attachedInfo;
                    break;
                }
            }
        }
    }

    public String get_imgShowCtr() {
        return _imgShowCtr;
    }

    public void set_imgShowCtr(String _imgShowCtr) {
        this._imgShowCtr = _imgShowCtr;
    }

    public String getLeftTopTagUrl() {
        return leftTopTagUrl;
    }

    public void setLeftTopTagUrl(String leftTopTagUrl) {
        this.leftTopTagUrl = leftTopTagUrl;
    }

    @Override
    public int compareTo(Object o) {
        return ((InteligentShowModel) o).getWeight() - this.weight;
    }

    @Override
    public String toString() {
        return "InteligentShowModel{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", titleColor='" + titleColor + '\'' +
                ", subtitle1='" + subtitle1 + '\'' +
                ", subtitle1Color='" + subtitle1Color + '\'' +
                ", subtitle2='" + subtitle2 + '\'' +
                ", subtitle2Color='" + subtitle2Color + '\'' +
                ", subtitle3='" + subtitle3 + '\'' +
                ", subtitle3Color='" + subtitle3Color + '\'' +
                ", subtitle4='" + subtitle4 + '\'' +
                ", subtitle4Color='" + subtitle4Color + '\'' +
                ", subtitle5='" + subtitle5 + '\'' +
                ", subtitle5Color='" + subtitle5Color + '\'' +
                ", subtitle6='" + subtitle6 + '\'' +
                ", subtitle6Color='" + subtitle6Color + '\'' +
                ", attachedInfo='" + attachedInfo + '\'' +
                ", attachedInfoColor='" + attachedInfoColor + '\'' +
                ", text1='" + text1 + '\'' +
                ", text1Color='" + text1Color + '\'' +
                ", text2='" + text2 + '\'' +
                ", text2Color='" + text2Color + '\'' +
                ", text3='" + text3 + '\'' +
                ", text3Color='" + text3Color + '\'' +
                ", icon3Url='" + icon3Url + '\'' +
                ", icon4Url='" + icon4Url + '\'' +
                ", icon4Color='" + icon4Color + '\'' +
                ", icon4Text='" + icon4Text + '\'' +
                ", icon4TextColor='" + icon4TextColor + '\'' +
                ", tagImgUrl='" + tagImgUrl + '\'' +
                ", frontImgUrl='" + frontImgUrl + '\'' +
                ", leftClickUrl='" + leftClickUrl + '\'' +
                ", rightClickUrl='" + rightClickUrl + '\'' +
                ", rightClickOpt='" + rightClickOpt + '\'' +
                ", rightClickContent='" + rightClickContent + '\'' +
                ", poiDistance=" + poiDistance +
                ", courierDistance=" + courierDistance +
                ", iconShowType='" + iconShowType + '\'' +
                ", animationType='" + animationType + '\'' +
                ", poiPhoneNum='" + poiPhoneNum + '\'' +
                ", QRIconUrl='" + QRIconUrl + '\'' +
                ", QRIconText='" + QRIconText + '\'' +
                ", inMallId=" + inMallId +
                ", slideTime=" + slideTime +
                ", weight=" + weight +
                ", isConnWifi=" + isConnWifi +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", poiType='" + poiType + '\'' +
                ", reason='" + reason + '\'' +
                ", reasonColor='" + reasonColor + '\'' +
                ", elapseSeconds=" + elapseSeconds +
                ", wifi_strength=" + wifi_strength +
                ", wifi_name='" + wifi_name + '\'' +
                ", wifi_mac='" + wifi_mac + '\'' +
                ", rating=" + rating +
                ", avg_price=" + avg_price +
                ", sales=" + sales +
                ", hotelpoiid='" + hotelpoiid + '\'' +
                ", poiScore=" + poiScore +
                ", card_pos=" + card_pos +
                ", appear_times=" + appear_times +
                ", mark_numbers=" + mark_numbers +
                ", _imgShowCtr='" + _imgShowCtr + '\'' +
                ", leftTopTagUrl='" + leftTopTagUrl + '\'' +
                ", icon4V2List=" + icon4V2List +
                '}';
    }

    public List<Icon4V2> getIcon4V2List() {
        return icon4V2List;
    }

    public void setIcon4V2List(List<Icon4V2> icon4V2List) {
        this.icon4V2List = icon4V2List;
    }

    public static class Icon4V2 {
        private String icon4Url = "";
        private String icon4Color = "";
        private String icon4Text = "";
        private String icon4TextColor = "";
        private String rightClickUrl = "";
        private String rightClickOpt = "1";
        private String rightClickContent = "";
        private String iconShowType = "";
        private String QRIconUrl = ""; //er wei ma de icon
        private String QRIconText = ""; //er wei ma di bu de wen zi
        private String menuText = "";
        private String menuIconUrl = "";
        private String buttonid = "1";
        private transient String type;
        private String text1 = "";
        private String text1Color = "";
        private String text2 = "";
        private String text2Color = "";
        private String text3 = "";

        public Icon4V2(){}

        public Icon4V2(String type){
            this.type = type;
            switch (type){
                case "sm" : {
                    setMenuIconUrl("");
                    break;
                }
                case "poi" : {
                    setMenuIconUrl("");
                    setMenuText("买单");
                    break;
                }
                case "movie" : {
                    setMenuIconUrl("");
                    setMenuText("取票");
                    break;
                }
                case "waimai" : {
                    setMenuIconUrl("");
                    break;
                }
                case "coupon" : {
                    setMenuIconUrl("");
                    setMenuText("验券");
                    break;
                }
                case "hotelroute" : {
                    setMenuIconUrl("");
                    setMenuText("查路线");
                    break;
                }
                case "recwm" : {
                    setMenuIconUrl("");
                    setMenuText("点菜");
                    break;
                }
                case "wifi" : {
                    setMenuIconUrl("");
                    setMenuText("连WiFi");
                    setButtonid("2");
                    break;
                }
            }
        }

        public String getIcon4Url() {
            return icon4Url;
        }

        public void setIcon4Url(String icon4Url) {
            this.icon4Url = icon4Url;
        }

        public String getIcon4Color() {
            return icon4Color;
        }

        public void setIcon4Color(String icon4Color) {
            this.icon4Color = icon4Color;
        }

        public String getIcon4Text() {
            return icon4Text;
        }

        public void setIcon4Text(String icon4Text) {
            this.icon4Text = icon4Text;
        }

        public String getIcon4TextColor() {
            return icon4TextColor;
        }

        public void setIcon4TextColor(String icon4TextColor) {
            this.icon4TextColor = icon4TextColor;
        }

        public String getRightClickUrl() {
            return rightClickUrl;
        }

        public void setRightClickUrl(String rightClickUrl) {
            this.rightClickUrl = rightClickUrl;
        }

        public String getRightClickOpt() {
            return rightClickOpt;
        }

        public void setRightClickOpt(String rightClickOpt) {
            this.rightClickOpt = rightClickOpt;
        }

        public String getRightClickContent() {
            return rightClickContent;
        }

        public void setRightClickContent(String rightClickContent) {
            this.rightClickContent = rightClickContent;
        }

        public String getIconShowType() {
            return iconShowType;
        }

        public void setIconShowType(String iconShowType) {
            this.iconShowType = iconShowType;
        }

        public String getQRIconUrl() {
            return QRIconUrl;
        }

        public void setQRIconUrl(String QRIconUrl) {
            this.QRIconUrl = QRIconUrl;
        }

        public String getQRIconText() {
            return QRIconText;
        }

        public void setQRIconText(String QRIconText) {
            this.QRIconText = QRIconText;
        }

        public String getMenuText() {
            return menuText;
        }

        public void setMenuText(String menuText) {
            this.menuText = menuText;
        }

        public String getButtonid() {
            return buttonid;
        }

        public void setButtonid(String buttonid) {
            this.buttonid = buttonid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText1() {
            return text1;
        }

        public void setText1(String text1) {
            this.text1 = text1;
        }

        public String getText2() {
            return text2;
        }

        public void setText2(String text2) {
            this.text2 = text2;
        }

        public String getText3() {
            return text3;
        }

        public void setText3(String text3) {
            this.text3 = text3;
        }

        public String getText1Color() {
            return text1Color;
        }

        public void setText1Color(String text1Color) {
            this.text1Color = text1Color;
        }

        public String getText2Color() {
            return text2Color;
        }

        public void setText2Color(String text2Color) {
            this.text2Color = text2Color;
        }

        public String getMenuIconUrl() {
            return menuIconUrl;
        }

        public void setMenuIconUrl(String menuIconUrl) {
            this.menuIconUrl = menuIconUrl;
        }
    }

}
