package org.zerock.mallapi.security.filter;

import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.mallapi.util.JWTUItil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    /**
     * 경로필터
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        // ture == not check

        String path = request.getRequestURI();

        log.info("check uri ------------------------" + path);

        // false == check
        return false;
    }

    /**
     * 모든 요청 필터, 위 경로필터 다음임
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("===================");

        log.info("===================");

        log.info("===================");

        String authHeaderStr = request.getHeader("Authorization");


        //bearer 7개 prefix 자르기 bearer 은  oauth2.0 표준임
        try {
            //Bearer accestoken...
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JWTUItil.validateToken(accessToken);

            log.info("JWT claims: " + claims);

            filterChain.doFilter(request, response);
        } catch (Exception e) {

            log.error("JWT Check Error..............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
            return;
        }

            // 다음 필터나 인터셉터나 서블릿 컨트롤러로
            filterChain.doFilter(request, response);
        }
    }
