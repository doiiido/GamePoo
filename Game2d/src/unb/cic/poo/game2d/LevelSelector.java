package unb.cic.poo.game2d;

import java.util.ArrayList;
import java.util.LinkedList;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import unb.cic.poo.game2d.scenes.SceneManager;

public class LevelSelector extends Entity{
	/* Level selector layer properties */
    private final int COLUMNS = 4;
    private final int ROWS = 2;

    /* Level selector tile properties */
    private final int TILE_DIMENSION = 150;
    private final int TILE_PADDING = 60;

    private final Scene mScene;
    private final org.andengine.engine.Engine mEngine;

    /* Variable containing the current max level and each level stars unlocked */
    private final int mMaxLevel;
    private final ArrayList<Integer> mNumStars;

    /* Camera width and height are needed for the layout */
    private final int mCameraWidth;
    private final int mCameraHeight;

    /* Initial x/y coordinates used for tile positioning */
    private final float mInitialX;
    private final float mInitialY;

    /*
     * Variable which defines whether the LevelSelector is hidden or visible
     */
    private boolean mHidden = true;

    /**
     * The LevelSelector object can be used to display a grid of level tiles for
     * user selection.
     * 
     * @param pMaxLevel
     *            Current max unlocked level.
     * @param pChapter
     *            Chapter/world number of this particular LevelSelector.
     * @param pCameraWidth
     *            Camera object's width value.
     * @param pCameraHeight
     *            Camera object's height value.
     * @param pScene
     *            The Scene in which the LevelSelector will be displayed on.
     * @param pEngine
     *            AndEngine's mEngine object.
     */
    public LevelSelector(final int pMaxLevel, final ArrayList<Integer> pNumStars,
            final int pCameraWidth, final int pCameraHeight,
            final Scene pScene, final org.andengine.engine.Engine pEngine) {
        this.mScene = pScene;
        this.mEngine = pEngine;
        this.mMaxLevel = pMaxLevel;
        this.mNumStars = pNumStars;
        this.mCameraWidth = pCameraWidth;
        this.mCameraHeight = pCameraHeight;

        /*
         * Obtain the initial tile's X coordinate by subtracting half of the
         * entire level selector width including all tiles and padding from the
         * center of the Scene
         */
        final float halfLevelSelectorWidth = ((TILE_DIMENSION * COLUMNS) + TILE_PADDING    * (COLUMNS - 1)) * 0.5f; 
        this.mInitialX = (this.mCameraWidth * 0.5f) - halfLevelSelectorWidth -80;
        
        /* Same math as above applies to the Y coordinate */ 
        final float halfLevelSelectorHeight = ((TILE_DIMENSION * ROWS) + TILE_PADDING    * (ROWS - 1)) * 0.5f; 
        this.mInitialY = (this.mCameraHeight * 0.5f) + halfLevelSelectorHeight - 300;

    }

    /**
     * Create the level tiles with a customized ITextureRegion representation as
     * well as a customized Font.
     * 
     * @param pTextureRegion
     *            The ITextureRegion to supply each of the level tiles.
     * @param star
     *            The Font to be displayed by Text written on the tiles,
     *            specifying tile level number for example.
     */
    public void createTiles(final LinkedList<ITextureRegion> pTextureRegion,
            final ITiledTextureRegion star) {

        /* Temp coordinates for placing level tiles */
        float tempX = this.mInitialX + pTextureRegion.get(0).getWidth() * 0.5f;
        float tempY = this.mInitialY - pTextureRegion.get(0).getHeight() * 0.5f;

        /* Current level of the tile to be placed */
        int currentTileLevel = 1;

        /*
         * Loop through the Rows, adjusting tempY coordinate after each
         * iteration
         */
        for (int i = 0; i < ROWS; i++) {

            /*
             * Loop through the column positions, placing a LevelTile in each
             * column
             */
            for (int o = 0; o < COLUMNS; o++) {

                final boolean locked;

                /* Determine whether the current tile is locked or not */
                if (currentTileLevel <= mMaxLevel) {
                    locked = false;
                } else {
                    locked = true;
                }

                /* Create a level tile */
                LevelTile levelTile = new LevelTile(tempX, tempY, locked,
                        currentTileLevel, pTextureRegion.get(currentTileLevel-1) , star);
                
                /*
                 * Attach the level tile's text based on the locked and
                 * currentTileLevel variables pass to its constructor
                 */

                levelTile.attachStar();

                /* Register & Attach the levelTile object to the LevelSelector */
                mScene.registerTouchArea(levelTile);
                this.attachChild(levelTile);

                /* Increment the tempX coordinate to the next column */
                tempX = tempX + TILE_DIMENSION + TILE_PADDING;

                /* Increment the level tile count */
                currentTileLevel++;
            }

            /* Reposition the tempX coordinate back to the first row (far left) */
            tempX = mInitialX + TILE_DIMENSION * 0.5f;

            /* Reposition the tempY coordinate for the next row to apply tiles */
            tempY = tempY + TILE_DIMENSION + TILE_PADDING +50;
        }
    }

    /**
     * Display the LevelSelector on the Scene.
     */
    public void show() {

        /* Register as non-hidden, allowing touch events */
        mHidden = false;

        /* Attach the LevelSelector the the Scene if it currently has no parent */
        if (!this.hasParent()) {
            mScene.attachChild(this);
        }

        /* Set the LevelSelector to visible */
        this.setVisible(true);
    }

    /**
     * Hide the LevelSelector on the Scene.
     */
    public void hide() {

        /* Register as hidden, disallowing touch events */
        mHidden = true;

        /* Remove the LevelSelector from view */
        this.setVisible(false);
    }

    public class LevelTile extends Sprite {

        /*
         * The LevelTile should keep track of level number and lock status. Feel
         * free to add additional data within level tiles
         */
        private final boolean mIsLocked;
        private final int mLevelNumber;
        private final ITiledTextureRegion mStar;
        private Sprite StarSprite;

        /*
         * Each level tile will be sized according to the constant
         * TILE_DIMENSION within the LevelSelector class
         */
        public LevelTile(float pX, float pY, boolean pIsLocked,
                int pLevelNumber, ITextureRegion pTextureRegion, ITiledTextureRegion pStar) {
        	
            super(pX, pY, LevelSelector.this.TILE_DIMENSION,
                    LevelSelector.this.TILE_DIMENSION, pTextureRegion,
                    LevelSelector.this.mEngine.getVertexBufferObjectManager());
        	//super(pX, pY, pTextureRegion, LevelSelector.this.mEngine.getVertexBufferObjectManager());

            /* Initialize the necessary variables for the LevelTile */
            this.mStar = pStar;
            this.mIsLocked = pIsLocked;
            this.mLevelNumber = pLevelNumber;
            this.attachStar();
            
        }

        /* Method used to obtain whether or not this level tile represents a
         * level which is currently locked */
        public boolean isLocked() {
            return this.mIsLocked;
        }

        /* Method used to obtain this specific level tiles level number */
        public int getLevelNumber() {
            return this.mLevelNumber;
        }

        /*
         * Attach the LevelTile's text to itself based on whether it's locked or
         * not. If not, then the level number will be displayed on the level
         * tile.
         */
        public void attachStar() {

            /* Determine the tile's string based on whether it's locked or not*/
            if (this.mIsLocked) {
                mStar.setCurrentTileIndex(0);
                
            } else {
            	mStar.setCurrentTileIndex(mNumStars.get(mLevelNumber-1));
            	
            }

            /* Setup the text position to be placed in the center-bottom of the tile */
            final float starPositionX = LevelSelector.this.TILE_DIMENSION/6;
            final float starPositionY = LevelSelector.this.TILE_DIMENSION - 15;
          
            /* Attach the mStar to the LevelTile */
            StarSprite = new Sprite(0, 0, mStar, LevelSelector.this.mEngine.getVertexBufferObjectManager());
            this.attachChild(StarSprite);
            StarSprite.setPosition(starPositionX, starPositionY);
        }

        @Override
        public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
                float pTouchAreaLocalX, float pTouchAreaLocalY) {

            /* If the LevelSelector is not hidden, proceed to execute the touch event */
            if (!LevelSelector.this.mHidden) {

                /* If a level tile is initially pressed down on */
                if (pSceneTouchEvent.isActionDown()) {
                    /* If this level tile is locked... */
                    if (!this.mIsLocked) {
                        LevelSelector.this.hide();
                        //!!!!!!!!!!! Depois colocar para carregar cada fase específica 
                        SceneManager.getInstance().loadGameSceneFromSelector();
                    }
                    return true;
                }
            }
            return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
                    pTouchAreaLocalY);
        }
    }
}


