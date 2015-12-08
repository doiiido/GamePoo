package unb.cic.poo.game2d;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import unb.cic.poo.game2d.enemies.ChasingYEnemy;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.fases.FaseManager;

//Essa classe sera responsavel por gerenciar os objetos do jogo. Usando o padrao de projeto Singleton, dessa forma,
//poderemos acessar os componentes do gerenciador em qualquer classe sem a necessidade de passarmos os objetos como 
//referencia

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
	private SaveHandler saveHandler;
	GameActivity activity;
	
	public FaseManager getFaseManager() {
		return faseManager;
	}

	public void setFaseManager(FaseManager faseManager) {
		this.faseManager = faseManager;
	}
	
	private GameManager(){
		saveHandler = new SaveHandler();
		saveHandler.init(ResourceManager.getInstance().activity);
		saveHandler.getUnlockedFases();
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

	public SaveHandler getSaveHandler() {
		return saveHandler;
	}

	public void setSaveHandler(SaveHandler saveHandler) {
		this.saveHandler = saveHandler;
	}
}
