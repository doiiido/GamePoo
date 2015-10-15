package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;



/* Ainda existe o problema que as naves continuam a atirar depois de removidas. */



public class VerticalMovementEnemy extends Enemy{
	private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	//private static final int COMMON_ENEMY_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	
	private static final int DEFAULT_COMMON_VENEMY_SPEED = 500;/* Velocidade vertical alterada. */
	
	private static final int COMMON_ENEMY_LIFE = 1;
	private BulletType bulletType = new CommonBulletType();
	private float timer;
	private float posXfinal;
	private float posXinicial;
	private float yInicial;
	private IUpdateHandler shootHandler;
	
	public VerticalMovementEnemy(float pX, float pY, float posXfinal) {
		super(pX, pY, ResourceManager.shooterTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.posXinicial = pX;
		this.yInicial = pY;
		this.posXfinal = posXfinal;		
		
		this.setMovement();
		
		this.shootHandler = new IUpdateHandler(){
			
			/* O inimigo atira de 1 em 1 segundo. */
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;
				if(timer <= 0){
					shoot();
					timer = 0.5f;
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
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, -(GameActivity.CAMERA_WIDTH-this.posXfinal), 0);
		
		MoveByModifier firstUp = new MoveByModifier(durationTime, 0, -this.yInicial);
		
		MoveByModifier down = new MoveByModifier(durationTime, 0, GameActivity.CAMERA_HEIGHT - COMMON_ENEMY_HEIGHT);
		MoveByModifier up = new MoveByModifier(durationTime, 0, -(GameActivity.CAMERA_HEIGHT - COMMON_ENEMY_HEIGHT));
		SequenceEntityModifier sequenceVertical = new SequenceEntityModifier(down, up);
		LoopEntityModifier loopVertical = new LoopEntityModifier(sequenceVertical);	
		
		
		SequenceEntityModifier posicionaLoop = new SequenceEntityModifier(firstUp, loopVertical);		
		
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
		Bullet bullet = this.bulletType.getBullet(this.getX(), this.getY()+(this.getHeight()/2), true);
		GameManager.getInstance().getGameScene().attachChild(bullet);
	}

	@Override
	public void removeEnemy() {
		this.unregisterUpdateHandler(shootHandler);
		GameManager.getInstance().getEnemies().remove(this);
		GameManager.getInstance().getGameScene().detachChild(this);		
	}

}
