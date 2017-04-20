/*
 * Copyright (c) 2010-2015 meituan.com
 * All rights reserved.
 * 
 */
package com.java.crownlu;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MathUtils {

    public static double round(double value, int scale) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    private static final double EARTH_RADIUS = 6371004;//地球半径,单位米

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点的经纬度计算两点之间的距离，单位是米
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 是否有效的position
     *
     * @param lat
     * @param lng
     * @return
     */
    public static boolean isValidPosition(double lat, double lng) {
        return !(lat > 90 || lat < -90 || lng > 180 || lng < -180);
    }

    public static String getFormatDistance(double lat1, double lng1, double lat2, double lng2) {
        double distance = getDistance(lat1, lng1, lat2, lng2);
        return formatDistance(distance);
    }

    public static String formatDistance(double distance) {
        if (distance/1000.0 > 100) {
            return String.format("%.0fkm", distance/1000.0);
        } else if (distance >= 1000) {
            return String.format("%.1fkm", distance/1000.0);
        } else if (distance >= 500) {
            return String.format("%.0fm", distance);
        } else if (distance > 0) {
            return "<500m";
        }
        return "";
    }

    public static Pair<Double, Double> toPosPair(String str) {
        if (StringUtils.isNotBlank(str) && str.contains(",")) {
            String [] arr =  StringUtils.split(str, ',');
            if (arr != null && arr.length > 1) {
                double d0 = toDouble(arr[0], -1);
                double d1 = toDouble(arr[1], -1);
                if (d0 < 0 || d1 < 0) {
                    return null;
                }
                return Pair.of(d0, d1);
            }
        }
        return null;
    }

    public static List<Pair<Double, Double>> mlls2List(String mlls) {
        if (StringUtils.isNotBlank(mlls)) {
            String [] arr = StringUtils.split(mlls, ';');
            if (arr != null && arr.length > 0) {
                List<Pair<Double, Double>> posList = new ArrayList<>();
                for (String str : arr) {
                    if (posList.size() > 100) {
                        break;
                    }
                    Pair<Double, Double> pos = toPosPair(str);
                    if (pos != null) {
                        posList.add(pos);
                    }
                }
                return posList;
            }
        }
        return Collections.emptyList();
    }

    public static List<Double> batchGetDistance(List<Pair<Double, Double>> posList, Pair<Double, Double> stdPos) {
        if(CollectionUtils.isNotEmpty(posList) && stdPos != null) {
            List<Double> distanceList = new ArrayList<>();
            for (Pair<Double, Double> pos : posList) {
                distanceList.add(getDistance(pos.getLeft(), pos.getRight(), stdPos.getLeft(), stdPos.getRight()));
            }
            return distanceList;
        }
        return Collections.emptyList();
    }

    public static String getDistanceStr(String mlls, String pos) {
        List<Pair<Double, Double>> posList = mlls2List(mlls);
        if (CollectionUtils.isNotEmpty(posList)) {
            Pair<Double, Double> stdPos = toPosPair(pos);
            List<Double> distanceList = batchGetDistance(posList, stdPos);
            if (CollectionUtils.isNotEmpty(distanceList)) {
                Double d = Collections.min(distanceList);
                if (d != null) {
                    return formatDistance(d);
                }
            }
        }
        return "";
    }

    public static Double getDistance(String mlls, String pos) {
        List<Pair<Double, Double>> posList = mlls2List(mlls);
        if (CollectionUtils.isNotEmpty(posList)) {
            Pair<Double, Double> stdPos = toPosPair(pos);
            List<Double> distanceList = batchGetDistance(posList, stdPos);
            if (CollectionUtils.isNotEmpty(distanceList)) {
                return Collections.min(distanceList);
            }
        }
        return -1d;
    }

    public static double toDouble(String value, double def) {
        if(!StringUtils.isBlank(value)) {
            try {
                Double t = Double.valueOf(value.trim());
                return t == null?def:t.doubleValue();
            } catch (Throwable var4) {
            }
        }

        return def;
    }

    public static void main(String [] args) {
        System.out.println(getDistance("40.129812,116.63", "40.004509,116.480895"));
        System.out.print(getDistance(40.129812,116.63, 40.004509,116.480895));
    }
}
