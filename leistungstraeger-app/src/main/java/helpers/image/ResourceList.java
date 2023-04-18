package helpers.image;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class ResourceList {

    public static Map<String, File> loadFiles() {
        Map<String, File> imageList = new HashMap<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp", "image");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            FileUtils.copyInputStreamToFile(loader.getResourceAsStream("images"), tempFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        loadSubdirectoryFiles(tempFile, imageList);
        return imageList;
    }

    private static void loadSubdirectoryFiles(final File f, final Map<String, File> imageList) {
        File[] files = f.listFiles();
        if (files != null) {
            for (File file : files) {
                File[] subFiles = file.listFiles();
                if (subFiles == null) {
                    imageList.put(file.getName(), file);
                } else {
                    loadSubdirectoryFiles(file, imageList);
                }
            }
        }
    }
}
