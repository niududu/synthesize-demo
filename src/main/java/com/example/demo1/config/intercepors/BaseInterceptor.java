package com.example.demo1.config.intercepors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: LiuZW
 * @CreateDate: 2019/10/17/017 15:38
 * @Version: 1.0
 */
@Component
@Log4j2
public class BaseInterceptor implements HandlerInterceptor {

    protected void writerJson(HttpServletResponse response, Object data) {
        this.writer(response, "application/json;charset=UTF-8", JSON.toJSONString(data, SerializerFeature.WriteDateUseDateFormat));
    }

    protected void writer(HttpServletResponse response, String contentType, String content) {
        try {
            response.setContentType(contentType);
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            log.error("写入数据错误，错误信息：", e);
        }
    }


}
