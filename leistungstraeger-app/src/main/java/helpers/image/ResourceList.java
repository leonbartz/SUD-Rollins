package helpers.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class ResourceList {

    public static Map<String, BufferedImage> loadFiles() throws IOException {
        Map<String, BufferedImage> imageList = new HashMap<>();


        BufferedImage image1 = loadByPath("images/characters/big_demon_idle_anim_f0.png");
        imageList.put("big_demon_idle_anim_f0.png", image1);
        BufferedImage image2 = loadByPath("images/default/image_not_found.png");
        imageList.put("image_not_found.png", image2);


        return imageList;
    }

    private static BufferedImage loadByPathAsStream(String path) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream(path);
        System.out.println(inputStream);
        return ImageIO.read(inputStream);
    }

    private static BufferedImage loadByPath(String path) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        File file = new File(loader.getResource(path).getPath());
        return ImageIO.read(file);
    }



    /*
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
            FileUtils.copyInputStreamToFile(loader.getResourceAsStream("/images/characters/big_demon_idle_anim_f0.png"), tempFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // loadSubdirectoryFiles(tempFile, imageList);
        return imageList;
    }
    */

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
