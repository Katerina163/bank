package com.github.Katerina163.bank.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {
        var uri = request.getRequestURI();
        if (isAlwaysPermitted(uri)) {
            chain.doFilter(request, response);
            return;
        }
        var userLoggedIn = request.getSession().getAttribute("clients") != null;
        if (!userLoggedIn) {
            var mainPageUrl = request.getContextPath() + "/main";
            response.sendRedirect(mainPageUrl);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isAlwaysPermitted(String uri) {
        return uri.startsWith("/main") || uri.startsWith("/error")
                || uri.startsWith("/client/login") || uri.startsWith("/client/register")
                || uri.startsWith("/bank/login") || uri.startsWith("/bank/register")
                || uri.startsWith("/static");
    }
}
