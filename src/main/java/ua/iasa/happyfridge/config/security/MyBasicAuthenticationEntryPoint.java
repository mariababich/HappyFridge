package ua.iasa.happyfridge.config.security;

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


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(MAPPER.writeValueAsString(request.getRequestURL().toString()));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Happy Fridge");
    }
}
