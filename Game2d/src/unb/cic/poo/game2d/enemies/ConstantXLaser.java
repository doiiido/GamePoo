package unb.cic.poo.game2d.enemies;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.bullets.BulletType;
import unb.cic.poo.game2d.bullets.LaserBulletType;

public class ConstantXLaser extends Enemy{

	private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	//private static final int COMMON_ENEMY_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	
	private static final int DEFAULT_COMMON_VENEMY_SPEED = 500;/* Velocidade vertical alterada. */
	
	private static final int COMMON_ENEMY_LIFE = 1;
	private BulletType bulletType = new LaserBulletType();
	private float timer;
	private float posXinicial;
	private float posYinicial;
	private float posXfinal;
	private IUpdateHandler shootHandler;
	
	public enum goUpOrDown{
		goUp, goDown;
	};
	
	private goUpOrDown movimento;
	
	public ConstantXLaser(float pX, float pY, float pXfinal, ConstantXLaser.goUpOrDown movimento) {
		super(pX, pY, ResourceManager.laserTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.posXfinal = pXfinal;
		this.posXinicial = pX;
		this.posYinicial = pY;
		this.movimento = movimento;
		
		this.setMovement();
		
		this.shootHandler = new IUpdateHandler(){
			
			/* O inimigo atira de 1 em 1 segundo. */
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;
				if(timer <= 0 && getX() == posXfinal && (getY() == 0 || getY() == GameActivity.CAMERA_HEIGHT)){
					shoot();
					timer = 0.001f;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
		};
		this.registerUpdateHandler(shootHandler);
		
	}
	
	/* Método que posiciona a nave inimiga na posição X final. */
	public void setMovement(){
		float distance = GameManager.getInstance().getGameCamera().getWidth();
		float durationTime = distance/this.speed;
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, -(this.posXinicial-this.posXfinal), 0);
		MoveByModifier down = new MoveByModifier(durationTime*3, 0, GameActivity.CAMERA_HEIGHT - COMMON_ENEMY_HEIGHT);
		MoveByModifier up = new MoveByModifier(durationTime*3, 0, -(GameActivity.CAMERA_HEIGHT - COMMON_ENEMY_HEIGHT));
		
		MoveByModifier posicionar;
		SequenceEntityModifier sequenceVertical;
		
		if(getMovimento().equals(ConstantXLaser.goUpOrDown.goUp)){
			posicionar = new MoveByModifier(durationTime, 0, GameActivity.CAMERA_HEIGHT-this.posYinicial);
			sequenceVertical = new SequenceEntityModifier(up, down);
		}			
		else{
			posicionar = new MoveByModifier(durationTime, 0, -this.posYinicial);
			sequenceVertical = new SequenceEntityModifier(down, up);
		}		
		
		LoopEntityModifier loopVertical = new LoopEntityModifier(sequenceVertical);	
		
		
		SequenceEntityModifier posicionaLoop = new SequenceEntityModifier(posicionar, loopVertical);		
		
		ParallelEntityModifier parallelEntityModifier = new ParallelEntityModifier(moveByModifier, posicionaLoop);
		
		this.registerEntityModifier(parallelEntityModifier);
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
	}

	@Override
	public void shoot() {
		this.bulletType.setBullet(this.getX(), this.getY()+(this.getHeight()/2), true);
	}

	@Override
	public void removeEnemy() {
		this.unregisterUpdateHandler(shootHandler);
		GameManager.getInstance().getEnemies().remove(this);
		GameManager.getInstance().getGameScene().detachChild(this);		
	}

	@Override
	public void handleTouchEvent(TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		
	}
	
	public goUpOrDown getMovimento(){
		return movimento;
	}

}
