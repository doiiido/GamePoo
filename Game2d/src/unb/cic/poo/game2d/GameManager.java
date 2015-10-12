package unb.cic.poo.game2d;

import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

//Essa classe será responsável por gerenciar os objetos do jogo. Usando o padrão de projeto Singleton, dessa forma,
//poderemos acessar os componentes do gerenciador em qualquer classe sem a necessidade de passarmos os objetos como 
//referência

public class GameManager implements IOnSceneTouchListener{
	private static GameManager gameManager;
	private Engine gameEngine;
	private Player player;
	private Camera gameCamera;
	private Scene gameScene;
	private ArrayList<Enemy> WaveEnemies; //Armazena os inimigos da Wave.
	
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

	//Esse método gerenciará o comportamento dos objetos ao se tocar na tela. 

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if(pScene == this.gameScene){
			if(pSceneTouchEvent.getX() <= this.gameCamera.getWidth()/2){
					//Update dos atributos do objeto Player.
				
					if(this.player.getLastMoveByModifier() != null){
						this.player.unregisterEntityModifier(this.player.getLastMoveByModifier());
					}
					this.player.setTargetX(pSceneTouchEvent.getX());
					this.player.setTargetY(pSceneTouchEvent.getY());
					
				
					//	Calcula o tempo de duração de cada movimento com base na velocidade configurada no Player.
					//	Foi feita uma simplificação no cálculo da distância para evitar o uso de raiz quadrada.
					//	Além disso, para evitar calculos quando o valor float é muito pequeno, foi utilizado uma duração 
					//	fixa para quando as distâncias são muito curtas.
					
					float durationTime;
					float deltaX = pSceneTouchEvent.getX()-this.player.getX();
					float deltaY = pSceneTouchEvent.getY()-this.player.getY();
					float absDistance = Math.abs(deltaX) + Math.abs(deltaY);
					if(absDistance <= 0.5){
						durationTime = 0.0001f;
					}
					else{
						durationTime = (absDistance)/player.getSpeed();
					}
					
					// Utiliza um MoveByModifier para alterar o caminho seguido pela nave.
					MoveByModifier moveByModifier = new MoveByModifier(durationTime, deltaX, deltaY);
					this.player.setLastMoveByModifier(moveByModifier);
					this.player.registerEntityModifier(moveByModifier);
			}
			else if(pSceneTouchEvent.isActionUp()){
				this.player.shoot();
			}
		}
		return false;
	}
}
