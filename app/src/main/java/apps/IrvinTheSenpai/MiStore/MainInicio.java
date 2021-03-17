package apps.IrvinTheSenpai.MiStore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainInicio extends AppCompatActivity {

    private Button accesoButton , inscribirseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio);

        accesoButton = findViewById(R.id.ingresobtn);
        inscribirseButton = findViewById(R.id.inscribirsebtn);

        accesoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainInicio.this , LoginActivity.class));
            }
        });

        inscribirseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainInicio.this , SignUpActivity.class));
            }
        });

    }
}