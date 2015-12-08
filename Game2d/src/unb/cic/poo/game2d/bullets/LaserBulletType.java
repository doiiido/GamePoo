package unb.cic.poo.game2d.bullets;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.SpaceshipAnimated;
import unb.cic.poo.game2d.scenes.SceneManager;

public class LaserBulletType extends BulletType{
	private static final float COOLDOWN_TIME = 1f;
	private static final int MAX_AMMO = 5;
	private SpaceshipAnimated nave;
	
	public LaserBulletType(SpaceshipAnimated nave) {
		this.ammoQuantity = MAX_AMMO;
		this.cooldown = COOLDOWN_TIME;
		this.onCooldown = false;
		this.nave = nave;
		this.swt = ResourceManager.getInstance().switchLaserTextureRegion;
	}
	
	@Override
	public void setBullet(float pX, float pY, boolean isEnemy) {
		if(!isEnemy){
			SceneManager.getInstance().getCurrentScene().registerUpdateHandler(new CooldownHandler(this));
			this.ammoQuantity--;
		}
		LaserBullet bullet = new LaserBullet(nave, pX, pY, isEnemy);
		GameManager.getInstance().getGameScene().attachChild(bullet);
	}

}
