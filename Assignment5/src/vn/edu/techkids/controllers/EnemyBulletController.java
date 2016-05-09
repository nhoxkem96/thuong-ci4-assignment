package vn.edu.techkids.controllers;


import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyBulletController extends SingleController implements Colliable {
    private int damage;
    public EnemyBulletController(EnemyBullet gameObject, GameDrawer gameDrawer , int damage) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 5;
        this.gameVector.dx = -5;
        this.damage = damage;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController) {
            Plane plane = (Plane) c.getGameObject();
            plane.decreaseHP(damage);
            if (plane.getHp() <= 0) {
                plane.setAlive(false);
            }
        }
    }
}
