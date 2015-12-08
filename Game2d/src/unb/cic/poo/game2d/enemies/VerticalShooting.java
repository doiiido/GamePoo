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
import unb.cic.poo.game2d.enemies.ConstantXLaser.goUpOrDown;
import unb.cic.poo.game2d.items.ItemGen;

public class VerticalShooting extends Enemy{

	private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	//private static final int COMMON_ENEMY_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	
	private static final int DEFAULT_COMMON_VENEMY_SPEED = 500;/* Velocidade vertical alterada. */
	
	private static final int COMMON_ENEMY_LIFE = 1;
	private BulletType bulletType = new LaserBulletType(this);
	private float timer;
	private float posXinicial;
	private float posYinicial;
	private float tamanhoMovimento;
	private IUpdateHandler shootHandler;
	
	private float rotacao;
	
	/**
	 * 
	 * @param pX deve estar dentro da Tela!
	 * @param pY Caso o inimigo se movimente para cima, ele deve ser criado abaixo da tela, numa posição
	 * 		maior do que GameActivity.CAMERA_HEIGHT. Caso ele se movimento para baixo, a posição Y inicial
	 * 		deve ser menor que 0.
	 * @param rotacao define a rotação do inimigo.
	 */
	
	public VerticalShooting(float pX, float pY, float tamanhoMovimento, float rotacao) {
		super(pX, pY, ResourceManager.laserTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.posXinicial = pX;
		this.posYinicial = pY;
		this.tamanhoMovimento = tamanhoMovimento;
		this.rotacao = rotacao;
		
		this.setRotation(rotacao);
		
		this.setMovement();
		
		this.shootHandler = new IUpdateHandler(){
			
			/* O inimigo atira de 1 em 1 segundo. */
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;					
						
				if(timer <= 0){
					shoot();
					timer = 1f;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
		};
		this.registerUpdateHandler(shootHandler);
		
	}
	
	
	public VerticalShooting(float pX, float pY, float tamanhoMovimento, float rotacao, boolean dropsIten){ 
		super(pX, pY, ResourceManager.laserTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager(), dropsIten);
		
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.posXinicial = pX;
		this.posYinicial = pY;
		this.tamanhoMovimento = tamanhoMovimento;
		this.rotacao = rotacao;
		
		this.setRotation(rotacao);
		
		this.setMovement();
		
		this.shootHandler = new IUpdateHandler(){
			
			/* O inimigo atira de 1 em 1 segundo. */
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;					
						
				if(timer <= 0){
					shoot();
					timer = 1f;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
		};
		this.registerUpdateHandler(shootHandler);
		
		
	}
	
	public VerticalShooting(float pX, float pY, float tamanhoMovimento, float rotacao, ItemGen itenDropped ){ 
		super(pX, pY, ResourceManager.laserTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager(), itenDropped);
		
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.posXinicial = pX;
		this.posYinicial = pY;
		this.tamanhoMovimento = tamanhoMovimento;
		this.rotacao = rotacao;
		
		this.setRotation(rotacao);
		
		this.setMovement();
		
		this.shootHandler = new IUpdateHandler(){
			
			/* O inimigo atira de 1 em 1 segundo. */
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;					
						
				if(timer <= 0){
					shoot();
					timer = 1f;
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
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, tamanhoMovimento - posXinicial, 0);
		MoveByModifier right = new MoveByModifier(durationTime*3, tamanhoMovimento, 0);
		MoveByModifier left = new MoveByModifier(durationTime*3, -tamanhoMovimento, 0);
		
		SequenceEntityModifier sequenceHorizontal = new SequenceEntityModifier(left, right);	
		
		LoopEntityModifier loopHorizontal = new LoopEntityModifier(sequenceHorizontal);	
		
		
		SequenceEntityModifier posicionaLoop = new SequenceEntityModifier(moveByModifier, loopHorizontal);		
		
		
		this.registerEntityModifier(posicionaLoop);
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
	

}
