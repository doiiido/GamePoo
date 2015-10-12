package unb.cic.poo.game2d;

import java.util.ArrayList;

public abstract class Wave {
	private ArrayList<Enemy> enemies;
	
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
