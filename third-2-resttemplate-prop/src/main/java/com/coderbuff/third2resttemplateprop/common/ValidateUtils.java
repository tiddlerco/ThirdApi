package com.coderbuff.third2resttemplateprop.common;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author yuke
 */
public class ValidateUtils {

    /**
     * 判断字符串是否只包含字母和数字.
     *
     * @param str 字符串
     * @return 如果字符串只包含字母和数字, 则返回 <code>ture</code>, 否则返回 <code>false</code>.
     */
    public final static boolean isAlphanumeric(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return false;
        }

        String regex = "[a-zA-Z0-9]+";
        return Pattern.matches(regex, str);
    }

    /**
     * 判断属性值是否合法
     *
     * @param str
     * @return
     */
    public final static boolean isPropValueValid(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return false;
        }
        String regex = "[A-Za-z0-9_-]+";
        return Pattern.matches(regex, str);
    }

    /**
     * 判断标识是否合法
     *
     * @param str
     * @return
     */
    public final static boolean isSignValid(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return false;
        }
        String regex = "^[A-Za-z][A-Za-z0-9_-]*$";
        return Pattern.matches(regex, str);
    }

    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     * <p>
     * <pre>
     *                     StringUtils.isBlank(null)                = true
     *                     StringUtils.isBlank(&quot;&quot;)        = true
     *                     StringUtils.isBlank(&quot; &quot;)       = true
     *                     StringUtils.isBlank(&quot;bob&quot;)     = false
     *                     StringUtils.isBlank(&quot;  bob  &quot;) = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public final static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否为中国移动手机号码.
     *
     * @param str
     * @return
     */
    public final static boolean isChinaMobile(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return false;
        }

        // Regex for checking ChinaMobile
        String regex = "1(3|5)[4-9]\\d{8}";
        return Pattern.matches(regex, str);
    }

    /**
     * 判断是否为小灵通手机(Personal Access Phone System, PAS).
     *
     * @param str 字符串
     * @return 如果是小灵通号码, 返回 <code>true</code>, 否则返回 <code>false</code>.
     */
    public final static boolean isChinaPAS(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return false;
        }

        // Deal with 013 & 015
        if (str.startsWith("013") || str.startsWith("015")) {
            return false;
        }

        // Regex for checking PAS
        String regex = "0\\d{9,11}";
        return Pattern.matches(regex, str);
    }

    /**
     * 是否为中国联通手机号码.
     *
     * @param str
     * @return
     */
    public final static boolean isChinaUnicom(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return false;
        }

        // Regex for checking ChinaMunicm
        String regex = "1(3|5)[0-3]\\d{8}";
        return Pattern.matches(regex, str);
    }

    /**
     * 是否是合法的日期字符串
     *
     * @param str 日期字符串
     * @return 是true，否则false
     */
//    public final static boolean isDate(String str) {
//        if (isEmpty(str) || str.length() > 10) {
//            return false;
//        }
//
//        String[] items = str.split("-");
//
//        if (items.length != 3) {
//            return false;
//        }
//
//        if (!isNumber(items[0], 1900, 9999) || !isNumber(items[1], 1, 12)) {
//            return false;
//        }
//
//        int year = Integer.parseInt(items[0]);
//        int month = Integer.parseInt(items[1]);
//
//        return isNumber(items[2], 1, DateUtils
//                .getMaxDayOfMonth(year, month - 1));
//    }

    /**
     * 是否是合法的日期时间字符串
     *
     * @param str 日期时间字符串
     * @return 是true，否则false
     */
//    public final static boolean isDateTime(String str) {
//        if (isEmpty(str) || str.length() > 20) {
//            return false;
//        }
//
//        String[] items = str.split(" ");
//
//        if (items.length != 2) {
//            return false;
//        }
//
//        return isDate(items[0]) && isTime(items[1]);
//    }

    /**
     * 是否是合法的电子邮箱
     *
     * @param str
     * @return
     */
    public final static boolean isEmail(String str) {
        return !isEmpty(str) && str.indexOf("@") > 0;
    }

    /**
     * 当数组为<code>null</code>, 或者长度为0, 或者长度为1且元素的值为<code>null</code>时返回
     * <code>true</code>.
     *
     * @param args
     * @return
     */
    public final static boolean isEmpty(Object[] args) {
        return args == null || args.length == 0
                || (args.length == 1 && args[0] == null);
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     * @return
     */
    public final static boolean isEmpty(Object object) {
        return object == null;
    }

    /**
     * 判断Collection是否为空
     *
     * @param collection
     * @return
     */
    public final static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断List是否为空
     *
     * @param list
     * @return
     */
    public final static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断Map是否为空
     *
     * @param map
     * @return
     */
    public final static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 字符串是否为Empty，null和空格都算是Empty
     *
     * @param str
     * @return
     */
    public final static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * <p>
     * Validating for ID card number.
     * </p>
     *
     * @param str string to be validated
     * @return If the str is valid ID card number return <code>true</code>,
     * otherwise return <code>false</code>.
     */
    public final static boolean isIdCardNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }

        // Regex for checking ID card number
        // 15位或18数字, 14数字加x(X)字符或17数字加x(X)字符才是合法的
        String regex = "(\\d{14}|\\d{17})(\\d|x|X)";
        return Pattern.matches(regex, str);
    }

    /**
     * 是否为手机号码, 包括移动, 联通, 小灵通等手机号码.
     *
     * @param str 字符串
     * @return 若是合法的手机号码返回 <code>true</code>, 否则返回 <code>false</code>.
     */
    public final static boolean isMobile(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return false;
        }

        // Regex for Mobile
        String regex = "(13\\d{9})|(0\\d{9,11})|(15\\d{9})";
        return Pattern.matches(regex, str) && !str.startsWith("013")
                && !str.startsWith("015");
    }

    /**
     * 是否为数字的字符串
     *
     * @param str
     * @return
     */
    public final static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }

        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是固定范围内的数字的字符串
     *
     * @param str
     * @param min
     * @param max
     * @return
     */
    public final static boolean isNumber(String str, long min, long max) {
        if (!isNumber(str)) {
            return false;
        }

        long number = Long.parseLong(str);
        return number >= min && number <= max;
    }

    /**
     * 判断字符是否为整数或浮点数. <br>
     *
     * @param str 字符
     * @return 若为整数或浮点数则返回 <code>true</code>, 否则返回 <code>false</code>
     */
    public final static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }

        String regex = "(\\+|-){0,1}(\\d+)([.]?)(\\d*)"; // 整数或浮点数
        return Pattern.matches(regex, str);
    }

    /**
     * 判断字符是否为符合精度要求的整数或浮点数. <br>
     *
     * @param str         字符
     * @param fractionNum 小数部分的最多允许的位数
     * @return 若为整数或浮点数则返回 <code>true</code>, 否则返回 <code>false</code>
     */
    public final static boolean isNumeric(String str, int fractionNum) {
        if (isEmpty(str)) {
            return false;
        }

        // 整数或浮点数
        String regex = "(\\+|-){0,1}(\\d+)([.]?)(\\d{0," + fractionNum + "})";
        return Pattern.matches(regex, str);
    }

    /**
     * <p>
     * Validating for phone number.
     * </p>
     * e.g. <li>78674585 --> valid</li> <li>6872-4585 --> valid</li> <li>
     * (6872)4585 --> valid</li> <li>0086-10-6872-4585 --> valid</li> <li>
     * 0086-(10)-6872-4585 --> invalid</li> <li>0086(10)68724585 --> invalid</li>
     *
     * @param str string to be validated
     * @return If the str is valid phone number return <code>true</code>,
     * otherwise return <code>false</code>.
     */
    public final static boolean isPhoneNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }

        // Regex for checking phone number
        String regex = "(([\\(（]\\d+[\\)）])?|(\\d+[-－]?)*)\\d+";
        return Pattern.matches(regex, str);
    }

    /**
     * 判断是否是合法的邮编
     *
     * @param str
     * @return
     */
    public final static boolean isPostcode(String str) {
        if (isEmpty(str)) {
            return false;
        }

        if (str.length() != 6 || !ValidateUtils.isNumber(str)) {
            return false;
        }

        return true;
    }

    /**
     * 判断是否是固定长度范围内的字符串
     *
     * @param str
     * @param minLength
     * @param maxLength
     * @return
     */
    public final static boolean isString(String str, int minLength, int maxLength) {
        if (str == null) {
            return false;
        }

        if (minLength < 0) {
            return str.length() <= maxLength;
        } else if (maxLength < 0) {
            return str.length() >= minLength;
        } else {
            return str.length() >= minLength && str.length() <= maxLength;
        }
    }

    /**
     * 判断是否是合法的时间字符串
     *
     * @param str
     * @return
     */
    public final static boolean isTime(String str) {
        if (isEmpty(str) || str.length() > 8) {
            return false;
        }

        String[] items = str.split(":");

        if (items.length != 2 && items.length != 3) {
            return false;
        }

        for (int i = 0; i < items.length; i++) {
            if (items[i].length() != 2 && items[i].length() != 1) {
                return false;
            }
        }

        return !(!isNumber(items[0], 0, 23) || !isNumber(items[1], 0, 59) || (items.length == 3 && !isNumber(
                items[2], 0, 59)));
    }

    /**
     * 返回记录ID是否有效
     *
     * @param id
     * @return
     */
    public static boolean isValidRecordId(Long id) {
        if (id != null && id.longValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回记录ID是否有效
     *
     * @param id
     * @return
     */
    public static boolean isValidRecordId(Integer id) {
        if (id != null && id.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFilePath(String filePath) {
        String regex = "(^//.|^/|^[a-zA-Z])?:?/.+(/$)?";
        return Pattern.matches(regex, filePath);
    }

    public static boolean isServiceExpress(String express) {
        if (ValidateUtils.isEmpty(express)) {
            return false;
        }

        String regex = "^[a-z][A-Za-z0-9]*[.][A-Za-z0-9_]+[(][A-Za-z0-9_]+[)]$";
        return Pattern.matches(regex, express);
    }

    public static boolean checkParam(Object... params) {
        for (Object p : params) {
            if (isEmpty(p)) {
                return false;
            }
        }
        return true;
    }


    private static final Pattern IPV4_PATTERN =
            Pattern.compile(
                    "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    private static final Pattern IPV6_STD_PATTERN =
            Pattern.compile(
                    "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN =
            Pattern.compile(
                    "^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    public static boolean isIPv4Address(final String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6StdAddress(final String input) {
        return IPV6_STD_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6HexCompressedAddress(final String input) {
        return IPV6_HEX_COMPRESSED_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6Address(final String input) {
        return isIPv6StdAddress(input) || isIPv6HexCompressedAddress(input);
    }

    public static void main(String[] args) {
        System.out.println(isServiceExpress("sfasdf.weasre(sss)"));
    }

}
