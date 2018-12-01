package com.gmail.fredllaranjo.visitarapi.core.validator;

import com.gmail.fredllaranjo.visitarapi.api.exception.BadRequestException;

public class AssetBundlesValidator {

    public AssetBundlesValidator() {

    }

    public void validateGetAssetBundle(String assetBundleName) throws BadRequestException {
        if (assetBundleName == null || assetBundleName.equals("")) {
            throw new BadRequestException("O nome do arquivo é mandatório");
        }
    }
}
