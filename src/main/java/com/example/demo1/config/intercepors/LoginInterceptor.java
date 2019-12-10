package com.example.demo1.config.intercepors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo1.utils.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: LiuZW
 * @CreateDate: 2019/10/17/017 14:24
 * @Version: 1.0
 */
@Component
@Log4j2
public class LoginInterceptor extends BaseInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @MethodName: preHandle
     * @Description: 这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，
     * 就可以在用户调用指定接口之前验证登陆状态了
     * @Param: [request, response, handler]
     * @Return: boolean
     * @Author: LiuZW
     * @Date: 2019/10/17/017 14:27
     **/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        try {
            // 区块1
            String token = request.getHeader("token");
            if (StringUtils.isNull(token)) {
                log.info("cncKey为空，拦截器拦截");
                notLoginHandler(request, response);
                return false;
            }
            log.info("cncKey信息为:{}", token);
            // 获取登录用户信息
            String tokenContentObjStr = stringRedisTemplate.opsForValue().get(token);
            if (StringUtils.isNull(tokenContentObjStr)) {
                log.info("cncKey不存在，拦截器拦截");
                notLoginHandler(request, response);
                return false;
            }
            JSONArray jsonArray = JSON.parseArray(tokenContentObjStr);
            log.info("获取jsonArray：{}", jsonArray);
            //获取用户ID
            String usrSqn = jsonArray.getString(3);
            request.setAttribute("REQUEST_USR_APP_INFO", usrSqn);
            return true;
        } catch (Exception ex) {
            log.error("系统异常：", ex);
        }
        notLoginHandler(request, response);
        return false;
    }

    /**
     * @MethodName: notLoginHandler
     * @Description: 拦截处理
     * @Param: [request, response]
     * @Return: void
     * @Author: LiuZW
     * @Date: 2019/10/17/017 15:42
     **/
    private void notLoginHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String requestUri = request.getRequestURI();
        log.info("拦截到未登录访问，已做跳转登录处理；访问地址：" + requestUri);
        writerJson(response, "token为空");
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
