/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.jetstream.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter adds an attachment header so that a filetype will be 
 * downloaded rather than opened in the browser.
 * 
 * @author Mario
 */
public class DownloadFilter implements Filter {
    
    static final String CONTENT_HEADER = "Content-Disposition";

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    /**
     * Adds a download header
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request; 
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String uri = httpRequest.getRequestURI();
        int pos = uri.lastIndexOf("/") + 1;
        String name = uri.substring(pos);
        
        String headerValue = String.format("attachment; filename=\"%s\"", name);
        httpResponse.setHeader(CONTENT_HEADER, headerValue);
        
        chain.doFilter(request, response);
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

}
