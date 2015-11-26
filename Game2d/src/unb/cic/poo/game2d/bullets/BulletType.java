package unb.cic.poo.game2d.bullets;

import org.andengine.opengl.texture.region.ITiledTextureRegion;

//Interface dos tipos de criadores de bala.

public abstract class BulletType {
	protected float cooldown = 0.1f;
	protected boolean onCooldown = false;
	protected ITiledTextureRegion swt;
	
	public abstract void setBullet(float pX, float pY, boolean isEnemy);
	
	public float getCooldownTime() {
		return cooldown;
	}
	
	public boolean isOnCooldown() {
		return onCooldown;
	}
	
	public void setOnCooldown(boolean onCooldown) {
		this.onCooldown = onCooldown;
	}

	public ITiledTextureRegion getSwt() {
		return swt;
	}
}
