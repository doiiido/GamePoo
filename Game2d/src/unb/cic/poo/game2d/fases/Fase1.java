package unb.cic.poo.game2d.fases;

import unb.cic.poo.game2d.waves.Wave1;
import unb.cic.poo.game2d.waves.Wave2;

public class Fase1 extends Fase{
	public Fase1(){
		this.waves.add(new Wave1());
		this.waves.add(new Wave2());
	}	
}
