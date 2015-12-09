package unb.cic.poo.game2d.waves.Fase2;

import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.CommonEnemy;
import unb.cic.poo.game2d.enemies.ConstantXLaser;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.enemies.VerticalMovementLaser;
import unb.cic.poo.game2d.items.DoubleBulletGen;
import unb.cic.poo.game2d.items.LaserBulletGen;
import unb.cic.poo.game2d.items.LifeItemGen;
import unb.cic.poo.game2d.items.MachineGunGen;
import unb.cic.poo.game2d.waves.Wave;

public class Wave4 extends Wave{
	
	double separacao = 0.0;
	double espacoEntreLinhas = 0;
	double espacoInicial = 100;
	
	public Wave4(){
		super();
		this.enemies = new ArrayList<Enemy>();

		
		//Primeira linha cima
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
		
		//Primeira linha baixo
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+220f+espacoEntreLinhas+espacoInicial), (float) (GameActivity.CAMERA_HEIGHT*separacao)));
		aumentaSeparacao();
		
		resetSeparacao();
		
		
		//Segunda linha cima
		aumentaEspacoEntreLinhas();
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
		
		//Segunda linha baixo
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
		
		resetSeparacao();
		
		
		//Terceira Wave cima
		aumentaEspacoEntreLinhas();
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
		
		//TErceira wave baixo
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
		
		resetSeparacao();
		
		
		 
		//Quarta linha cima
		aumentaEspacoEntreLinhas();
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
		
		
		//Quarta linha baixo
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220), (float) (GameActivity.CAMERA_HEIGHT*separacao), new LaserBulletGen()));
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
		this.enemies.add(new CommonEnemy((float)(GameActivity.CAMERA_WIDTH+espacoEntreLinhas+espacoInicial+220f), (float) (GameActivity.CAMERA_HEIGHT*separacao), new DoubleBulletGen()));
		aumentaSeparacao();
		
		resetSeparacao();
		
		this.enemies.add(new ConstantXLaser(GameActivity.CAMERA_WIDTH*0.4f, -100f, ConstantXLaser.goUpOrDown.goDown, 1/3f));
		this.enemies.add(new VerticalMovementLaser(GameActivity.CAMERA_WIDTH+200f, GameActivity.CAMERA_HEIGHT*0.9f, GameActivity.CAMERA_WIDTH*0.8f, 1.2f));
		
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
