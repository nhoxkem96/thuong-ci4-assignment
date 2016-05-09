package vn.edu.techkids.controllers;

import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.EnemyPlane;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyPlaneController extends SingleControllerWithHP implements Colliable {
    private int dx ;
    private int dy ;
    private EnemyBulletControllerManager enemyBulletControllerManager;
    private int count = 0;

    public EnemyPlaneController(EnemyPlane gameObject, GameDrawer gameDrawer , int dx , int dy) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = dx;
        this.gameVector.dx = dy;
        enemyBulletControllerManager = new EnemyBulletControllerManager();
        CollisionPool.getInst().add(this);
    }

    /* TODO override run */

    @Override
    public void run() {
        super.run();
        this.enemyBulletControllerManager.run();

        count++;
//        if (GameConfig.getInst().durationInSeconds(count) >= 2) {
//            count = 0;
//            EnemyBullet enemyBullet = new EnemyBullet(
//                    gameObject.getX() + gameObject.getWidth() / 2 - EnemyBullet.WIDTH / 2,
//                    gameObject.getY() + gameObject.getHeight(),
//                    EnemyBullet.WIDTH,
//                    EnemyBullet.HEIGHT
//            );
//            ImageDrawer imageDrawer = new ImageDrawer("resources/enemy_bullet.png");
//            EnemyBulletController enemyBulletController = new EnemyBulletController(
//                    enemyBullet,
//                    imageDrawer
//            );
//            this.enemyBulletControllerManager.add(enemyBulletController);
//        }
        if (GameConfig.getInst().durationInSeconds(count) >= 2) {
            count = 0;
            EnemyBullet enemyBullet = new EnemyBullet(
                    gameObject.getX() + gameObject.getWidth() / 4 - EnemyBullet.WIDTH / 2,
                    gameObject.getY() + gameObject.getHeight()/4,
                    EnemyBullet.WIDTH,
                    EnemyBullet.HEIGHT
            );
            ImageDrawer imageDrawer = new ImageDrawer("resources/enemy_bullet.png");
            EnemyBulletController enemyBulletController = new EnemyBulletController(
                    enemyBullet,
                    imageDrawer , 3
            );
            this.enemyBulletControllerManager.add(enemyBulletController);
        }


        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.enemyBulletControllerManager.paint(g);
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof PlaneController){
            Plane plane=(Plane)c.getGameObject();
            plane.decreaseHP(2);
        }
    }
}
