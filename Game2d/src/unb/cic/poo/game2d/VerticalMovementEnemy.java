package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.Path;
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
	private float pontosY[] = new float[4];
	private float pontosX[] = new float[4];;
	private float timer;
	private float posXfinal;
	private float posXinicial;
	private IUpdateHandler shootHandler;
	
	public VerticalMovementEnemy(float pX, float pY, float posXfinal) {
		super(pX, pY, ResourceManager.shooterTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.posXinicial = pX;
		this.posXfinal = posXfinal;
		
		this.pontosY[0] = pY;
		this.pontosY[1] = 0f;
		this.pontosY[2] = (float)(22*COMMON_ENEMY_HEIGHT-COMMON_ENEMY_HEIGHT);
		this.pontosY[3] = (pY);
		this.pontosX[0] = this.posXfinal;
		this.pontosX[1] = this.posXfinal;
		this.pontosX[2] = this.posXfinal;
		this.pontosX[3] = this.posXfinal;
		
		
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
	private void setMovement(){
		float distance = GameManager.getInstance().getGameCamera().getWidth();
		float durationTime = distance/this.speed;
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, -(this.posXinicial-this.posXfinal), 0);
		moveByModifier.addModifierListener(this);
		
		moveByModifier.setAutoUnregisterWhenFinished(true);
		this.registerEntityModifier(moveByModifier);
	}

	/* Método que define o movimento vertical da nave inimiga. */
	private void setMovementV() {
		float distance = GameManager.getInstance().getGameCamera().getHeight();
		float durationTime = distance/this.speed;
		
		Path path = new Path(4);
		for(int i = 0; i < 4; ++i){
			float posX = this.pontosX[i];
			float poxY = this.pontosY[i];
			
			path.to(posX, poxY);
		}
		
		PathModifier pathModifier = new PathModifier(durationTime, path);
		LoopEntityModifier loopEntityModifier = new LoopEntityModifier(pathModifier);
		this.registerEntityModifier(loopEntityModifier);
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		this.setMovementV();
	}

	@Override
	public void shoot() {
		Bullet bullet = this.bulletType.getBullet(this.getX()+this.getWidth(), this.getY()+(this.getHeight()/2), true);
		GameManager.getInstance().getGameScene().attachChild(bullet);
	}

	@Override
	public void removeEnemy() {
		this.unregisterUpdateHandler(shootHandler);
		GameManager.getInstance().getEnemies().remove(this);
		GameManager.getInstance().getGameScene().detachChild(this);		
	}

}
