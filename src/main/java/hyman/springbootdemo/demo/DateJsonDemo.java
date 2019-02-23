package hyman.springbootdemo.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DateJsonDemo {

    /**
     * 如果在配置文件中，不进行配置，实体类也不添加注解，则 spring 无法接收时间参数（400），json 输出 "2018-03-29T09:45:31.513+0000"
     * 如果有进行配置，实体类中也有注解，则实体类可接收两种不同格式的日期参数。
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date newdate;


    /**
     *
     * (Ip转Integer)
     */
    public static int ipToInteger(String ip){
        String[] ips = ip.split("\\.");
        int ipFour = 0;
        //因为每个位置最大255，刚好在2进制里表示8位
        for(String ip4: ips){
            Integer ip4a = Integer.parseInt(ip4);
            //这里应该用+也可以,但是位运算更快
            ipFour = (ipFour << 8) | ip4a;
        }
        return ipFour;
    }

    /**
     *
     * (Integer转IP)
     */
    public static String IntegerToIp(Integer ip) {
        //思路很简单，每8位拿一次，就是对应位的IP
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            int ipa = (ip >> (8 * i)) & (0xff);
            sb.append(ipa + ".");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }


    public static void toList() {
        /**
         * 要注意，基本数据类型的数组（如int[],long[]）直接用 Arrays.asList 转成 list，那么 List 中就只会有一个 Integer数组
         * 类型的对象，整个数组作为一个元素存进去的。而对象类型数组转 list 则是遍历后存入的。所以基本数据类型数组要转成包装类
         * 后再转 list。
         *
         * 也可以使用 apache commons-lang 工具包里的数组工具类 ArrayUtils 类的 toObject()方法，非常方便：
         *
         * Arrays.asList(ArrayUtils.toObject(基本数据类型的数组))。
         */
    }

    public static void main(String[] args) {
        String test = null;
        System.out.println(test==null);

        System.out.println(ipToInteger("192.168.1.120"));

        System.out.println(IntegerToIp(-1062731400));
    }
}
