package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.controllers.enemybullets.EnemyBulletController;
import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.GameVector;
import vn.edu.techkids.views.ImageDrawer;

/**
 * Created by nhoxkem96 on 14/05/2016.
 */
public class EnemySlowShotBehavior implements EnemyShotBehavior {
    @Override
    public EnemyBulletController doShot(int x, int y) {
        EnemyBullet enemyBullet =
                new EnemyBullet(x, y,
                        EnemyBullet.WIDTH, EnemyBullet.HEIGHT, 2 , 2);
        ImageDrawer imageDrawer = new ImageDrawer("resources/enemy_bullet.png");

        EnemyBulletController enemyBulletController = new EnemyBulletController(
                enemyBullet,
                imageDrawer,
                new GameVector(0, 5)
        );
        return enemyBulletController;
    }
}
