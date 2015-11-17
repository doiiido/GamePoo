package unb.cic.poo.game2d.bullets;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.scenes.SceneManager;

//Classe responsável por instanciar uma bala.

public class CommonBulletType extends BulletType{
	private static final float COOLDOWN_TIME = 0.1f;
	
	public CommonBulletType() {
		this.cooldown = COOLDOWN_TIME;
		this.onCooldown = false;
	}
	
	@Override
	public void setBullet(float pX, float pY, boolean isEnemy) {
		if(!isEnemy)
			SceneManager.getInstance().getCurrentScene().registerUpdateHandler(new CooldownHandler(this));
		CommonBullet bullet = new CommonBullet(pX, pY, isEnemy);
		GameManager.getInstance().getGameScene().attachChild(bullet);
	}
}
