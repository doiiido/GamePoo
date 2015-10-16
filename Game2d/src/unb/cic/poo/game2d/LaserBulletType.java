package unb.cic.poo.game2d;



import unb.cic.poo.game2d.BulletType;

public class LaserBulletType implements BulletType{
	private final static float COOLDOWN_TIME = 1.5f;
	private final static float TIME_LIMIT = 2f;
	private float timeAfterLastShot;

	@Override
	public Bullet getBullet(float pX, float pY, boolean isEnemy) {
		return new LaserBullet(pX, pY, isEnemy);
	}

	@Override
	public float getTimeAfterLastShot() {
		return this.timeAfterLastShot;
	}
	
	@Override
	public void setTimeAfterLastShot(float pSeconds) {
		this.timeAfterLastShot = pSeconds;
	}

	@Override
	public void incrementTimeAfterLastShot(float pSeconds) {
		this.timeAfterLastShot += pSeconds;
	}

	@Override
	public float getTimeLimit() {
		return TIME_LIMIT;
	}

	@Override
	public float getCooldownTime() {
		return COOLDOWN_TIME;
	}

}
