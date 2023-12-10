package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Loader {
    public static BufferedImage ImageLoader(String path){
        try{
            return ImageIO.read(Objects.requireNonNull(Loader.class.getResource(path)));
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
