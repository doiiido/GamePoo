package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;

public class FaseHandler implements IUpdateHandler {
	private Fase fase;
	
	public FaseHandler(Fase fase) {
		this.fase = fase;
	}
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		if(!fase.waves.isEmpty()){
			if(fase.getCurrentWave().waveFinished()){
				fase.nextWave();
			}
		}
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
