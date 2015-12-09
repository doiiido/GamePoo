package unb.cic.poo.game2d.waves.Fase2;

import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.ChasingYEnemy;
import unb.cic.poo.game2d.enemies.CommonEnemy;
import unb.cic.poo.game2d.enemies.ConstantXLaser;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.enemies.FreezedShootingEnemy;
import unb.cic.poo.game2d.enemies.VerticalMovementEnemy;
import unb.cic.poo.game2d.enemies.VerticalMovementLaser;
import unb.cic.poo.game2d.waves.Wave;

public class Wave5 extends Wave{
	
	double separacao = 0.0;
	double espacoEntreLinhas = 0;
	double espacoInicial = 100;
	
	public Wave5(){
		super();
		this.enemies = new ArrayList<Enemy>();

		
		//Primeira linha cima
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		
		//Primeira linha baixo
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		
		resetSeparacao();
		
		
		//Segunda linha cima
		aumentaEspacoEntreLinhas();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		
		//Segunda linha baixo
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		
		resetSeparacao();
		
		
		//Terceira Wave cima
		aumentaEspacoEntreLinhas();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		
		//TErceira wave baixo
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		
		resetSeparacao();
		
		
		 
		//Quarta linha cima
		aumentaEspacoEntreLinhas();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		
		
		//Quarta linha baixo
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		
		resetSeparacao();
		
		
		this.enemies.add(new ConstantXLaser(GameActivity.CAMERA_WIDTH*0.4f, GameActivity.CAMERA_HEIGHT+100f, ConstantXLaser.goUpOrDown.goUp, 1/3f));
		this.enemies.add(new VerticalMovementEnemy(GameActivity.CAMERA_WIDTH+300f, GameActivity.CAMERA_HEIGHT*1f, GameActivity.CAMERA_WIDTH*0.95f, 0.5f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+300f, GameActivity.CAMERA_HEIGHT*0.7f, GameActivity.CAMERA_WIDTH*0.9f));
//		this.enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+200f, GameActivity.CAMERA_HEIGHT*0.8f, GameActivity.CAMERA_WIDTH*0.7f, 350f));
//		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+100f, GameActivity.CAMERA_HEIGHT*0.3f, GameActivity.CAMERA_WIDTH*0.1f));
//		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+100f, GameActivity.CAMERA_HEIGHT*0.7f, GameActivity.CAMERA_WIDTH*0.1f));
		
	}
	
	private void resetSeparacao() {
		this.separacao = 0.0;
		
	}

	public void aumentaSeparacao(){
		this.separacao += 0.05;
	}
	
	public void aumentaEspacoEntreLinhas(){
		this.espacoEntreLinhas += 50;
	}

}
