/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public void testDownload() throws Exception {
        cache.setMesh(new TriangleMesh());
        classUnderTest.download(response);
        verify(response).setContentType(DownloadController.MIME);
    }
    
    /**
     * Test of download method, of class DownloadController.
     */
    @Test
    public void testDownloadByType() throws Exception {
        cache.setMesh(new TriangleMesh());
        classUnderTest.download(response, "w");
        verify(response).setContentType(DownloadController.MIME);
    }
    
}
