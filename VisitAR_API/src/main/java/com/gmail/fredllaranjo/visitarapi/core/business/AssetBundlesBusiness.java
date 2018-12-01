package com.gmail.fredllaranjo.visitarapi.core.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.core.io.ClassPathResource;

import com.gmail.fredllaranjo.visitarapi.api.Constants;
import com.gmail.fredllaranjo.visitarapi.api.exception.NotFoundException;

public class AssetBundlesBusiness {

    public AssetBundlesBusiness() {
    }

    public File getAssetBundle(String assetBundleName) throws NotFoundException {
        File file;
        try {
            file = new ClassPathResource(Constants.PATH + assetBundleName).getFile();
            //ClassLoader loader = AssetBundlesBusiness.class.getClassLoader();
            //File file = new File(loader.getResource(Constants.PATH + assetBundleName).getFile());
            if (!file.exists()) {
                throw new NotFoundException("Não foi possível encontrar o arquivo: " + assetBundleName + ".");
            }
        } catch (IOException e) {
            throw new NotFoundException("Não foi possível encontrar o arquivo: " + assetBundleName + ".");
        }
        return file;
    }

    public InputStream getAssetBundleIS(String assetBundleName) throws FileNotFoundException, NotFoundException {
        return new FileInputStream(getAssetBundle(assetBundleName));
    }
    /**
     * Utility method to save InputStream data to target location/file
     * 
     * @param inStream
     *            - InputStream to be saved
     * @param target
     *            - full path to destination file
     */
    private void saveToFile(InputStream inStream, String target)
            throws IOException {
        OutputStream out = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(new File(target));
        while ((read = inStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
    }
    /**
     * Creates a folder to desired location if it not already exists
     * 
     * @param dirName
     *            - full path to the folder
     * @throws SecurityException
     *             - in case you don't have permission to create the folder
     */
    private void createFolderIfNotExists(String dirName)
            throws SecurityException {
        File theDir = new File(dirName);
        if (!theDir.exists()) {
            theDir.mkdir();
        }
    }

}
