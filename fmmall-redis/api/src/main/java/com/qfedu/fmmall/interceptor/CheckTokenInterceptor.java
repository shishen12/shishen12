package com.qfedu.fmmall.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CheckTokenInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{

        String method= request.getMethod();
        if("OPTIONS".equalsIgnoreCase(method)){
            return true;
        }

        String token = request.getHeader("token");
        System.out.println("--------"+token);
        if(token==null){
            ResultVO resultVO=new ResultVO(ResStatus.LOGIN_FAIL_NOT,"请先登录",null);
            doResponse(response,resultVO);
            return  false;
        }else {
            try {
                JwtParser parser = Jwts.parser();
                parser.setSigningKey("ZHUzhu8023");
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                return true;
            }catch (ExpiredJwtException e){
                ResultVO resultVO=new ResultVO(ResStatus.LOGIN_FAIL_OVERDUE,"登录过期，请重新登录",null);
                doResponse(response,resultVO);
            }catch (UnsupportedJwtException e){
                ResultVO resultVO=new ResultVO(ResStatus.NO,"Token不合法，请重新登录",null);
                doResponse(response,resultVO);
            }catch (Exception e){
                ResultVO resultVO=new ResultVO(ResStatus.LOGIN_FAIL_NOT,"请先登录",null);
                doResponse(response,resultVO);
            }

        }
        return false;
    }
    private void doResponse(HttpServletResponse response,ResultVO resultVO) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String s = new ObjectMapper().writeValueAsString(resultVO);
        out.print(s);
        out.flush();
        out.close();
    }

}