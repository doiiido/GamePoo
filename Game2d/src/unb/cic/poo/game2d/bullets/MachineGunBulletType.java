package unb.cic.poo.game2d.bullets;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.scenes.SceneManager;

public class MachineGunBulletType extends BulletType{
	private static final float COOLDOWN_TIME = 0.05f;
	private static final int MAX_AMMO = 40;
	private static int alternator = 1;
	
	public MachineGunBulletType() {
		this.ammoQuantity = MAX_AMMO;
		this.cooldown = COOLDOWN_TIME;
		this.onCooldown = false;
		this.swt = ResourceManager.switchHeavyTextureRegion;
		this.automatic = true;
	}
	@Override
	public void setBullet(float pX, float pY, boolean isEnemy) {
		if(!isEnemy){
			SceneManager.getInstance().getCurrentScene().registerUpdateHandler(new CooldownHandler(this));
			this.ammoQuantity--;
		}
			
		//Por meio da variavel alternator, é possível variar a posicão dos tiros, alternando entre cima e baixo (pY+10 e pY-10).
		if(alternator == 1){
			CommonBullet bullet = new CommonBullet(pX, pY-10, isEnemy); 
			GameManager.getInstance().getGameScene().attachChild(bullet);
			alternator = 2;
		} else{
			CommonBullet bullet = new CommonBullet(pX, pY+10, isEnemy);
			GameManager.getInstance().getGameScene().attachChild(bullet);
			alternator = 1;
		}
		
	}

}
