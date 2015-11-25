package unb.cic.poo.game2d.bullets;

import org.andengine.engine.handler.IUpdateHandler;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.enemies.Enemy;

public class BulletHandler implements IUpdateHandler {
	Bullet bullet;
	
	public BulletHandler(Bullet bullet) {
		this.bullet = bullet;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		GameManager.getInstance().getGameEngine().runOnUpdateThread(new Runnable() {			
			@Override
			public void run() {
				if(bullet.checkHit()){
					if(!bullet.alreadyHit){
						bullet.alreadyHit = true;
						if(!bullet.isEnemyBullet()){
							for(Enemy enemy : bullet.getEnemiesHit()){
								bullet.OnEnemyHit(enemy); //Sobrescrevendo esse método poderemos adicionar o comportamento da bala quando ela atingir um inimigo.
															//(Explodir, aumentar score do player, etc).
							}
						}
						else{
							bullet.onPlayerHit();
						}
					}
					
				}
				else if(bullet.movementFinished){
					bullet.removeBullet();
				}
				
			}
		});

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
