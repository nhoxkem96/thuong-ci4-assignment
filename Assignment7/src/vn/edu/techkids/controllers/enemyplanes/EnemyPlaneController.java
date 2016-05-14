package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.Utils;
import vn.edu.techkids.controllers.Colliable;
import vn.edu.techkids.controllers.CollisionPool;
import vn.edu.techkids.controllers.PlaneController;
import vn.edu.techkids.controllers.SingleControllerWithHP;
import vn.edu.techkids.controllers.enemybullets.EnemyBulletController;
import vn.edu.techkids.controllers.enemybullets.EnemyBulletControllerManager;
import vn.edu.techkids.models.*;
import vn.edu.techkids.views.AnimationDrawer;
import vn.edu.techkids.views.GameDrawer;

import java.awt.*;

/**
 * Created by qhuydtvt on 5/6/2016.
 */


public class EnemyPlaneController extends SingleControllerWithHP implements Colliable {

    private EnemyShotBehavior enemyShotBehavior;

//    private EnemyBulletControllerManager enemyBulletControllerManager;
    private int count = 0;

    public EnemyPlaneController(EnemyPlane gameObject,
                                GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 2;
//        enemyBulletControllerManager = new EnemyBulletControllerManager();
        CollisionPool.getInst().add(this);
    }

    public EnemyPlaneController(EnemyPlane gameObject,
                                GameDrawer gameDrawer,
                                GameVector gameVector) {
        super(gameObject, gameDrawer);
        this.gameVector = gameVector;
//        enemyBulletControllerManager = new EnemyBulletControllerManager();
        CollisionPool.getInst().add(this);
    }

    public EnemyPlaneController(EnemyPlane gameObject,
                                GameDrawer gameDrawer,
                                GameVector gameVector,
                                EnemyShotBehavior enemyShotBehavior) {
        super(gameObject, gameDrawer);
        this.gameVector = gameVector;
//        enemyBulletControllerManager = new EnemyBulletControllerManager();
        this.enemyShotBehavior = enemyShotBehavior;
        CollisionPool.getInst().add(this);
    }

    /* TODO override run */

    @Override
    public void run() {
        super.run();
//        this.enemyBulletControllerManager.run();

        count++;
        if (GameConfig.getInst().durationInSeconds(count) >= 2) {
            count = 0;
            shot();
        }

        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    private void shot() {
        /* TODO */
        if(enemyShotBehavior != null) {
            EnemyBulletController enemyBulletController = enemyShotBehavior.doShot(
                   gameObject.getX() + gameObject.getWidth() / 2 - EnemyBullet.WIDTH / 2,
                   gameObject.getY() + gameObject.getHeight()
            );
            EnemyBulletControllerManager.getInst().add(enemyBulletController);
        }
    }

    public void die() {
        /* Effect */
        /* Play sound */
        Utils.playSound("resources/sound_explosion.wav", false);
        this.gameObject.setAlive(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        this.enemyBulletControllerManager.paint(g);
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof PlaneController){
            Plane plane=(Plane)c.getGameObject();
            plane.decreaseHP(2);
        }
    }


    public static EnemyPlaneController create(EnemyPlaneType enemyPlaneType,
                                              int x, int y) {
        EnemyPlane enemyPlane =
                new EnemyPlane(x,y,EnemyPlane.WIDTH_DEFAULT, EnemyPlane.HEIGHT_DEFAULT);
        EnemyPlaneController enemyPlaneController = null;
        GameVector gameVector = null;


        switch (enemyPlaneType){
            case BLACK:
//                ImageDrawer blackPlaneDrawer =
//                        new ImageDrawer("resources/plane1.png");
                AnimationDrawer blackPlaneDrawer = new AnimationDrawer(new String[]{
                        "resources/enemy_plane_yellow_1.png",
                        "resources/enemy_plane_yellow_2.png",
                        "resources/enemy_plane_yellow_3.png",
                });
                gameVector = new GameVector(0, 2);
                enemyPlaneController =
                        new EnemyPlaneController(
                                enemyPlane,
                                blackPlaneDrawer,
                                gameVector,
                                new EnemySlowShotBehavior()
                                );
                break;
            case WHITE:
//                ImageDrawer whitePlaneDrawer =
//                        new ImageDrawer("resources/enemy_plane_white_1.png");
                AnimationDrawer whitePlaneDrawer = new AnimationDrawer(
                        new String[] {
                                "resources/enemy_plane_white_1.png",
                                "resources/enemy_plane_white_2.png",
                                "resources/enemy_plane_white_3.png",
                        }
                );
                gameVector = new GameVector(2, 2);
                enemyPlaneController = new EnemyPlaneController(enemyPlane, whitePlaneDrawer, gameVector , new EnemyDirectShotBehavior());
                break;
        }

        return enemyPlaneController;
    }
}






