package hyman.springbootdemo.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.util.Date;

public class TestDemo {

    /**
     * 如果在配置文件中，不进行配置，实体类也不添加注解，则 spring 无法接收时间参数（400），json 输出 "2018-03-29T09:45:31.513+0000"
     * 如果有进行配置，实体类中也有注解，则实体类可接收两种不同格式的日期参数。
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date newdate;

    public static void main(String[] args) {
        String test = null;
        System.out.println(test==null);
    }
}
