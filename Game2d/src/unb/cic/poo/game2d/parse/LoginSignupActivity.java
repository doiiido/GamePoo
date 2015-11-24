package unb.cic.poo.game2d.parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import unb.cic.poo.game2d.GameActivity;

import com.example.game2d.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginSignupActivity extends Activity {
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	Button loginbutton;
	Button signup;
	Button back;
	String usernametxt;
	String passwordtxt;
	EditText password;
	EditText username;
	
    //---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Determina o xml
		setContentView(R.layout.loginsignup);
		// Localiza as caixas de texto
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		// Localiza os botões
		loginbutton = (Button) findViewById(R.id.login);
		signup = (Button) findViewById(R.id.signup);
		back = (Button) findViewById(R.id.back);

		// Login Button Click Listener
		loginbutton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// Retorna as informações contidas nas caixas de texto
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();

				// Envia os dados ao Parse
				ParseUser.logInInBackground(usernametxt, passwordtxt, new LogInCallback() {
					public void done(ParseUser user, ParseException e) {
						if (user != null) {
							// Se o usuário existe e é autenticado, envia ao ScoreTableActivity.class
							Intent intent = new Intent(LoginSignupActivity.this, ScoreTableActivity.class);
							startActivity(intent);
							Toast.makeText(getApplicationContext(), "Successfully Logged in.", Toast.LENGTH_LONG).show();
							finish();
						} else {
							Toast.makeText(getApplicationContext(),	"No such user exist, please signup.", Toast.LENGTH_LONG).show();
						}
					}
				});
			}
		});
		
		// Sign Up Button Click Listener
		signup.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginSignupActivity.this, SignUpActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		// Back Button Click Listener
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginSignupActivity.this, GameActivity.class);
				startActivity(intent);
				finish();
			}
		});

	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(LoginSignupActivity.this, GameActivity.class);
		startActivity(intent);
		finish();
	}
}
