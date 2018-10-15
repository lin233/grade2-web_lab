/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package ebook;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "TimeOfDayFilter",
urlPatterns = {"/*"},
initParams = {
    @WebInitParam(name = "user", value = "awake")})
public class TimeOfDayFilter implements Filter {

	
    String user = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        user = filterConfig.getInitParameter("user");
    }

    @Override
    public void doFilter(ServletRequest req,
            ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
    	
        req.setAttribute("user", user);
        chain.doFilter(req, res);
    
    }

    @Override
    public void destroy() {
    }
}
