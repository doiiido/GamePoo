package unb.cic.poo.game2d.fases;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.scenes.GameScene;
import unb.cic.poo.game2d.waves.Fase2.*;

public class Fase2 extends Fase{	
		
	public Fase2(){
		this.waves.add(new Wave1());
		this.waves.add(new Wave1());
	}	
	
	@Override
	public void onFaseStart() {
		((GameScene)GameManager.getInstance().getGameScene()).showTransition("Fase 2");
	}
}
