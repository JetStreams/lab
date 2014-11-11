/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.jetstream.web.controller;

import de.science.hack.GlobeType;
import de.science.hack.JetStreamModelWriter;
import de.science.hack.MeshReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import toxi.geom.mesh.TriangleMesh;

/**
 * Controller to download the result.
 *
 * @author Mario
 */
@Controller
@Scope("session")
public class DownloadController implements Serializable {
    
    private static final String WIND = "wind.stl";

    private static final String GLOBE = "globe.stl";

    static final String MIME = "application/sla";

    @Autowired
    private ResultCache resultCache;

    /**
     * Download the mesh.
     *
     * @param response {@link HttpServletResponse}
     * @throws IOException
     */
    @RequestMapping(value = "/wind")
    public void windData(HttpServletResponse response) throws IOException {
        
        TriangleMesh wind = resultCache.getMesh();
        wind.saveAsSTL(prepareResponse(response));
    }

    @RequestMapping(value = "/globe/{type}")
    public void globe(HttpServletResponse response, @PathVariable String type) throws IOException {
        
        Optional<GlobeType> opt = GlobeType.getByKey(type);
        MeshReader modelReader = new MeshReader();
        TriangleMesh globe = modelReader.readGlobe(opt);
        globe.saveAsSTL(prepareResponse(response));
    }

    private OutputStream prepareResponse(HttpServletResponse response) throws IOException {
        response.setContentType(MIME);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", GLOBE);
        response.setHeader(headerKey, headerValue);
        return response.getOutputStream();
    }
}
