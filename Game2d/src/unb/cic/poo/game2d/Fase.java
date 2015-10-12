package unb.cic.poo.game2d;

import java.util.LinkedList;

import org.andengine.engine.handler.IUpdateHandler;

public abstract class Fase {
	private LinkedList<Wave> waves;
	
	public Fase(){
		waves = new LinkedList<Wave>();
		GameManager.getInstance().getGameEngine().registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				if(getCurrentWave().waveFinished()){
					nextWave();
				}
			}
		});
	}

	public LinkedList<Wave> getWaves() {
		return waves;
	}

	public void setWaves(LinkedList<Wave> waves) {
		this.waves = waves;
	}
	
	public Wave getCurrentWave(){
		return waves.get(0);
	}
	
	public Wave nextWave(){
		Wave nextWave;
		
		waves.remove(0);
		nextWave = waves.get(0);
		nextWave.setWave();
		return nextWave;
	}
}
