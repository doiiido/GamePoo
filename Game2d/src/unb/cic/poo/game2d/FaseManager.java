package unb.cic.poo.game2d;

import java.util.LinkedList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;

import unb.cic.poo.game2d.scenes.GameScene;
import unb.cic.poo.game2d.scenes.SceneManager;

public class FaseManager extends Entity{
//	private LinkedList<Fase> fases;
//
//	public FaseManager(Fase firstFase){
//		fases = new LinkedList<Fase>();
//		fases.add(firstFase);
//		this.registerUpdateHandler(new IUpdateHandler() {
//			
//			@Override
//			public void reset() {
//			}
//			
//			@Override
//			public void onUpdate(float pSecondsElapsed) {
//				if(getFases().isEmpty()){
//					GameScene scene = (GameScene) SceneManager.gameScene;
//					scene.gameOver();
//				}
//				if(getCurrentFase().faseFinished()){
//					nextFase();
//				}
//			}
//		});
//	}
//	
//	public LinkedList<Fase> getFases() {
//		return fases;
//	}
//
//	public void setFases(LinkedList<Fase> fases) {
//		this.fases = fases;
//	}
//	public Fase getCurrentFase(){
//		return fases.get(0);
//	}
//	
//	public void nextFase(){
//		Fase nextFase;
//		
//		GameManager.getInstance().getGameEngine().unregisterUpdateHandler(fases.get(0).getUpdateHandler());
//		
//		fases.remove(0);
//		nextFase = fases.get(0);
//		nextFase.setFase();
//	}	
}
