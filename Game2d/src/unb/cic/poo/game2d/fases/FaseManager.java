package unb.cic.poo.game2d.fases;

import java.util.LinkedList;

import org.andengine.engine.handler.IUpdateHandler;

import unb.cic.poo.game2d.FasesGetter;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.scenes.BaseScene;
import unb.cic.poo.game2d.scenes.GameScene;
import unb.cic.poo.game2d.scenes.SceneManager;

public class FaseManager implements IUpdateHandler{
	private LinkedList<Fase> fases;
	private int startingFase;
	private int currentFaseIndex;
	
	public FaseManager(LinkedList<Fase> fases){
		startingFase = GameManager.getInstance().getSaveHandler().getCurrentFase();
		currentFaseIndex = startingFase;
		this.fases = fases;
	}
	
	public FaseManager() {
		startingFase = GameManager.getInstance().getSaveHandler().getCurrentFase();
		currentFaseIndex = startingFase;
		fases = new FasesGetter().getFases();
	}
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		if(fases.isEmpty()){
			GameManager.getInstance().getSaveHandler().setUnlockedFases(GameManager.getInstance().getSaveHandler().getCurrentFase()-1);
			GameManager.getInstance().getSaveHandler().setCurrentFase(1);
			BaseScene aux = SceneManager.gameScene;
			((GameScene) aux).gameOver(true);
		}
		else if(getCurrentFase().isFaseFinished()){
			Fase fase = nextFase();
			if(fase != null){
				fase.onFaseStart();
				//fase.start();
			}
		}
		
	}

	private Fase nextFase() {
		GameManager.getInstance().getSaveHandler().setFaseScore(currentFaseIndex - 1, getCurrentFase().getFaseScore());
		GameManager.getInstance().getSaveHandler().nextFase();
		this.fases.pop();
		currentFaseIndex++;
		return fases.isEmpty() ? null : fases.getFirst();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public Fase getCurrentFase() {
		return fases.isEmpty() ? null : fases.getFirst();
	}
	
	public void start(){
		int i = 1;
		while(i != startingFase){
			fases.pop();
			i++;
		}
		//fases.getFirst().start();
		GameManager.getInstance().getGameScene().registerUpdateHandler(this);
		fases.getFirst().onFaseStart();
	}
	

	public LinkedList<Fase> getFases() {
		return fases;
	}

	public void setFases(LinkedList<Fase> fases) {
		this.fases = fases;
	}
	public int getStartingFase() {
		return startingFase;
	}
	public void setStartingFase(int startingFase) {
		this.startingFase = startingFase;
	}

}
