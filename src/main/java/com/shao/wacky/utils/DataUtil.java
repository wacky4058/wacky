package com.shao.wacky.utils;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author jackcooperz
 */
public class DataUtil {

    private static final Integer NEW_SCALE = 1;

    public static final String SPLIT_SYMBOL = ",";

    public static final String EMPTY_STR = "";

    public static final String NULL_STR = "null";

    public static final String UNDEFINED_STR = "undefined";

    public static final String UTF_8_CODE = "UTF-8";

    public static final String URL_REGEX = "/";

    public static final Integer ZERO = 0;

    public static final Long LONG_ZERO = 0L;

    public static final Integer INVALID_INT = -1;

    public static final Integer KB_SIZE = 1024;

    public static final String HTML_LINE_BREAK = "<br/>";

    public static final String SIMILAR_EM_START_TAG = "<em class='similar' id='\\d+_\\d+'>";

    /**
     * 非中文、字母、数字的符号集合
     */
    public static final String PUNCTUATION_PATTERN = "[^\\w\\d\\u4e00-\\u9fa5]+";

    public static Pattern pattern = Pattern.compile("\\.*?\\(\\d+\\)$");

    public static Pattern fileNamePattern = Pattern.compile("[\\\\/:\\*\\?\\\"<>\\|]");

    public static final String SBC_STR = "，。！？【】（）％＃＠＆１２３４５６７８９０‘’“”";

    public static final String DBC_STR = ",.!?[]()%#@&1234567890\'\'\"\"";

    public static boolean isNull(Object originalObj) {
        return null == originalObj;
    }

    public static boolean isNotNull(Object originalObj) {
        return !isNull(originalObj);
    }

    public static boolean isBlank(String originalStr) {
        return DataUtil.EMPTY_STR.equals(originalStr);
    }

    public static boolean isEmptyStr(String originalStr) {
        return isNull(originalStr) || "".equals(originalStr);
    }

    public static boolean isNotEmptyStr(String originalStr) {
        return !isEmptyStr(originalStr);
    }

    public static boolean isNotNullEmptyStr(String originalStr) {
        return !isEmptyStr(originalStr) && !NULL_STR.equals(originalStr);
    }

    public static boolean isNotUndefinedEmptyStr(String originalStr) {
        return !isNull(originalStr)
                && !isBlank(originalStr.trim())
                && !NULL_STR.equalsIgnoreCase(originalStr)
                && !UNDEFINED_STR.equalsIgnoreCase(originalStr);
    }

    public static boolean isNullEmptyStr(String originalStr) {
        return isEmptyStr(originalStr) || NULL_STR.equals(originalStr);
    }

    public static boolean isNotEqual(String originalData, String compareData) {
        return !originalData.equals(compareData);
    }

    public static boolean isNotEqual(Object originalData, Object compareData) {
        return !originalData.equals(compareData);
    }

    public static boolean isNotZeroNull(Object originalObj) {
        return !isNull(originalObj) && !ZERO.equals(originalObj);
    }

    public static boolean isNotZeroLongNull(Object originalObj) {
        return !isNull(originalObj) && !LONG_ZERO.equals(originalObj);
    }

    public static boolean isNullZeroLong(Object originalObj) {
        return isNull(originalObj) || LONG_ZERO.equals(originalObj);
    }

    public static boolean isZeroNull(Object originalObj) {
        return isNull(originalObj) || ZERO.equals(originalObj);
    }

    public static boolean isNotInvalidIntNull(Object originalObj) {
        return isNotZeroNull(originalObj) && !INVALID_INT.equals(originalObj);
    }

    /**
     * List 为空判断
     *
     * @param sourceList 源List
     * @return boolean
     */
    public static boolean isEmptyList(List sourceList) {
        return isNull(sourceList) || sourceList.isEmpty();
    }

    /**
     * List 非空判断
     *
     * @param sourceList 源List
     * @return boolean
     */
    public static boolean isNotEmptyList(List sourceList) {
        return !isEmptyList(sourceList);
    }

    public static BigDecimal parseStrToDecimal(String orignalStr) {
        return new BigDecimal(orignalStr);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static Double roundDouble(Double originalData) {
        BigDecimal bigDecimal = new BigDecimal(originalData);
        return bigDecimal.setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Double roundDouble(Double originalData, Integer newScale) {
        if (isZeroNull(newScale)) {
            return roundDouble(originalData);
        }
        BigDecimal bigDecimal = new BigDecimal(originalData);
        return bigDecimal.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String formatPlaceholder(String str, String... params) {
        if (DataUtil.isBlank(str)) {
            return "";
        }
        return MessageFormat.format(str, params);
    }

    public static String format(String target, Object... params) {
        if (target.contains("%s") && ArrayUtils.isNotEmpty(params)) {
            return String.format(target, params);
        }
        return target;
    }

    public static List<String> stringToStringList(String strArr, String regex) {
        return Arrays.stream(strArr.split(regex))
                .map(s -> s.trim())
                .collect(Collectors.toList());
    }


    public static String formatNum(String num, Boolean kBool) {
        StringBuffer sb = new StringBuffer();
        if (!StringUtils.isNumeric(num)) {
            return "0";
        }
        if (kBool == null) {
            kBool = false;
        }
        BigDecimal b0 = new BigDecimal("1000");
        BigDecimal b1 = new BigDecimal("10000");
        BigDecimal b2 = new BigDecimal("100000000");
        BigDecimal b3 = new BigDecimal(num);

        String formatNumStr = "";
        String nuit = "";
        // 以千为单位处理
        if (kBool) {
            if (b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1) {
                return "999+";
            }
            return num;
        }
        // 以万为单位处理
        if (b3.compareTo(b1) == -1) {
            sb.append(b3.toString());
        } else if ((b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1) || b3.compareTo(b2) == -1) {
            formatNumStr = b3.divide(b1).toString();
            nuit = "万";
        } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
            formatNumStr = b3.divide(b2).toString();
            nuit = "亿";
        }
        if (!"".equals(formatNumStr)) {
            int i = formatNumStr.indexOf(".");
            if (i == -1) {
                sb.append(formatNumStr).append(nuit);
            } else {
                i = i + 1;
                String v = formatNumStr.substring(i, i + 1);
                if (!"0".equals(v)) {
                    sb.append(formatNumStr.substring(0, i + 1)).append(nuit);
                } else {
                    sb.append(formatNumStr.substring(0, i - 1)).append(nuit);
                }
            }
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    /**
     * 去掉查重解析的无用标签
     */
    public static String removeUncheck(String content) {
        if (StringUtils.isNotEmpty(content)) {
            content = content.replaceAll("<uncheck type='catalog1'>", "");
            content = content.replaceAll("<uncheck type='catalog'>", "");
            content = content.replaceAll("<uncheck type='ref'>", "");
            content = content.replaceAll("<uncheck type='other'>", "");
            content = content.replaceAll("</uncheck>", "");
            content = content.replaceAll("</location>", "");
            content = content.replaceAll("<location.*?tag=.*?>", "");
        }
        return content;
    }

    public static Long KbToByte(Double fileSize) {
        if (DataUtil.isNull(fileSize)) {
            return 0l;
        } else {
            Double s = fileSize * KB_SIZE;
            return s.longValue();
        }
    }

    public static Double byteSizeToKb(Long byteLong) {
        if (byteLong == null) {
            return 0d;
        }
        return (double) byteLong / 1024;
    }

    /**
     * 保留两位小数的 四舍五入 法
     */
    public static Double byteSizeToMb(Long byteLong) {
        if (byteLong == null) {
            return 0d;
        }
        BigDecimal bigDecimal = new BigDecimal(byteLong).divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
        BigDecimal value = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return value.doubleValue();
    }


    public static String decodeString(String value) {
        if (DataUtil.isEmptyStr(value)) {
            return value;
        }
        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }


    /**
     * @param startIndex
     * @param elements
     * @param action
     * @param <T>
     */
    public static <T> void getForIndexAndElement(int startIndex, Iterable<? extends T> elements, BiConsumer<Integer, ? super T> action) {
        if (startIndex < 0) {
            startIndex = 0;
        }
        int index = 0;
        for (T element : elements) {
            index++;
            if (index <= startIndex) {
                continue;
            }
            action.accept(index - 1, element);
        }
    }

    public static String getTitlePrefix(String title) {
        Matcher matcher = pattern.matcher(title);
        if (matcher.find()) {
            String group = matcher.group();
            int index = title.lastIndexOf(group);
            if (index > -1) {
                return title.substring(0, index);
            }
        }
        return title;
    }

    public static boolean hasSeqNo(String title) {
        Matcher matcher = pattern.matcher(title);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static int getSeqNo(String title) {
        Matcher matcher = pattern.matcher(title);
        if (matcher.find()) {
            String group = matcher.group();
            String seq = group.replace("(", "").replace(")", "");
            return Integer.valueOf(seq);
        }
        return 0;
    }

    public static String createTitleSuffix(int seqNo) {
        return "(" + seqNo + ")";
    }


    /**
     * 转换年份
     */
    public static Integer convertYear(String value) {
        if (DataUtil.isNotEmptyStr(value)) {
            try {
                return Integer.valueOf(value);
            } catch (Exception e) {

            }
        }
        return null;
    }


    /**
     * 文件名是否合法   / \ " : | * ? < >
     */
    public static boolean isFileNameLegal(String value) {
        if (DataUtil.isNotEmptyStr(value)) {
            return !fileNamePattern.matcher(value).find();
        }
        return true;
    }

    public static String getFileTitle(String title) {
        if (DataUtil.isNull(title)) {
            return null;
        }
        int index = title.lastIndexOf(".");
        if (index == 0) {
            return title;
        }
        if (index > 0) {
            return title.substring(0, index);
        }
        return title;
    }

    public static int getLength(String str) {
        return str == null ? 0 : str.length();
    }


    public static String replaceToHtmlLineBreak(String str) {
        return str == null ? null : str.replaceAll("\n", HTML_LINE_BREAK);
    }

    public static String toDBC(String str) {
        char[] chars = str.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 12288) {
                chars[i] = 32;
            } else if (chars[i] >= 65281 && chars[i] <= 65374) {
                chars[i] -= 65248;
            } else if ((index = SBC_STR.indexOf(chars[i])) > 0) {
                chars[i] = DBC_STR.charAt(index);
            }
        }
        return new String(chars);
    }
}