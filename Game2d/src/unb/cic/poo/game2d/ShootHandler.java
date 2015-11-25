package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;
import unb.cic.poo.game2d.enemies.*;

public class ShootHandler implements IUpdateHandler {
	FreezedShootingEnemy enemy;
	float timer = 2f;
	private boolean stopShooting;
	
	public boolean isStopShooting() {
		return stopShooting;
	}
	public void setStopShooting(boolean stopShooting) {
		this.stopShooting = stopShooting;
	}
	
	public ShootHandler(FreezedShootingEnemy enemy){
		this.enemy = enemy;
		stopShooting = false;
	}
	/* O inimigo ira atirar de 1 em 1 segundo. */
	public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;
				if(timer <= 0){
					enemy.shoot();
					timer = 1f;
				}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
