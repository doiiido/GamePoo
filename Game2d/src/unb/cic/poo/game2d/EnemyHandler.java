package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;
import unb.cic.poo.game2d.enemies.Enemy;

public class EnemyHandler implements IUpdateHandler {

	private Enemy enemy;
	
	public EnemyHandler(Enemy enemy){
		this.enemy = enemy;
	}
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		GameManager.getInstance().getGameEngine().runOnUpdateThread(new Runnable() {			
			@Override
			public void run() {
				if(enemy.movementFinished){
					enemy.removeEnemy();
				}
				else if(GameManager.getInstance().getEnemies().contains(enemy) && enemy.collidesWith(GameManager.getInstance().getPlayer())){
					GameManager.getInstance().getPlayer().decrementLife(Enemy.INFINITY);
				}
			}
		});

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
