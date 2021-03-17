package apps.IrvinTheSenpai.MiStore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsername , loginPassword;
    private Button loginButton;
    private DataBaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginUsername = findViewById(R.id.loginusername);
        loginPassword = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.loginbutton);
        myDb = new DataBaseHelper(this);

        loginUser();

    }
    private void loginUser(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean var = myDb.checkUser(loginUsername.getText().toString() , loginPassword.getText().toString());
                if (var){
                    Toast.makeText(LoginActivity.this, "Inicio de sesión correctamente", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    loginUsername.setText("");
                    loginPassword.setText("");

                }else{
                    Toast.makeText(LoginActivity.this, "Error de inicio de sesion !!", Toast.LENGTH_SHORT).show();



                }
            }
        });
    }
}