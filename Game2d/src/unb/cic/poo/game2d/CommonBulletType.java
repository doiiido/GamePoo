package unb.cic.poo.game2d;

//Classe responsável por instanciar uma bala.

public class CommonBulletType implements BulletType{
	public final static float COOLDOWN_TIME = 0.1f;
	public final static float TIME_LIMIT = 0.2f;
	private float timeAfterLastShot;
	
	@Override
	public Bullet getBullet(float pX, float pY, boolean isEnemy) {
		return new CommonBullet(pX, pY, isEnemy);
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
	
	//Getters e Setters
	
}
