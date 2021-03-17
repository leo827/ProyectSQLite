package apps.IrvinTheSenpai.MiStore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailSignUp , usernameSignUp , passwordSignUp;
    private Button signUpButton;
    private DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailSignUp = findViewById(R.id.signupemail);
        usernameSignUp = findViewById(R.id.signupusername);
        passwordSignUp = findViewById(R.id.siguppassword);
        signUpButton = findViewById(R.id.signupbutton);

        myDB = new DataBaseHelper(this);
        insertUser();
    }


    private void insertUser(){

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAccount();
            }


            private void createAccount() {

                String Nombre = emailSignUp.getText().toString().trim();
                String ficha = usernameSignUp.getText().toString().trim();
                String email = passwordSignUp.getText().toString();

                //Verificamos que las cajas de texto no esten vacías y sean obligatorias
                if (TextUtils.isEmpty(Nombre)) {
                    emailSignUp.setError("Se debe ingresar un correo");
                    emailSignUp.requestFocus();
                    //it is better then Toast.
                    return;
                }

                if (TextUtils.isEmpty(ficha)) {
                    usernameSignUp.setError("Se debe ingresar nombre de usuario");
                    usernameSignUp.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    passwordSignUp.setError("Se debe ingresar una contraseña");
                    passwordSignUp.requestFocus();
                    return ;
                }

                boolean var = myDB.registerUser(usernameSignUp.getText().toString() , emailSignUp.getText().toString() , passwordSignUp.getText().toString());
                if(var) {
                    Toast.makeText(SignUpActivity.this, "Usuario registrado correctamente !!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    emailSignUp.setText("");
                    usernameSignUp.setText("");
                    passwordSignUp.setText("");

                }
                else
                    Toast.makeText(SignUpActivity.this, "error de registro !!", Toast.LENGTH_SHORT).show();

            }

        });

            }
        }


