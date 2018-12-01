package com.gmail.fredllaranjo.visitarapi.core.service.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import com.gmail.fredllaranjo.visitarapi.api.exception.BadRequestException;
import com.gmail.fredllaranjo.visitarapi.api.exception.NotFoundException;
import com.gmail.fredllaranjo.visitarapi.api.service.AssetBundlesService;
import com.gmail.fredllaranjo.visitarapi.core.business.AssetBundlesBusiness;
import com.gmail.fredllaranjo.visitarapi.core.validator.AssetBundlesValidator;

public class AssetBundlesServiceImpl implements AssetBundlesService {

    private final AssetBundlesValidator validator;
    private final AssetBundlesBusiness business;

    public AssetBundlesServiceImpl(AssetBundlesValidator validator, AssetBundlesBusiness business) {
        this.validator = validator;
        this.business = business;
    }

    @Override
    public void getAssetBundle(String name, HttpServletRequest request, HttpServletResponse response)
            throws BadRequestException, NotFoundException, IOException {
        validator.validateGetAssetBundle(name);
        InputStream fis = business.getAssetBundleIS(name);
        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment;filename=" + name);

        int x = fis.available();
        byte byteArray[] = new byte[x];
        fis.read(byteArray);

        response.getOutputStream().write(byteArray);
        response.flushBuffer();
        fis.close();
    }
}
