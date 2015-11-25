package unb.cic.poo.game2d.waves;

import java.util.ArrayList;

import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.scenes.SceneManager;

public abstract class Wave {
	protected ArrayList<Enemy> enemies;
	
	public Wave(){
		enemies = new ArrayList<Enemy>();
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public boolean waveFinished(){
		return enemies.isEmpty();
	}
	
	public void setWave(){
		GameManager.getInstance().setEnemies(enemies);
		for(Enemy enemy: this.enemies){
			GameManager.getInstance().getGameScene().attachChild(enemy);
		}
	}

	/*Esse metodo eh chamado antes de iniciar essa wave*/
	public void onWaveStart() {
		
	}

	/*Esse metodo eh chamado toda vez que uma wave eh finalizada*/
	public void onWaveFinish() {
		
	}
}
