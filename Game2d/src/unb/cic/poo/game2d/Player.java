package unb.cic.poo.game2d;

//Classe que vai armazenar as propriedades do player: Vida, Pontuação...
//Atualmente ela herda da classe Rectangle, mas assim que tivermos os 
//sprites podemos mudar. 

import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.primitive.Rectangle;

public class Player extends Rectangle{
	private final static int PLAYER_HEIGHT = 32;
	private final static int PLAYER_WIDTH = 32;
	private final static int DEFAULT_SPEED = 1500;
	
	private int life;
	private int score;
	private MoveByModifier lastMoveByModifier; // Armazena o ultimo modificador de movimento utilizado na classe.
	private int speed;
	private float targetX; // targetX e targetY armazenam as coordenadas para as quais a nave esta se movendo.
	private float targetY;
	
	//Método construtor, por enquanto esta apenas chamando o construtor da superclasse e configurando a variável de velocidade.
	public Player(){
		super(0, (GameActivity.CAMERA_HEIGHT/2) - (PLAYER_HEIGHT/2), 
				PLAYER_WIDTH, PLAYER_WIDTH, GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.speed = DEFAULT_SPEED;
	}

	// getters e setters.

	public int getLife() {
		return life;
	}


	public void setLife(int life) {
		this.life = life;
	}


	public int getScore() {
		return score;
	}


	public void incrementScore(int scoreIncrement) {
		this.score += scoreIncrement;
	}


	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}



	public float getTargetY() {
		return targetY;
	}



	public void setTargetY(float targetY) {
		this.targetY = targetY;
	}



	public float getTargetX() {
		return targetX;
	}



	public void setTargetX(float targetX) {
		this.targetX = targetX;
	}



	public MoveByModifier getLastMoveByModifier() {
		return lastMoveByModifier;
	}



	public void setLastMoveByModifier(MoveByModifier lastMoveByModifier) {
		this.lastMoveByModifier = lastMoveByModifier;
	}
	

}