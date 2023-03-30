package com.shao.wacky.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * IP地址
 *
 * @author chengdu
 */
public class IPUtil {

    public static final String UN_KNOWN = "unknown";

    public static final String LOCAL_IP = "0:0:0:0:0:0:0:1";

    public static final String LOCAL_IP_STR = "127.0.0.1";

    public static final String[] ADDR_HEADER = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "X-Real-IP"};

    private static Logger logger = LoggerFactory.getLogger(IPUtil.class);

    public static String getIpAddr() {
        return getIpAddr(RequestContextUtil.request());
    }

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (Arrays.asList("0:0:0:0:0:0:0:1", "127.0.0.1", "localhost").contains(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet;
                    try {
                        inet = InetAddress.getLocalHost();
                        ipAddress = inet.getHostAddress();
                    } catch (UnknownHostException e) {
                    }
                }
            }
            // 对于通过多个代理的情况,第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }

        return ipAddress;
    }

    public static String getIP(HttpServletRequest request) {
        String ip = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest hsr = (HttpServletRequest) request;
            for (String header : ADDR_HEADER) {
                if (DataUtil.isEmptyStr(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
                    ip = hsr.getHeader(header);
                } else {
                    break;
                }
            }
        }
        if (DataUtil.isEmptyStr(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip != null) {
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按','分割
            int i = ip.indexOf(",");
            if (i > 0) {
                ip = ip.substring(0, i);
            }
        }
        if (!checkIP(ip)) {
            ip = LOCAL_IP_STR;
        }
        if (LOCAL_IP.equals(ip)) {
            ip = LOCAL_IP_STR;
        }
        return ip;
    }

    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || UN_KNOWN.equalsIgnoreCase(ip)) {
            return false;
        }
        return true;
    }

    /**
     * ip地址转成long型数字
     * 将IP地址转化成整数的方法如下：
     * 1、通过String的split方法按.分隔得到4个长度的数组
     * 2、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1
     *
     * @return
     * @paramstrIp
     */
    public static long ipToLong(String strIp) {
        String[] ip = strIp.split("\\.");
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }

    /**
     * 将十进制整数形式转换成127.0.0.1形式的ip地址
     * 将整数形式的IP地址转化成字符串的方法如下：
     * 1、将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。
     * 2、通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。
     * 3、通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。
     * 4、通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP。
     *
     * @return
     * @paramlongIp
     */
    public static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        //直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        //将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        //将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        //将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

}
