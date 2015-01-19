/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.science.hack.jetstream.web;

import be.klak.junit.jasmine.JasmineSuite;
import be.klak.junit.jasmine.JasmineTestRunner;
import org.junit.runner.RunWith;

/**
 *
 * @author schroeder
 */
@RunWith(JasmineTestRunner.class)
@JasmineSuite(sourcesRootDir="src/main/webapp/WEB-INF/views/res/js", 
        sources = {"webgl/jetstreams.js"})
public class JetstreamsTest {
    
}
