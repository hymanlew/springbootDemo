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


    public static void main(String[] args) {
        String test = null;
        System.out.println(test==null);

        System.out.println(ipToInteger("192.168.1.120"));

        System.out.println(IntegerToIp(-1062731400));
    }
}
