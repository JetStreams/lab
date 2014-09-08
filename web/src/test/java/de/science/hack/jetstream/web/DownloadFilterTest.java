/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.jetstream.web;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Mario
 */
public class DownloadFilterTest {
    
    private DownloadFilter classUnderTest;
    
    private HttpServletRequest request;
    
    private MockHttpServletResponse response;
    
    private FilterChain chain;
    
    @Before
    public void setUp() {
        classUnderTest = new DownloadFilter();
        
        response = new MockHttpServletResponse();
        request = mock(HttpServletRequest.class);
        chain = mock(FilterChain.class);
    }


    /**
     * Test of doFilter method, of class DownloadFilter.
     */
    @Test
    public void testDoFilter() throws Exception {
        when(request.getRequestURI()).thenReturn("/jet/txt/north.txt");
        classUnderTest.doFilter(request, response, chain);
        
        verify(chain).doFilter(request, response);
        
        String header = response.getHeader(DownloadFilter.CONTENT_HEADER);
        assertNotNull(header);
        assertFalse(header.isEmpty());
        assertEquals("attachment; filename=\"north.txt\"", header);
    }

    
}
