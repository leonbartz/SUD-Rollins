package helpers.image;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class ResourceList{

    public static Map<String, File> loadFiles() {
        Map<String, File> imageList = new HashMap<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        File files = new File(loader.getResource("images").getPath());
        loadSubdirectoryFiles(files, imageList);
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