package unb.cic.poo.game2d.fases;

import unb.cic.poo.game2d.waves.fase1.*;
import unb.cic.poo.game2d.waves.fase1.WaveInicial;

public class Fase1 extends Fase{
	public Fase1(){
		this.waves.add(new WaveInicial());
		this.waves.add(new Wave2());
		this.waves.add(new Wave3());
		this.waves.add(new Wave4());
	}	
}
