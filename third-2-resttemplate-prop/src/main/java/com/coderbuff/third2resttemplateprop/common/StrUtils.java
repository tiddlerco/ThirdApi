package com.coderbuff.third2resttemplateprop.common;


import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author yuke
 * @Date 2022/7/21 9:58
 */
public class StrUtils {


    public static final String SEPARATOR_MULTI = ";";
    public static final String SEPARATOR_SINGLE = "#";
    public static final String SQL_REPLACE = "_";
    public static final String MULTI_FORAMT_SPLIT_REGEX = "[,，;； 　 ]";

    public static String getDefaultValue(String origin, String defaultValue) {
        if (origin == null) {
            return defaultValue;
        } else {
            return origin;
        }
    }

    /**
     * 在str右边加入足够多的addStr字符串
     *
     * @param str
     * @param addStr
     * @param length
     * @return
     */
    public final static String addStringRight(String str, String addStr, int length) {
        while (str.length() < length) {
            str = str + addStr;
        }
        return str;
    }

    /**
     * 在字符串str拼接分隔符regex和字符串sub
     *
     * @param str
     * @param sub
     * @param regex
     * @return
     */
    public final static String addSubString(String str, String sub, String regex) {
        return ValidateUtils.isEmpty(str) ? sub : str + regex + sub;
    }

    /**
     * 在字符串str右边补齐0直到长度等于length
     *
     * @param str
     * @param length
     * @return
     */
    public final static String addZeroRight(String str, int length) {
        return addStringRight(str, "0", length);
    }

    /**
     * 计算字符串str中字符sub的个数
     *
     * @param str
     * @param sub
     * @return
     */
    public final static int charCount(String str, char sub) {
        int charCount = 0;
        int fromIndex = 0;

        while ((fromIndex = str.indexOf(sub, fromIndex)) != -1) {
            fromIndex++;
            charCount++;
        }
        return charCount;
    }

    /**
     * 计算字符串str右边出现多少次sub
     *
     * @param str
     * @param sub
     * @return
     */
    public final static int charCountRight(String str, String sub) {
        if (str == null) {
            return 0;
        }

        int charCount = 0;
        String subStr = str;
        int currentLength = subStr.length() - sub.length();
        while (currentLength >= 0 && subStr.substring(currentLength).equals(sub)) {
            charCount++;
            subStr = subStr.substring(0, currentLength);
            currentLength = subStr.length() - sub.length();
        }
        return charCount;
    }

    /**
     * <p>
     * Counts how many times the substring appears in the larger String.
     * </p>
     *
     * <p>
     * A <code>null</code> or empty ("") String input returns <code>0</code>.
     * </p>
     *
     * <pre>
     *                                              StringUtils.countMatches(null, *)                           = 0
     *                                              StringUtils.countMatches(&quot;&quot;, *)                   = 0
     *                                              StringUtils.countMatches(&quot;abba&quot;, null)            = 0
     *                                              StringUtils.countMatches(&quot;abba&quot;, &quot;&quot;)    = 0
     *                                              StringUtils.countMatches(&quot;abba&quot;, &quot;a&quot;)   = 2
     *                                              StringUtils.countMatches(&quot;abba&quot;, &quot;ab&quot;)  = 1
     *                                              StringUtils.countMatches(&quot;abba&quot;, &quot;xxx&quot;) = 0
     * </pre>
     *
     * @param str the String to check, may be null
     * @param sub the substring to count, may be null
     * @return the number of occurrences, 0 if either String is
     * <code>null</code>
     */
    public final static int countMatches(String str, String sub) {
        if (ValidateUtils.isEmpty(str) || ValidateUtils.isEmpty(sub)) {
            return 0;
        }

        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    /**
     * 截取固定长度的字符串，超长部分用suffix代替，最终字符串真实长度不会超过maxLength
     *
     * @param str
     * @param maxLength
     * @param suffix
     * @return
     */
    public final static String cutOut(String str, int maxLength, String suffix) {
        if (ValidateUtils.isEmpty(str)) {
            return str;
        }

        int byteIndex = 0;
        int charIndex = 0;

        while (charIndex < str.length() && byteIndex <= maxLength) {
            char c = str.charAt(charIndex);
            if (c >= 256) {
                byteIndex += 2;
            } else {
                byteIndex++;
            }
            charIndex++;
        }

        if (byteIndex <= maxLength) {
            return str;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.substring(0, charIndex));
        stringBuilder.append(suffix);

        while (getRealLength(stringBuilder.toString()) > maxLength) {
            stringBuilder.deleteCharAt(--charIndex);
        }

        return stringBuilder.toString();
    }

    /**
     * 在字符串str左边补齐0直到长度等于length
     *
     * @param str
     * @param len
     * @return
     */
    public final static String enoughZero(String str, int len) {
        while (str.length() < len) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 用来显示异常信息的html过滤器
     *
     * @param value
     * @return
     */
    public final static String exceptionFilter(String value) {
        return ValidateUtils.isEmpty(value) ? "" : value.replaceAll("\n", "<br>").replaceAll("\t", "&nbsp; &nbsp; ");
    }

    /**
     * @param text 将要被格式化的字符串 <br>
     *             eg:参数一:{0},参数二:{1},参数三:{2}
     * @param args 将替代字符串中的参数,些参数将替换{X} <br>
     *             eg:new Object[] { "0001", "0005049", new Integer(1) }
     * @return 格式化后的字符串 <br>
     * eg: 在上面的输入下输出为:参数一:0001,参数二:0005049,参数三:1
     */
    public final static String format(String text, Object[] args) {
        if (ValidateUtils.isEmpty(text) || args == null || args.length == 0) {
            return text;
        }
        for (int i = 0, length = args.length; i < length; i++) {
            text = replace(text, "{" + i + "}", args[i].toString());
        }
        return text;
    }

    /**
     * 格式化浮点型数字成字符串, 保留两位小数位.
     *
     * @param number 浮点数字
     * @return 格式化之后的字符串
     */
    public final static String formatDecimal(double number) {
        NumberFormat format = NumberFormat.getInstance();

        format.setMaximumIntegerDigits(Integer.MAX_VALUE);
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);

        return format.format(number);
    }

    /**
     * 格式化浮点类型数据.
     *
     * @param number            浮点数据
     * @param minFractionDigits 最小保留小数位
     * @param maxFractionDigits 最大保留小数位
     * @return 将浮点数据格式化后的字符串
     */
    public final static String formatDecimal(double number, int minFractionDigits, int maxFractionDigits) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(minFractionDigits);
        format.setMaximumFractionDigits(minFractionDigits);

        return format.format(number);
    }

    /**
     * 取得字符串的真实长度，一个汉字长度为2
     *
     * @param str
     * @return
     */
    public final static int getRealLength(String str) {
        if (str == null) {
            return 0;
        }

        char separator = 256;
        int realLength = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= separator) {
                realLength += 2;
            } else {
                realLength++;
            }
        }
        return realLength;
    }

    /**
     * html文本过滤, 如果value为 <code>null</code> 或为空串, 则返回"&nbsp;". 对于空格字符,
     * 转化为"&nbsp". (适用于显示普通文本)
     *
     * @param value 要过滤的文本
     * @return 过滤后的html文本
     */
    public final static String htmlFilter(String value) {
        if (ValidateUtils.isEmpty(value)) {
            return "&nbsp;";
        }

        return value.replaceAll("&", "&amp;").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("\n", "<br>");
    }

    /**
     * html文本过滤, 如果value为 <code>null</code> 或为空串, 则返回空串. 对于空格字符, 不转化为"&nbsp;".
     * (适用于显示文本框中的值)
     *
     * @param value 要过滤的文本
     * @return 过滤后的html文本
     */
    public final static String htmlFilterToEmpty(String value) {
        if (ValidateUtils.isEmpty(value)) {
            return "";
        }

        return value.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
    }

    /**
     * 忽略值为 <code>null</code> 的字符串
     *
     * @param str 字符串
     * @return 如果字符串为 <code>null</code>, 则返回空字符串.
     */
    public final static String ignoreNull(String str) {
        return str == null ? "" : str;
    }

    /**
     * 忽略值为 <code>null</code> 的字符串
     *
     * @param object
     * @return
     */
    public final static String ignoreNull(Object object) {
        return object == null ? "" : object.toString();
    }

    /**
     * 只否包括"\""等不利于文本框显示的字符
     *
     * @param arg
     * @return
     */
    public final static boolean isNotAllowed4TextBox(String arg) {
        if (ValidateUtils.isEmpty(arg)) {
            return false;
        }

        return arg.indexOf("\"") >= 0;
    }

    /**
     * 过滤html的"'"字符(转义为"\'")以及其他特殊字符, 主要用于链接地址的特殊字符过滤.
     *
     * @param str 要过滤的字符串
     * @return 过滤后的字符串
     */
    public final static String linkFilter(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return str;
        }

        return htmlFilter(htmlFilter(str.replaceAll("'", "\\\\'")));
    }

    /**
     * <p>
     * Replaces all occurrences of a String within another String.
     * </p>
     */
    public final static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * <p>
     * Replaces a String with another String inside a larger String, for the
     * first <code>max</code> values of the search String.
     */
    public final static String replace(String text, String repl, String with, int max) {
        if (text == null || ValidateUtils.isEmpty(repl) || with == null || max == 0) {
            return text;
        }

        StringBuilder buf = new StringBuilder(text.length());
        int start = 0, end = 0;
        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 清除字符串左侧的空格
     *
     * @param str
     * @return
     */
    public final static String ltrim(String str) {
        return ltrim(str, " ");
    }

    /**
     * 清除字符串左侧的指定字符串
     *
     * @param str
     * @param remove
     * @return
     */
    public final static String ltrim(String str, String remove) {
        if (str == null || str.length() == 0 || remove == null || remove.length() == 0) {
            return str;
        }

        while (str.startsWith(remove)) {
            str = str.substring(remove.length());
        }
        return str;
    }

    /**
     * 清除字符串右侧的空格
     *
     * @param str
     * @return
     */
    public final static String rtrim(String str) {
        return rtrim(str, " ");
    }

    /**
     * 清除字符串右侧的指定字符串
     *
     * @param str
     * @param remove
     * @return
     */
    public final static String rtrim(String str, String remove) {
        if (str == null || str.length() == 0 || remove == null || remove.length() == 0) {
            return str;
        }

        while (str.endsWith(remove) && (str.length() - remove.length()) >= 0) {
            str = str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    /**
     * 把字符串按照规则分割，比如str为“id=123&name=test”，rule为“id=#&name=#”，分隔后为["123",
     * "test"];
     *
     * @param str
     * @param rule
     * @return
     */
    public final static String[] split(String str, String rule) {
        if (rule.indexOf(SEPARATOR_SINGLE) == -1 || rule.indexOf(SEPARATOR_SINGLE + SEPARATOR_SINGLE) != -1) {
            throw new IllegalArgumentException("Could not parse rule");
        }

        String[] rules = rule.split(SEPARATOR_SINGLE);
        // System.out.println(rules.length);

        if (str == null || str.length() < rules[0].length()) {
            return new String[0];
        }

        boolean endsWithSeparator = rule.endsWith(SEPARATOR_SINGLE);

        String[] strs = new String[endsWithSeparator ? rules.length : rules.length - 1];
        if (rules[0].length() > 0 && !str.startsWith(rules[0])) {
            return new String[0];
        }

        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < strs.length; i++) {
            startIndex = str.indexOf(rules[i], endIndex) + rules[i].length();
            if (i + 1 == strs.length && endsWithSeparator) {
                endIndex = str.length();
            } else {
                endIndex = str.indexOf(rules[i + 1], startIndex);
            }

            // System.out.println(startIndex + "," + endIndex);

            if (startIndex == -1 || endIndex == -1) {
                return new String[0];
            }
            strs[i] = str.substring(startIndex, endIndex);
        }

        return strs;
    }

    /**
     * 替换sql like的字段中的通配符，包括[]%_
     *
     * @param str
     * @return
     */
    public final static String sqlWildcardFilter(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return str;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '[') {
                stringBuilder.append("[[]");
            } else if (c == ']') {
                stringBuilder.append("[]]");
            } else if (c == '%') {
                stringBuilder.append("[%]");
            } else if (c == '_') {
                stringBuilder.append("[_]");
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 把字符串按照指定的字符集进行编码
     *
     * @param str
     * @param charSetName
     * @return
     */
    public final static String toCharSet(String str, String charSetName) {
        try {
            return new String(str.getBytes(), charSetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 把一个字节数组转换为16进制表达的字符串
     *
     * @param bytes
     * @return
     */
    public final static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            hexString.append(enoughZero(Integer.toHexString(bytes[i] & 0xff), 2));
        }
        return hexString.toString();
    }

    /**
     * 把16进制表达的字符串转换为整数
     *
     * @param hexString
     * @return
     */
    public final static int hexString2Int(String hexString) {
        return Integer.valueOf(hexString, 16).intValue();
    }

    /**
     * 清除字符串两边的空格，null不处理
     *
     * @param str
     * @return
     */
    public final static String trim(String str) {
        return str == null ? str : str.trim();
    }

    /**
     * <p>
     * Removes control characters (char &lt;= 32) from both ends of this String
     * returning an empty String ("") if the String is empty ("") after the trim
     * or if it is <code>null</code>.
     * </p>
     *
     * @param str the String to be trimmed, may be null
     * @return the trimmed String, or an empty String if <code>null</code> input
     */
    public final static String trimToEmpty(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 清除字符串中的回车和换行符
     *
     * @param str
     * @return
     */
    public final static String ignoreEnter(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        return str.replaceAll("\r|\n", "");
    }

    /**
     * 清除下划线，把下划线后面字母转换成大写字母
     *
     * @param str
     * @return
     */
    public final static String underline2Uppercase(String str) {
        if (ValidateUtils.isEmpty(str)) {
            return str;
        }

        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '_' && i < charArray.length - 1) {
                charArray[i + 1] = Character.toUpperCase(charArray[i + 1]);
            }
        }

        return new String(charArray).replaceAll("_", "");
    }

    /**
     * 二进制转16进制字符串
     *
     * @param byteArray
     * @return
     */
    public final static String transByteArrayToHexString(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < byteArray.length; i++) {
            int b = byteArray[i];
            String s = Integer.toHexString(b);

            if (s.length() > 2) {
                s = s.substring(s.length() - 2, s.length());
            }

            if (s.length() == 1) {
                s = "0" + s;
            }

            sb.append(s);
        }

        return sb.toString();
    }

    /**
     * 16进制字符串转二进制
     *
     * @param hexString
     * @return
     */
    public final static byte[] transHexStringToByteArray(String hexString) {
        byte[] byteArray = new byte[hexString.length() / 2];

        for (int i = 0; i < byteArray.length; i++) {
            int tempValue = Integer.parseInt(hexString.substring(i * 2, (i + 1) * 2), 16);

            if (tempValue > 127) {
                tempValue = tempValue - 256;
            }

            byteArray[i] = (byte) (Byte.parseByte(tempValue + "", 10));
        }

        return byteArray;
    }

    /**
     * 是否常用标准字符（中英文、数字、下划线，如：昵称）
     *
     * @param
     * @return
     */
    public final static boolean isNormalStr(String s) {

        if (ValidateUtils.isEmpty(s)) return false;
        return s.matches("[\u4e00-\u9fa5a-zA-Z0-9_]+");
    }

    /**
     * 判断是否英文
     *
     * @param s
     * @return
     */
    public final static boolean isEn(String s) {

        if (ValidateUtils.isEmpty(s)) return false;
        String regex = "[a-zA-Z]+";
        if (s.matches(regex)) {
            return true;
        }
        return false;

    }

    /**
     * 判断是否属于svn帐户合法字符
     *
     * @param s
     * @return
     */
    public final static boolean isSvnAccountValid(String s) {

        if (ValidateUtils.isEmpty(s)) return false;
        String regex = "[a-zA-Z0-9_\\.]+";
        if (s.matches(regex)) {
            return true;
        }
        return false;

    }

    /**
     * 判断是否属于密码合法字符
     *
     * @param s
     * @return
     */
    public final static boolean isPassValid(String s) {

        if (ValidateUtils.isEmpty(s)) return false;
        String regex = "[a-zA-Z0-9_`~!@#$%\\^\\*()+-=|[]{}:;',\\.>?]+]";
        if (s.matches(regex)) {
            return true;
        }
        return false;

    }

    /**
     * 判断是否属于svn合法字符
     *
     * @param s
     * @return
     */
    public final static boolean isSvnValid(String s) {

        if (ValidateUtils.isEmpty(s)) return false;
        String regex = "[a-zA-Z0-9\\-_\\.]+";
        if (s.matches(regex)) {
            return true;
        }
        return false;

    }

    /**
     * 判断是否属于svn合法字符
     *
     * @param s
     * @return
     */
    public final static boolean isSvnNameValid(String s) {

        if (ValidateUtils.isEmpty(s)) return false;
        String regex = "[a-zA-Z0-9\\-_]+";
        if (s.matches(regex)) {
            return true;
        }
        return false;

    }

    public final static boolean isIp(String s) {
        if (ValidateUtils.isEmpty(s)) return false;
        String[] tempArray = s.split("\\.");
        if (tempArray.length != 4) {
            return false;
        }
        for (String string : tempArray) {
            int tempInt = Integer.parseInt(string);
            if (tempInt < 0 || tempInt > 255) {
                return false;
            }
        }
        return true;

    }

    /**
     * 判断是否为有效链接
     *
     * @param url
     * @return
     */
    public final static boolean isUrl(String url) {
        if (ValidateUtils.isEmpty(url)) return false;
        if (url.length() >= 6 && url.substring(0, 6).equals("ftp://")) return true;
        if (url.length() >= 7 && url.substring(0, 7).equals("http://")) return true;
        if (url.length() >= 8 && url.substring(0, 8).equals("https://")) return true;
        else return false;

    }

    /**
     * 判断url是否包含中文字符
     *
     * @param url
     * @return
     */
    public final static boolean hasChaneseLetter(String url) {
        if (ValidateUtils.isEmpty(url)) return false;
        String regex = "[a-zA-Z0-9_.\\{}?/+=|\\'\":;~!@#*$%\\^&()`\\\\-]+";
        if (url.matches(regex)) {
            return true;
        }
        return false;

    }

    /**
     * URL 参数转码
     *
     * @param para
     * @return
     */
    public final static String encodeString(String para) {
        return encodeString(para, "UTF-8");
    }

    /**
     * URL 参数转码
     *
     * @param para
     * @param charset
     * @return
     */
    public final static String encodeString(String para, String charset) {
        if (para == null) {
            return para;
        }
        try {
            return URLEncoder.encode(para, charset);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return para;
    }

    /**
     * 格式化java字符串为html字符串,保证html的自动换行且不破坏单词本身
     *
     * @param string
     * @return
     */
    public final static String htmlFormat(String string) {
        if (ValidateUtils.isEmpty(string)) {
            return "&nbsp;";
        }

        return string.replaceAll("&", "&amp;").replaceAll("  ", "&nbsp; ").replaceAll("  ", " &nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("\n", "<br>");
    }

    // 获取文件名的后缀
    public final static String getFileExtension(String fileName) {
        String ext = null;
        int dotPos = fileName.lastIndexOf('.');

        if ((dotPos > 0) && (dotPos < (fileName.length() - 1))) {
            ext = fileName.substring(dotPos + 1).toLowerCase();
        }

        return ext;
    }

    /**
     * 清除String字符串中的html代码
     *
     * @param htmlValue
     * @return
     */
    public final static String getCleanHtmlCodeStrng(String htmlValue) {
        String _tempName = htmlValue;
        int beginIndex = 0;
        int endIndex = 0;

        while (true) {
            if (_tempName.indexOf("<") != -1) {
                beginIndex = _tempName.indexOf("<");
            } else {
                beginIndex = 0;
            }

            if (_tempName.indexOf(">") != -1) {
                endIndex = _tempName.indexOf(">");
            } else {
                endIndex = 0;
            }

            if ((beginIndex == 0) && (endIndex == 0)) {
                break;
            }

            _tempName = _tempName.replaceAll(_tempName.substring(beginIndex, endIndex), "");
        }

        return _tempName;
    }

    public final static String combineString(String[] stringArray, String seperator) {
        StringBuilder sb = new StringBuilder();
        if (!ValidateUtils.isEmpty(stringArray)) {
            for (int i = 0; i < stringArray.length - 1; i++) {
                String string = stringArray[i];
                if (ValidateUtils.isEmpty(string)) {
                    continue;
                }
                sb.append(string).append(seperator);
            }
            sb.append(stringArray[stringArray.length - 1]);
        }
        return sb.toString();
    }

    public static String join(Iterable<String> strings, String separator) {
        return StringUtils.join(strings, separator);
    }

    /**
     * 穷举各种类型分隔符分割字段
     *
     * @param inputString
     * @return
     */
    public static String[] multiForamtSplit(String inputString) {
        if (ValidateUtils.isEmpty(inputString)) {
            return new String[]{};
        }
        return inputString.trim().replaceAll("\r\n", ";").replaceAll("\n", ";").split(MULTI_FORAMT_SPLIT_REGEX);

    }

    /**
     * 将URL路径转换为文件路径
     *
     * @param para
     * @return
     */
    public final static String decodeString(String para) {
        return decodeString(para, "UTF-8");
    }

    /**
     * 将URL路径转换为文件路径
     *
     * @param para
     * @param charset
     * @return
     */
    public final static String decodeString(String para, String charset) {
        try {
            return URLDecoder.decode(para, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return para;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {

        System.out.println(camelName("Not_throw_or_wait_throw_port_capacity"));
    }

    public final static String convert(String utfString) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = utfString.indexOf("\\u", pos)) != -1) {
            sb.append(utfString.substring(pos, i));
            if (i + 5 < utfString.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
            }
        }
        sb.append(utfString.substring(pos, utfString.length() - 1));
        return sb.toString();
    }

    public final static String ip2str(long ip) {
        return String.format("%d.%d.%d.%d", (ip & 0xFF000000) >> 24, (ip & 0xFF0000) >> 16, (ip & 0xFF00) >> 8, ip & 0xFF);
    }

    public final static long str2ip(String ip) throws Exception {
        String[] ips = ip.split("\\.");
        if (ips.length != 4) {
            throw new Exception("IP格式错误");
        }
        long out = 0;
        for (int i = 0; i < ips.length; i++) {
            int x = Integer.parseInt(ips[i]);
            if (x > 255) {
                throw new Exception("IP格式错误");
            }
            out = x + (out << 8);
        }
        return out;
    }

    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->HELLO_WORLD
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underscoreName(String name) {

        StringBuilder result = new StringBuilder();
        if (!ValidateUtils.isEmpty(name)) {
            if (name.equals(name.toUpperCase())) {
                return name;
            }
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0)) && !s.equals("_")) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    public static String indent(String str, int lineLen, String delimiter) {
        List<String> list = new ArrayList();
        int step = str.length() % lineLen;
        for (int i = 0; i < step; i++) {
            int endIndex = i * lineLen + lineLen;
            if (endIndex > str.length()) {
                endIndex = str.length();
            }
            list.add(str.substring(i * lineLen, endIndex));
        }
        if (list.isEmpty()) {
            return str;
        } else {
            return String.join(delimiter, list);
        }
    }

    public static boolean equalsStr(String str1, String str2) {
        if (ValidateUtils.isBlank(str1) && ValidateUtils.isBlank(str2)) {
            return true;
        }
        if (!ValidateUtils.isEmpty(str1) && str1.equals(str2)) {
            return true;
        }
        return false;
    }

}
