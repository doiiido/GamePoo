package unb.cic.poo.game2d.fases;

import org.andengine.engine.handler.IUpdateHandler;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.waves.Wave;

public class FaseHandler implements IUpdateHandler {
	private Fase fase;
	
	public FaseHandler(Fase fase) {
		this.fase = fase;
	}
	

	@Override
	public void reset() {
	}
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		if(fase.waves.isEmpty()){
			GameManager.getInstance().getGameScene().unregisterUpdateHandler(this);
			fase.setFaseFinished(true);
		}
		else if(fase.getCurrentWave().waveFinished()){
			Wave wave = fase.nextWave();
			
			if(wave != null){
				wave.onWaveStart();
				wave.setWave();
			}
		}
	}

}
