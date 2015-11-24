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
				/*else if(GameManager.getInstance().getEnemies().contains(enemy) && enemy.collidesWith(GameManager.getInstance().getPlayer())){
					BaseScene aux = SceneManager.gameScene;
					((GameScene) aux).gameOver(false);
					GameManager.getInstance().getPlayer().decrementLife(Enemy.INFINITY);
					// Erro aqui! Aparentemente por estar adicionando e retirando em threads diferentes.
					// Uma op��o � colocar para decrementar a vida ao inv�s de dar gameOver direto, pois est�
					// funcionando por meio do Player
				}*/
			}
		});

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
