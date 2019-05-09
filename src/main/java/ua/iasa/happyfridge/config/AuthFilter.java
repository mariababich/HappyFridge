package ua.iasa.happyfridge.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends BasicAuthenticationFilter {

    private final String loginUrl;

    public AuthFilter(final String loginUrl, AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
        this.loginUrl = loginUrl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(loginUrl.equalsIgnoreCase(request.getRequestURI()) || isAuthenticated()
                || request.getRequestURI().equalsIgnoreCase("/registration")
                ||request.getRequestURI().equalsIgnoreCase("/h2-console/**"))) {
//            System.out.println(request.getRequestURI());
            SecurityContextHolder.clearContext();
            final InsufficientAuthenticationException exception = new InsufficientAuthenticationException("Not authenticated");
            onUnsuccessfulAuthentication(request, response, exception);

            if (super.isIgnoreFailure()) {
                chain.doFilter(request, response);
            }
            else {
                getAuthenticationEntryPoint().commence(request, response, exception);
            }
        } else {
            super.doFilterInternal(request, response, chain);
        }
    }

    private boolean isAuthenticated() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
}
