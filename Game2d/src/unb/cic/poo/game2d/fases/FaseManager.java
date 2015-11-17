package unb.cic.poo.game2d.fases;

import java.util.LinkedList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;

import unb.cic.poo.game2d.scenes.GameScene;
import unb.cic.poo.game2d.scenes.SceneManager;

public class FaseManager implements IUpdateHandler{
	private LinkedList<Fase> fases;
	
	public FaseManager(LinkedList<Fase> fases){
		this.fases = fases;
	}

	public LinkedList<Fase> getFases() {
		return fases;
	}

	public void setFases(LinkedList<Fase> fases) {
		this.fases = fases;
	}

	@Override
	public void onUpdate(float pSecondsElapsed) {
		if(getCurrentFase().isFaseFinished()){
			
		}
		
	}

	private Fase getCurrentFase() {
		return fases.getFirst();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
