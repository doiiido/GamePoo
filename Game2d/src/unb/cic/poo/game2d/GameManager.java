package unb.cic.poo.game2d;

import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;
import unb.cic.poo.game2d.enemies.ChasingYEnemy;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.fases.FaseManager;

//Essa classe será responsável por gerenciar os objetos do jogo. Usando o padrão de projeto Singleton, dessa forma,
//poderemos acessar os componentes do gerenciador em qualquer classe sem a necessidade de passarmos os objetos como 
//referência

public class GameManager {
	private static GameManager gameManager;
	public static final int DX = -30; // deslocamento da nave em relacao ao toque
	public static final int DY = -30; // ver valores melhores
	private Engine gameEngine;
	private Player player;
	private Camera gameCamera;
	private Scene gameScene;
	private ArrayList<Enemy> WaveEnemies; //Armazena os inimigos da Wave.
	private FaseManager faseManager;
	
	public FaseManager getFaseManager() {
		return faseManager;
	}

	public void setFaseManager(FaseManager faseManager) {
		this.faseManager = faseManager;
	}

	//Esse método será usado para obter o único objeto que será instanciado da classe GameManager.
	public static GameManager getInstance(){
		if(gameManager == null){
			gameManager = new GameManager();
			return gameManager;
		}
		return gameManager;
	}

	//getters e setters
	
	public Engine getGameEngine() {
		return gameEngine;
	}

	public void setGameEngine(Engine gameEngine) {
		this.gameEngine = gameEngine;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	public Camera getGameCamera() {
		return gameCamera;
	}


	public void setGameCamera(Camera gameCamera) {
		this.gameCamera = gameCamera;
	}


	public Scene getGameScene() {
		return gameScene;
	}

	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}
	
	public ArrayList<Enemy> getEnemies() {
		return WaveEnemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.WaveEnemies = enemies;
	}
}
