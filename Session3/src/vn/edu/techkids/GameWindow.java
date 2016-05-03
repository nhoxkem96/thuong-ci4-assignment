package vn.edu.techkids;
/* TODO packaage exanplation */

import vn.edu.techkids.controllers.EnemyController;
import vn.edu.techkids.controllers.PlaneController;
import vn.edu.techkids.controllers.PlaneDirection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by qhuydtvt on 4/24/2016.
 */
public class GameWindow extends Frame implements Runnable {
    Image backgroundImage;
    Thread thread;
    Image backbufferImage;
    PlaneController planeController1;
    EnemyController enemyController;
    long nextShot = 0;
    long recoilTime = 300;
    long nextEnemyShot = 0;
    long enemyRecoilTime = 2000;

    public GameWindow () {
        this.setVisible(true);
        this.setSize(400, 600);

        this.planeController1 = PlaneController.getPlaneController1();

        this.enemyController = EnemyController.getEnemyController();
        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);

            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
 //               System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
 //               System.out.println("keyPressed");
 //               System.out.println(e.getKeyCode());

                PlaneDirection planeDirection = PlaneDirection.NONE;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        planeDirection = PlaneDirection.UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        planeDirection = PlaneDirection.DOWN;
                        break;
                    case KeyEvent.VK_LEFT:
                        planeDirection = PlaneDirection.LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        planeDirection = PlaneDirection.RIGHT;
                        break;
                    case KeyEvent.VK_SPACE:
                        if(System.currentTimeMillis()  > nextShot){
                            nextShot = System.currentTimeMillis() + recoilTime;
                            planeController1.shot();
                            break;
                        }
                }

                planeController1.move(planeDirection);
                /*TODO static explanation*/
            }

            @Override
            public void keyReleased(KeyEvent e) {
 //               System.out.println("keyReleased");

                PlaneDirection planeDirection = PlaneDirection.NONE;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        planeDirection = PlaneDirection.STOP_Y;
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        planeDirection = PlaneDirection.STOP_X;
                        break;
                }
                planeController1.move(planeDirection);
            }
        });

        enemyController.move();

        this.addMouseMotionListener(new MouseMotionListener(){

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
//                dx2 = e.getX();
//                dy2 = e.getY();
//x2 => e.getX(); y2 => e.getY()

//                if(e.getX() - 5 > x2) {
//                    dx2 = 5;
//                } else if(e.getX() +5 < x2) {
//                    dx2 = -5;
//                } else {
//                    dx2 = 0;
//                }
  //              System.out.println("mouseMoved");
            }
        });
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void update(Graphics g) {

        if(backbufferImage == null){
            backbufferImage =  new BufferedImage(400, 600, 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        backbufferGraphics.drawImage(backgroundImage, 0, 0, null);

        planeController1.paint(backbufferGraphics);
            enemyController.paint(backbufferGraphics);

            if(System.currentTimeMillis()  > nextEnemyShot){
                nextEnemyShot = System.currentTimeMillis() + enemyRecoilTime;
                enemyController.shot();
            }
           // enemyControllerVector.get(0).shot();

        g.drawImage(backbufferImage, 0, 0, null);
        Rectangle rec1 = new Rectangle(enemyController.gameObject.getX() , enemyController.gameObject.getY() , enemyController.gameObject.getWidth() , enemyController.gameObject.getHeight());
        for(int i = 0 ; i < planeController1.bulletControllerVector.size() ; i++){
            Rectangle rect2 = new Rectangle(planeController1.bulletControllerVector.get(i).gameObject.getX(),
                    planeController1.bulletControllerVector.get(i).gameObject.getY(),
                    planeController1.bulletControllerVector.get(i).gameObject.getWidth(),
                    planeController1.bulletControllerVector.get(i).gameObject.getHeight());
            if(rect2.intersects(rec1)){
                System.out.println("Va cham");
                planeController1.bulletControllerVector.remove(i);
            }
        }
    }

    @Override
    public void run() {
        long count = 0;

        while(true){
//            count++;
//            System.out.println(count);
            try {

                Point mousePoint = MouseInfo.getPointerInfo().getLocation();

                mousePoint.x -= getLocationOnScreen().x;
                mousePoint.y -= getLocationOnScreen().y;

                /* TODO player2 moving */
//                if(mousePoint.x - 5 > plane2.x) {
//                    plane2.dx = 5;
//                } else if(mousePoint.x + 5 < plane2.x) {
//                    plane2.dx = -5;
//                } else {
//                    plane2.dx = 0;
//                }
//
//                if(mousePoint.y - 5 > plane2.y) {
//                    plane2.dy = 5;
//                } else if(mousePoint.y + 5 < plane2.y) {
//                    plane2.dy = -5;
//                } else {
//                    plane2.dy = 0;
//                }
 //               plane2.run();


                planeController1.run();
                enemyController.run();

                repaint();


                Thread.sleep(17);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
