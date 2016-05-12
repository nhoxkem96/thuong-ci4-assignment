package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.controllers.EnemyBulletController;
import vn.edu.techkids.controllers.EnemyBulletType;

/**
 * Created by nhoxkem96 on 12/05/2016.
 */
public class EnemyDiagonalShotBehavior implements EnemyShotBehavior {
    public EnemyBulletController doShot(int x, int y) {
        return EnemyBulletController.create(EnemyBulletType.DIAGONAL , x , y);
    }
}
