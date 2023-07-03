package com.qipt.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.qipt.util.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String message = "";
        try {
            String token = request.getHeader("Authorization");
            System.out.println(token);
            JWTUtils.verifyToken(token);//验证token令牌
            return true;  // 验证成功 放行
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            message = "无效签名";
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            message = "token过期";
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            message = "token算法不一致";
        } catch (Exception e) {
            e.printStackTrace();
            message = "token无效";
        }
        response.setStatus(406);
        PrintWriter writer = response.getWriter();
        writer.write(message);
        System.out.println(message);
        return false;  //拦截
    }
}
