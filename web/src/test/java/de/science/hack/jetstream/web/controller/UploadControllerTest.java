/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.jetstream.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

/**
 *
 * @author Mario
 */
public class UploadControllerTest {
    
    private static final String NAME = "test";
    
    private UploadController classUnderTest;
    
    private ResultCache cache;
    
    @Before
    public void setUp() {
        classUnderTest = new UploadController();
        
        cache = new ResultCache();
        setField(classUnderTest, "resultCache", cache);
    }

    /**
     * Test of upload method, of class UploadController.
     */
    @Test
    public void testFailedUpload() {
        byte[] content = new byte[0];
        MultipartFile file = new MockMultipartFile(NAME, content);
        ModelAndView result = classUnderTest.upload(file);
        assertNotNull(result);
        assertEquals(UploadController.FAILED_VIEW, result.getViewName());
        assertEquals(UploadController.FAILED_UPLOAD, result.getModel().get(UploadController.MSG_OBJ));
    }
    
    /**
     * Test of upload method, of class UploadController.
     */
    @Test
    public void testSuccessfulUpload() {
        byte[] content = ("0,69,-2.35675048828125\n"
                + "0,67.5,-2.00323486328125\n"
                + "0,66,-3.88800048828125").getBytes();
        MultipartFile file = new MockMultipartFile(NAME, content);
        ModelAndView result = classUnderTest.upload(file);
        assertNotNull(result);
        assertEquals(UploadController.WEBGL_VIEW, result.getViewName());
    }
    
}
