package unb.cic.poo.game2d.fases;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.scenes.GameScene;
import unb.cic.poo.game2d.waves.fase1.*;

public class Fase1 extends Fase{
	public Fase1(){
		this.waves.add(new WaveInicial());
		this.waves.add(new Wave2());
		this.waves.add(new Wave3());
		this.waves.add(new Wave4());
		this.waves.add(new Wave5());
		this.waves.add(new Wave6());
		this.waves.add(new Wave7());
		this.waves.add(new Wave8());
	}	
	
	@Override
	public void onFaseStart() {
		((GameScene)GameManager.getInstance().getGameScene()).showTransition("Fase 1");
	}

}
