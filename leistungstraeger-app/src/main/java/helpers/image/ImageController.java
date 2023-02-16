package helpers.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ImageController {

    private static final Map<String, BufferedImage> images = new HashMap<>();
    private static final Map<String, Image> cachedImages = new HashMap<>();

    private static BufferedImage loadImage(URL url) throws IOException {
        return ImageIO.read(url);
    }

    public static void loadImages() {
        Map<String, String> image_names = new HashMap<>() {{
            put("room", "room_images/room.png");
            put("character", "character_images/character.png");
        }};
        for (String key : image_names.keySet()) {
            ClassLoader cl = new ClassLoader() {};
            URL url = cl.getResource(image_names.get(key));
            try {
                images.put(key, loadImage(url));
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
        Image newImage = images.get(imageName).getScaledInstance(width, height, Image.SCALE_SMOOTH);
        cachedImages.put(imageName, newImage);
        return newImage;
    }
}