/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.jetstream.web.controller;

import de.science.hack.WindModelBuilder;
import java.io.IOException;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import toxi.geom.mesh.TriangleMesh;

/**
 * Controller to upload the wind data and calculate the result.
 *
 * @author Mario
 */
@Controller
@Scope("session")
public class UploadController implements Serializable{

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    static final String SUCCESSFULL_UPLOAD = "You uploaded the file successfully.";
    static final String FAILED_UPLOAD = "Your upload failed, because the file was empty.";
    
    static final String MSG_OBJ = "message";
    
    static final String FAILED_VIEW = "failed";
    static final String WEBGL_VIEW = "webgl";

    @Autowired
    private ResultCache resultCache;

    /**
     * Upload a single file and process the content
     *
     * @param file the actual content
     * @return the result as String
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView upload(@RequestParam("file") MultipartFile file) {
        
        ModelAndView model;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                WindModelBuilder builder = new WindModelBuilder();
                TriangleMesh mesh = builder.build(bytes);
                resultCache.setMesh(mesh);

                model = new ModelAndView(WEBGL_VIEW);
            } catch (IOException e) {
                LOG.warn(e.getMessage(), e);
                model = new ModelAndView(FAILED_VIEW, MSG_OBJ, e.getMessage());
            }
        } else {
            model = new ModelAndView(FAILED_VIEW, MSG_OBJ, FAILED_UPLOAD);
        }
        
        return model;
    }
    
}
