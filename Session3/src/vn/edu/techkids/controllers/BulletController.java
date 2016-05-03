package vn.edu.techkids.controllers;

import vn.edu.techkids.models.Bullet;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;

/**
 * Created by qhuydtvt on 4/29/2016.
 */
public class BulletController extends SingleController {

    public static final int SPEED = 15;
    public Rectangle r;

    public BulletController(Bullet gameObject, ImageDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        gameVector.dy = -SPEED;
        r = new Rectangle(0 , 0 , 0 , 0);
    }
    public void EnemyBullet(){
        gameVector.dy = 15;
    }
    public Rectangle rectangle(){
        return new Rectangle(gameObject.getX() , gameObject.getY() , gameObject.getWidth() , gameObject.getHeight());
    }
}