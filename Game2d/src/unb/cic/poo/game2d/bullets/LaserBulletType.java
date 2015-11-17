package unb.cic.poo.game2d;



import unb.cic.poo.game2d.BulletType;
import unb.cic.poo.game2d.scenes.SceneManager;

public class LaserBulletType extends BulletType{
	private static final float COOLDOWN_TIME = 1f;
	
	public LaserBulletType() {
		this.cooldown = COOLDOWN_TIME;
		this.onCooldown = false;
	}
	
	@Override
	public Bullet getBullet(float pX, float pY, boolean isEnemy) {
		SceneManager.getInstance().getCurrentScene().registerUpdateHandler(new CooldownHandler(this));
		return new LaserBullet(pX, pY, isEnemy);
	}

}
