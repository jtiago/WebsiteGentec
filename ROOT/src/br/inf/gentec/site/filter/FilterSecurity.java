package br.inf.gentec.site.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class FilterSecurity implements Filter {

    public FilterSecurity() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest servletRequest = (HttpServletRequest)request;
        HttpServletResponse servletResponse = (HttpServletResponse)response;
        Object user = servletRequest.getSession().getAttribute("user");
        if(user != null) {
            chain.doFilter(request, response);
        } else {
            servletResponse.sendRedirect((new StringBuilder(String.valueOf(servletRequest.getContextPath()))).append("/pages/login.jsf").toString());
        }
    }

    public void init(FilterConfig filterconfig) throws ServletException {
    	
    }
}