package unb.cic.poo.game2d.fases;

import unb.cic.poo.game2d.waves.*;

public class Fase1 extends Fase{
	public Fase1(){
		this.waves.add(new Wave1());
		//this.waves.add(new Wave1());
		this.waves.add(new Wave2());
		//this.waves.add(new Wave2());
		this.waves.add(new Wave3());
		//this.waves.add(new Wave3());
	}	
}
