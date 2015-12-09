package unb.cic.poo.game2d.fases;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.scenes.GameScene;
import unb.cic.poo.game2d.waves.fase1.*;

public class Fase1 extends Fase{
	public Fase1(){
		this.waves.add(new WaveInicial());
		this.waves.add(new Wave2());
		
	}	
	
	@Override
	public void onFaseStart() {
		((GameScene)GameManager.getInstance().getGameScene()).showTransition("Fase 1");
		GameManager.getInstance().getGameScene().registerUpdateHandler(new TimerHandler(3f, new ITimerCallback()
	    {                                    
	        public void onTimePassed(final TimerHandler pTimerHandler)
	        {
	            pTimerHandler.reset();
	            GameManager.getInstance().getGameScene().unregisterUpdateHandler(pTimerHandler);
	            GameManager.getInstance().getFaseManager().getCurrentFase().start();
	    		((GameScene)GameManager.getInstance().getGameScene()).hideTransition();
	        }
	    }));
	}
	public void onFaseFinished() {
		((GameScene)GameManager.getInstance().getGameScene()).showTransition("Bom Trabalho!");
		GameManager.getInstance().getGameScene().registerUpdateHandler(new TimerHandler(3f, new ITimerCallback()
	    {                                    
	        public void onTimePassed(final TimerHandler pTimerHandler)
	        {
	            pTimerHandler.reset();
	            GameManager.getInstance().getGameScene().unregisterUpdateHandler(pTimerHandler);
	    		setFaseFinished(true);
	    		((GameScene)GameManager.getInstance().getGameScene()).hideTransition();
	        }
	    }));
	}

}
