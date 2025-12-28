import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageResizer {

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        // Create a new BufferedImage with the target dimensions and the same type as the original
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());

        // Get the Graphics2D object from the new image
        Graphics2D graphics2D = resizedImage.createGraphics();

        // Optional: Set rendering hints for quality (e.g., bilinear or bicubic interpolation)
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Draw the original image onto the new image, scaling it to fit the target dimensions
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);

        // Dispose of the Graphics2D context to release resources
        graphics2D.dispose();

        return resizedImage;
    }
}
