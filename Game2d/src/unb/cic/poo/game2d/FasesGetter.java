package unb.cic.poo.game2d;

import java.util.LinkedList;

import unb.cic.poo.game2d.fases.Fase;

public class FasesGetter {
	
	public static FasesGetter fasesGetter;
	private LinkedList<Fase> fases;
	
	private FasesGetter(){
		
	}
	
	public LinkedList<Fase> getFases(){
		return (LinkedList<Fase>)fases.subList(0, fases.size());
	}
	
	
}
