package ua.iasa.happyfridge.config;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    private static ObjectMapper MAPPER = new ObjectMapper();

    private final String loginUrl;

    public MyBasicAuthenticationEntryPoint(final String realm, final String loginUrl) {
        setRealmName(realm);
        this.loginUrl = loginUrl;
    }
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        if (loginUrl.equalsIgnoreCase(request.getRequestURI()) || request.getRequestURI().equalsIgnoreCase("/registration")
//                ||request.getRequestURI().equalsIgnoreCase("/h2-console/**")) {
//            super.commence(request, response, authException);
//        } else {
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(MAPPER.writeValueAsString(request.getRequestURL().toString()));
//        }
    }
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        setRealmName("Happy Fridge");
//    }
}
