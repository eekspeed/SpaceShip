package Game;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageUtil {
    private static int w;
    private static int h;

    private static int type;

    private static BufferedImage image;

    private static Graphics2D graphics2d;

    public static BufferedImage rotateImage(BufferedImage bufferedimage, Double degree) {
        w    = bufferedimage.getWidth();
        h    = bufferedimage.getHeight();
        type = bufferedimage.getColorModel().getTransparency();

        image      = new BufferedImage(w, h, type);
        graphics2d = image.createGraphics();

        // 采用双线性插值
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);

        return image;
    }
}
