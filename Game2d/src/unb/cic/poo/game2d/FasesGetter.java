package unb.cic.poo.game2d;

import java.util.LinkedList;

import unb.cic.poo.game2d.fases.Fase;
import unb.cic.poo.game2d.fases.Fase1;

public class FasesGetter {
	private LinkedList<Fase> fases;
	
	public LinkedList<Fase> getFases(){
		fases = new LinkedList<Fase>();
		fases.add(new Fase1());
		fases.add(new Fase1());
		
		return fases;
	}
	
	
}
