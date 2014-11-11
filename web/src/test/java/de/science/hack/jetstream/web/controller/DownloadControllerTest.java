/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.jetstream.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.DelegatingServletOutputStream;
import toxi.geom.mesh.TriangleMesh;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

/**
 *
 * @author schroeder
 */
public class DownloadControllerTest {
    
    private DownloadController classUnderTest;
    
    private ResultCache cache;
    
    private HttpServletResponse response;
    
    @Before
    public void setUp() throws IOException {
        classUnderTest = new DownloadController();
        cache = new ResultCache();
        setField(classUnderTest, "resultCache", cache);
        response = mock(HttpServletResponse.class);
        ServletOutputStream outputStream = new DelegatingServletOutputStream(new ByteArrayOutputStream());
        when(response.getOutputStream()).thenReturn(outputStream);
    }

    /**
     * Test of download method, of class DownloadController.
     */
    @Test
    public void testWindDownload() throws Exception {
        cache.setMesh(new TriangleMesh());
        classUnderTest.windData(response);
        verify(response).setContentType(DownloadController.MIME);
    }
    
    /**
     * Test of download method, of class DownloadController.
     */
    @Test
    public void testGlobeDownload() throws Exception {
        cache.setMesh(new TriangleMesh());
        classUnderTest.globe(response, "w");
        verify(response).setContentType(DownloadController.MIME);
    }
    
}
