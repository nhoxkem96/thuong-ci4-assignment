package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.controllers.EnemyBulletController;
import vn.edu.techkids.controllers.EnemyBulletType;

/**
 * Created by qhuydtvt on 5/9/2016.
 */
public class EnemyDirectShotBehavior implements EnemyShotBehavior {

    @Override
    public EnemyBulletController doShot(int x, int y) {
        return EnemyBulletController.create(EnemyBulletType.DIRECT , x , y);
    }
}
