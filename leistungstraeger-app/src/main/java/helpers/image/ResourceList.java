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
    private final static String[] imageNames = new String[]{
            "big_demon_idle_anim_f0.png",
            "floor_1.png",
            "floor_2.png",
            "floor_3.png",
            "floor_4.png",
            "floor_5.png",
            "floor_6.png",
            "floor_7.png",
            "floor_8.png",
            "floor_ladder.png",
            "image_not_found.png",
            "knight_f_idle_anim_f0.png",
            "lizard_f_idle_anim_f0.png",
            "wall_side_mid_right.png",
            "wall_side_mid_left.png",
            "wall_top_left.png",
            "wall_top_right.png",
            "wall_top_mid.png",
            "wall_corner_left.png",
            "wall_corner_right.png",
            "wall_mid.png",
            "wall_corner_bottom_left.png",
            "wall_corner_bottom_right.png",
            "wall_top_mid.png",
            "wall_left.png",
            "wall_right.png",
            "wall_mid.png"};


    public static Map<String, BufferedImage> loadFiles() throws IOException {
        Map<String, BufferedImage> imageList = new HashMap<>();

        for (String imageName : imageNames) {
            BufferedImage image = loadByPathAsStream("usedImages/" + imageName);
            imageList.put(imageName, image);
        }
        return imageList;
    }

    private static BufferedImage loadByPathAsStream(String path) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream(path);
        return ImageIO.read(inputStream);
    }

    private static BufferedImage loadByPath(String path) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        File file = new File(loader.getResource(path).getPath());
        return ImageIO.read(file);
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
