package helpers.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ImageController {

    private static final Map<String, BufferedImage> images = new HashMap<>();
    private static final Map<String, Image> cachedImages = new HashMap<>();

    private static BufferedImage loadImage(final File file) throws IOException {
        return ImageIO.read(file);
    }

    public static void loadImages() {
        Map<String, File> image_names = ResourceList.loadFiles();
        for (String key : image_names.keySet()) {
            try {
                images.put(key, loadImage(image_names.get(key)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Image getImage(String imageName, int width, int height) {
        if (width == 0 || height == 0) {
            return null;
        }
        Image cachedImage = cachedImages.get(imageName);
        if (cachedImage != null &&
                cachedImage.getWidth(null) == width &&
                cachedImage.getHeight(null) == height) {
            return cachedImage;
        }
        Image newImage = images.get(imageName);
        if (newImage == null) {
            newImage = images.get("image_not_found.png");
        }
        Image scaledImage = newImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        cachedImages.put(imageName, scaledImage);
        return newImage;
    }
}