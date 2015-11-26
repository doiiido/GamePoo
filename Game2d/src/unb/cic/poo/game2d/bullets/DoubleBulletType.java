package unb.cic.poo.game2d.bullets;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.scenes.SceneManager;

public class DoubleBulletType extends BulletType{
	private static final float COOLDOWN_TIME = 0.2f;
	
	public DoubleBulletType() {
		this.cooldown = COOLDOWN_TIME;
		this.onCooldown = false;
		this.swt = ResourceManager.switchDoubleTextureRegion;
	}
	@Override
	public void setBullet(float pX, float pY, boolean isEnemy) {
		if(!isEnemy)
			SceneManager.getInstance().getCurrentScene().registerUpdateHandler(new CooldownHandler(this));
		//instancia de duas CommonBullets, sendo uma delas posicionada em pY-20 e a outra em pY+20
		CommonBullet bullet1 = new CommonBullet(pX, pY-20, isEnemy); 
		GameManager.getInstance().getGameScene().attachChild(bullet1);
		CommonBullet bullet2 = new CommonBullet(pX, pY+20, isEnemy);
		GameManager.getInstance().getGameScene().attachChild(bullet2);
	}

}
