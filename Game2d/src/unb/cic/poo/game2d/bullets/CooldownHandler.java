package unb.cic.poo.game2d.bullets;

import org.andengine.engine.handler.IUpdateHandler;

import unb.cic.poo.game2d.scenes.SceneManager;

public class CooldownHandler implements IUpdateHandler {
	float timeAfterLastShot;
	BulletType bulletType; 
	
	public CooldownHandler(BulletType bulletType){
		this.timeAfterLastShot = 0;
		this.bulletType = bulletType;
		this.bulletType.setOnCooldown(true);
	}
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		timeAfterLastShot += pSecondsElapsed;
		if(this.timeAfterLastShot >= this.bulletType.getCooldownTime()){
			this.bulletType.setOnCooldown(false);
			SceneManager.getInstance().getCurrentScene().unregisterUpdateHandler(this);
		}
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
