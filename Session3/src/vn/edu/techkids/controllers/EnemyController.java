package vn.edu.techkids.controllers;

import vn.edu.techkids.models.Bullet;
import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;
import java.util.Vector;

/**
 * Created by nhoxkem96 on 02/05/2016.
 */
public class EnemyController extends SingleController {
    public final int speed = 3;

    public Rectangle rectangle;
    public Vector<BulletController> enemyBulletControllerVector;
    public EnemyController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        enemyBulletControllerVector = new Vector<BulletController>();
        rectangle = new Rectangle(0 , 0 , 0 , 0);
    }

    public static EnemyController enemyController;

    public static EnemyController getEnemyController(){
        if(enemyController == null) {
            Plane plane = new Plane(100, 100, 60, 70);
            ImageDrawer planeDrawer = new ImageDrawer("resources/plane1.png");
            enemyController = new EnemyController(plane, planeDrawer);
        }
        return enemyController;
    }

    public void shot(){
        Bullet bullet = new Bullet(
                gameObject.getX() + gameObject.getWidth() / 2 - Bullet.DEFAULT_WIDTH / 3 ,
                gameObject.getY() +gameObject.getHeight(),
                Bullet.DEFAULT_WIDTH /2,
                Bullet.DEFAULT_HEIGHT/2
        );
        ImageDrawer imageDrawer = new ImageDrawer("resources/bullet.png");
        BulletController bulletController = new BulletController(
                bullet,
                imageDrawer
        );
        this.enemyBulletControllerVector.add(bulletController);
        for(int i = 0 ; i < enemyBulletControllerVector.size() ; i++){
            if(enemyBulletControllerVector. get(i).gameObject.getY() > 600){
                enemyBulletControllerVector.remove(i);
            }
        }
    }
    public void move(){
        this.gameVector.dy = 1;
    }
    public void run(){
        super.run();
        for(BulletController bulletController : this.enemyBulletControllerVector) {
            bulletController.EnemyBullet();
            bulletController.run();
        }
    }
    public void paint(Graphics graphics){
        super.paint(graphics);
        for (BulletController bulletController : this.enemyBulletControllerVector)
            bulletController.paint(graphics);
    }
    public Rectangle getBounds(){
        return new Rectangle(gameObject.getX() , gameObject.getY() , gameObject.getWidth() , gameObject.getHeight());
    }
}
