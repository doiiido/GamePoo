package unb.cic.poo.game2d.bullets;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.SpaceshipAnimated;
import unb.cic.poo.game2d.scenes.SceneManager;

public class FlamethrowerBulletType extends BulletType{
	private static final float COOLDOWN_TIME = 0.3f;
	private static final int MAX_AMMO = 20;
	private SpaceshipAnimated nave;
	
	public FlamethrowerBulletType(SpaceshipAnimated nave) {
		this.ammoQuantity = MAX_AMMO;
		this.cooldown = COOLDOWN_TIME;
		this.onCooldown = false;
		this.nave = nave;
		this.swt = ResourceManager.switchFlameTextureRegion;
	}
	
	@Override
	public void setBullet(float pX, float pY, boolean isEnemy) {
		if(!isEnemy){
			SceneManager.getInstance().getCurrentScene().registerUpdateHandler(new CooldownHandler(this));
			this.ammoQuantity--;
		}
		FlamethrowerBullet bullet = new FlamethrowerBullet(nave, pX, pY, isEnemy);
		GameManager.getInstance().getGameScene().attachChild(bullet);
	}

}
