package hyman.springbootdemo.rabbitmqSpring.messageConverter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

// 自定义文件消息转换器
public class FileMessageConverter implements MessageConverter{

    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String headername = (String) message.getMessageProperties().getHeaders().get("_extName");
        byte[] bytes = message.getBody();
        String filename = UUID.randomUUID().toString();

        /**
         * java.io.tmpdir 是获取操作系统缓存的临时目录，不同操作系统的缓存临时目录不一样：
         * 在 Windows的缓存目录为：C:\Users\登录用户~1\AppData\Local\Temp\
         * 在 Linux 为：/tmp
         * String filepath = System.getProperty("java.io.tmpdir");
         */
        String filepath = FileMessageConverter.class.getClassLoader().getResource("/").getPath().replace("classes","filefolder");
        filepath += filename+"."+headername;

        File tempFile = new File(filepath);
        try {
            // ByteArrayInputStream，OutputStream
            FileCopyUtils.copy(bytes,tempFile);
        } catch (IOException e) {
            throw new MessageConversionException("FileMessageConverter消息转换失败", e);
        }
        return tempFile;
    }
}
