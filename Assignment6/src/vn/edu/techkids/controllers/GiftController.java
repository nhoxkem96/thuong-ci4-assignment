package vn.edu.techkids.controllers;

import vn.edu.techkids.models.Bullet;
import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.models.GameVector;
import vn.edu.techkids.views.GameDrawer;

import java.awt.*;

/**
 * Created by nhoxkem96 on 12/05/2016.
 */
public class GiftController extends SingleController implements Colliable {

    private final static int ADD_DEFAUL = 2;
    public GiftController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    public GiftController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
    }

    @Override
    public void paint(Graphics g) {
        if(this.gameObject.isAlive())
            super.paint(g);
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof PlaneController){
            Bullet.damage += ADD_DEFAUL;
            BulletController.speed += ADD_DEFAUL;
        }
    }

}
