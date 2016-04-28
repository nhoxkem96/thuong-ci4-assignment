package vn.edu.techkids;

import java.awt.*;

/**
 * Created by nhoxkem96 on 28/04/2016.
 */
public class EnemyPlane {
    public int x;
    public int y;
    private Image image;
    public final int WIDTH = 50;
    public final int HEIGHT = 40;

    public EnemyPlane(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void paint(Graphics g){
        g.drawImage(image,x,y, WIDTH, HEIGHT, null);
    }

    public void run(){
        y += 3;
    }

}
