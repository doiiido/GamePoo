package unb.cic.poo.game2d;

//Interface dos tipos de criadores de bala.

public interface BulletType {
	
	public Bullet getBullet(float pX, float pY, boolean isEnemy);
	public float getTimeLimit();
	public float getCooldownTime();
	public float getTimeAfterLastShot();
	public void setTimeAfterLastShot(float pSeconds);
	public void incrementTimeAfterLastShot(float pSeconds);
}
