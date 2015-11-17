package unb.cic.poo.game2d.fases;

import java.util.LinkedList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.scenes.BaseScene;
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
		if(fases.isEmpty()){
			BaseScene aux = SceneManager.gameScene;
			((GameScene) aux).gameOver(false);
		}
		else if(getCurrentFase().isFaseFinished()){
			Fase fase = nextFase();
			if(fase != null){
				fase.start();
			}
		}
		
	}

	private Fase nextFase() {
		fases.removeFirst();
		return fases.isEmpty() ? null : fases.getFirst();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	private Fase getCurrentFase() {
		return fases.isEmpty() ? null : fases.getFirst();
	}
	
	public void start(){
		fases.getFirst().start();
		GameManager.getInstance().getGameScene().registerUpdateHandler(this);
	}
}
