package helpers.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */

public class ImageController {

    private static Map<String, BufferedImage> images = new HashMap<>();
    private static final Map<String, Image> cachedImages = new HashMap<>();

    private static BufferedImage loadImage(final File file) throws IOException {
        return ImageIO.read(file);
    }

    public static void loadImages() {
        /*
        Map<String, File> image_names = ResourceList.loadFiles();
        for (String key : image_names.keySet()) {
            try {
                images.put(key, loadImage(image_names.get(key)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         */

        try {
            images = ResourceList.loadFiles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Image getImage(String imageName, int width, int height) {
        if (width == 0 || height == 0) {
            return null;
        }
        String iName = imageName;
        if (images.get(iName) == null) {
            iName = "image_not_found.png";
        }
        Image cachedImage = cachedImages.get(iName);
        if (cachedImage != null &&
                cachedImage.getWidth(null) == width &&
                cachedImage.getHeight(null) == height) {
            return cachedImage;
        }
        Image newImage = images.get(iName);
        Image scaledImage = newImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        cachedImages.put(iName, scaledImage);
        return newImage;
    }
}