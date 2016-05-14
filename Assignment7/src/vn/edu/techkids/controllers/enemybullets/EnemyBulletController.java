package vn.edu.techkids.controllers.enemybullets;


import vn.edu.techkids.controllers.*;
import vn.edu.techkids.models.*;
import vn.edu.techkids.views.GameDrawer;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyBulletController extends SingleController implements Colliable {
    private int count = 0;
    public EnemyBulletController(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 5;
        CollisionPool.getInst().add(this);
    }

    public EnemyBulletController(EnemyBullet gameObject,
                                 GameDrawer gameDrawer,
                                 GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        System.out.println(gameObject.getClass().toString());
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    long currentTime  ;
    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController) {
            Plane plane = (Plane) c.getGameObject();
            EnemyBullet enemyBullet = (EnemyBullet)gameObject;
            plane.decreaseHP(enemyBullet.getDamage());

            if(enemyBullet.getSlow() > 1 && ((PlaneController)c).getSpeed() == ((PlaneController)c).SPEED) {
                currentTime = System.currentTimeMillis();
                ((PlaneController) c).setSpeed(((PlaneController) c).getSpeed() / enemyBullet.getSlow());
            }
            if(enemyBullet.getSlow() > 1){
                currentTime = System.currentTimeMillis();
            }
            if(System.currentTimeMillis() - currentTime >= 3){

                ((PlaneController)c).setSpeed(((PlaneController)c).SPEED);
            }
            if (plane.getHp() <= 0) {
                plane.setAlive(false);
            }

        }
        else if(c instanceof BulletController){
            //c.getGameObject().setAlive(false);
        }
    }
}
