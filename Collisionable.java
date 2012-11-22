
interface Collisionable
{
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    int collisionCheckSum();
    void collisionedWith(int o);
    boolean isDead(Box box);
}