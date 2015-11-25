package unb.cic.poo.game2d.parse;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {
	
    private String APP_ID = "secl8Yatp0oi7oavClCrPZe9FcwOMz2PStz5lLfm";
    private String CLIENT_ID = "tNGlFADa71F5aOZf3lvVgtPeup9rMlLK4LsCi3OG";

	@Override
	public void onCreate() {
		super.onCreate();
	    //Adiciona o Banco de Dados para o High-Score
	  	Parse.enableLocalDatastore(this);
	  	Parse.initialize(this, APP_ID, CLIENT_ID);
	  	ParseObject.registerSubclass(HighScore.class);
	  	
	  	ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
 
        //Se quiser privacidade entre os usuários, remover essa linha
        defaultACL.setPublicReadAccess(true);
 
        ParseACL.setDefaultACL(defaultACL, true);
    }
	
}