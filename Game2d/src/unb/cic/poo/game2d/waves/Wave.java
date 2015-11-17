package unb.cic.poo.game2d.waves;

import java.util.ArrayList;

import unb.cic.poo.game2d.Enemy;

public abstract class Wave {
	protected ArrayList<Enemy> enemies;
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public boolean waveFinished(){
		return enemies.isEmpty();
	}
	
	public abstract void setWave();
}
