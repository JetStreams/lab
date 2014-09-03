/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.jetstream.web.controller;

import de.science.hack.JetStreamModelWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import toxi.geom.mesh.TriangleMesh;

/**
 * Controller to download the result.
 *
 * @author Mario
 */
@Controller
@Scope("session")
public class DownloadController implements Serializable{

    private static final String FILENAME = "model.stl";
    
    static final String MIME = "application/sla";

    @Autowired
    private ResultCache resultCache;

    /**
     * Download the mesh.
     * @param response {@link HttpServletResponse}
     * @throws IOException 
     */
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response) throws IOException {

        response.setContentType(MIME);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", FILENAME);
        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();
        TriangleMesh wind = resultCache.getMesh();

        JetStreamModelWriter modelWriter = new JetStreamModelWriter();
        modelWriter.addWindModel(wind);
        modelWriter.write(outStream);
    }
}
