package unb.cic.poo.game2d;

import unb.cic.poo.game2d.scenes.SceneManager;

//Classe responsável por instanciar uma bala.

public class CommonBulletType extends BulletType{
	private static final float COOLDOWN_TIME = 0.1f;
	
	public CommonBulletType() {
		this.cooldown = COOLDOWN_TIME;
		this.onCooldown = false;
	}
	
	@Override
	public Bullet getBullet(float pX, float pY, boolean isEnemy) {
		SceneManager.getInstance().getCurrentScene().registerUpdateHandler(new CooldownHandler(this));
		return new CommonBullet(pX, pY, isEnemy);
	}
}
