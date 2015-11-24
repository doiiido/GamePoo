package unb.cic.poo.game2d.parse;

import com.example.game2d.R;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import unb.cic.poo.game2d.GameActivity;

public class ScoreTableActivity extends Activity {
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	Button logout;
	Button back;
	
	//---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Determina o xml
		setContentView(R.layout.welcome);
 
		// Retorna a conta do usuário do Parse
		ParseUser currentUser = ParseUser.getCurrentUser();
		String struser = currentUser.getUsername().toString();
 
		// Localiza as caixas de texto
		TextView txtuser = (TextView) findViewById(R.id.txtuser);
		txtuser.setText("You are logged in as " + struser);
 
		// Localiza os botões
		logout = (Button) findViewById(R.id.logout);
		back = (Button) findViewById(R.id.back);
 
		// Logout Button Click Listener
		logout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				ParseUser.logOut();
				Intent intent = new Intent(ScoreTableActivity.this,	LoginSignupActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		// Back Button Click Listener
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(ScoreTableActivity.this,	GameActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(ScoreTableActivity.this,	GameActivity.class);
		startActivity(intent);
		finish();
	}
}
