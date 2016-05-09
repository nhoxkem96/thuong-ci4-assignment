package vn.edu.techkids.controllers;

import vn.edu.techkids.models.EnemyPlane;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.views.ImageDrawer;
import vn.edu.techkids.views.ImageDrawerRotate;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyPlaneControllerManager extends ControllerManager {

    private int count = 0;
    private int count2 = 0;

    private EnemyPlaneControllerManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        count2++;
        if(GameConfig.getInst().durationInSeconds(count) > 4) {
            count = 0;
            for (int x = 40; x < GameConfig.getInst().getScreenWidth() - 40; x += 100) {
                EnemyPlane enemyPlane= new EnemyPlane(x, 0, 32, 32);
                ImageDrawer imageDrawer =
                        new ImageDrawer("resources/plane1.png");

                EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                        enemyPlane,
                        imageDrawer , 2 , 0
                );
                this.singleControllerVector.add(enemyPlaneController);
            }
        }
        if(GameConfig.getInst().durationInSeconds(count2) > 1){
            count2 = 0;
            int x1 = 0;
            int y1 = 0;
            EnemyPlane enemyPlane2 = new EnemyPlane( x1 , y1 , 60 , 70 );
            ImageDrawerRotate imageDrawerRotate = new ImageDrawerRotate("resources/enemy_plane_white_1.png");
            EnemyPlaneController enemyPlaneController2 = new EnemyPlaneController(
                    enemyPlane2,
                    imageDrawerRotate , 2 , 2
            );
            this.singleControllerVector.add(enemyPlaneController2);
        }
    }

    private static EnemyPlaneControllerManager inst;
    public static EnemyPlaneControllerManager getInst() {
        if(inst == null) {
            inst = new EnemyPlaneControllerManager();
        }

        return inst;
    }

}
