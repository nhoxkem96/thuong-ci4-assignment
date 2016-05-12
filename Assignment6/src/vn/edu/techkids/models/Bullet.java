package vn.edu.techkids.models;

public class Bullet extends GameObject {
    public static final int DEFAULT_WIDTH = 13;
    public static final int DEFAULT_HEIGHT = 33;

    public static int damage = 1;

    public static int getDamage() {
        return damage;
    }

    public static void setDamage(int damage) {
        Bullet.damage = damage;
    }

    public Bullet(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void increaseDamage(int x){
        damage += x;
    }
}
