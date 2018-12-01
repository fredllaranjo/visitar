package com.gmail.fredllaranjo.visitarapi.api.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.fredllaranjo.visitarapi.api.exception.BadRequestException;
import com.gmail.fredllaranjo.visitarapi.api.exception.NotFoundException;

/**
 * REST API for AssetBundles
 * 
 * @author fredllaranjo
 */
@RestController
@RequestMapping("/visitar/api/assetbundles")
public interface AssetBundlesService {

    /**
     * Retrieve the asset bundle file for the supplied name
     * 
     * @throws BadRequestException,
     *             NotFoundException, IOException
     */
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    void getAssetBundle(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response)
            throws BadRequestException, NotFoundException, BadRequestException, NotFoundException, IOException;

}
