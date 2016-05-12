package vn.edu.techkids.controllers;

import vn.edu.techkids.controllers.enemyplanes.EnemyPlaneControllerManager;
import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.views.GameDrawer;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by nhoxkem96 on 12/05/2016.
 */
public class BombController extends SingleController implements Colliable {
    public BombController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if(this.gameObject.isAlive())
        super.paint(g);
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController) {
            EnemyPlaneControllerManager enemyPlaneControllerManager = EnemyPlaneControllerManager.getInst();
            Vector<SingleController> enemyPlaneControllerVector = enemyPlaneControllerManager.getSingleControllerVector();
            Iterator<SingleController> iterator = enemyPlaneControllerVector.iterator();
            while(iterator.hasNext()) {
                SingleController singleController = iterator.next();
                iterator.remove();
            }
        }
    }
}
