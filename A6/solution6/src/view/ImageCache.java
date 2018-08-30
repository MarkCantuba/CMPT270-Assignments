package view;

import java.awt.image.BufferedImage;
// import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * A singleton that is a container storing images. When an image is to be accessed, first search the
 * container. If it isn't found, read the image from disk and store it in the container for future
 * accesses.
 */
public class ImageCache {
    /* The one instance for this Singleton class. */
    private static ImageCache instance = null;

    /**
     * @return the actual instance of the class
     */
    public static ImageCache getInstance() {
        if (instance == null) {
            instance = new ImageCache();
        }
        return instance;
    }

    /** The name of the directory that contains the images. */
    public static final String IMAGE_DIRECTORY = "images/";

    /** The container where the images are actually stored. */
    private HashMap<String, BufferedImage> cache;

    private ImageCache() {
        cache = new HashMap<String, BufferedImage>();
    }

    /**
     * @param fileName the name of the file containing the image to be retrieved
     * @return the image with name fileName
     */
    public BufferedImage getImage(String fileName) {
        BufferedImage image = cache.get(fileName);
        if (image == null) {
            try {
                // image = ImageIO.read(new File(IMAGE_DIRECTORY + fileName));
                // images are stored with the (binary version of the) classes
                image = ImageIO.read(ClassLoader.getSystemResource(IMAGE_DIRECTORY + fileName));
                cache.put(fileName, image);
            } catch (Exception e) {
                throw new IllegalArgumentException("The image in " + IMAGE_DIRECTORY + fileName
                        + " could not be read");
            }
        }
        return image;
    }
}
