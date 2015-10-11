package unb.cic.poo.game2d;

//Interface dos tipos de criadores de bala.

public interface BulletType {
	
	public Bullet getBullet(float pX, float pY, boolean isEnemy);
}
