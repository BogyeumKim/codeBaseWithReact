package org.zerock.mallapi.security.handler;

import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.mallapi.dto.MemberDTO;
import org.zerock.mallapi.util.JWTUItil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("========================================");
        log.info(authentication);
        log.info("========================================");

        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();

        Map<String ,Object> claims = memberDTO.getClaims();

        String accessToken = JWTUItil.generateToken(claims, 10);
        String refreshToken = JWTUItil.generateToken(claims, 60*24);
        claims.put("accessToken",accessToken);
        claims.put("refreshToken",refreshToken);

        String jsonStr = new Gson().toJson(claims);

        response.setContentType("application/json; charset=utf-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();

    }
}
