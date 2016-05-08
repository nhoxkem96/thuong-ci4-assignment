package vn.edu.techkids.views;

import vn.edu.techkids.models.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by nhoxkem96 on 08/05/2016.
 */
public class ImageDrawerRotate implements GameDrawer{
    BufferedImage image;
    public void paint(GameObject gameObject, Graphics g){
        AffineTransform at = AffineTransform.getTranslateInstance(gameObject.getX() , gameObject.getY());
        at.rotate(Math.toRadians(-45));
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, at , null);
    }
    public ImageDrawerRotate(String url){
        try {
            image = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
