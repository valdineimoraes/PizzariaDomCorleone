package valdinei.domcorleone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button btnLogar, btnCancelarLogin;
    EditText editTextSenha;
    EditText editTextUsuario;
    TextView editTextCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogar = (Button) findViewById(R.id.buttonLogin);
        btnCancelarLogin = (Button) findViewById(R.id.buttonCancelarLogin);
        editTextUsuario = (EditText) findViewById(R.id.editTextUser);
        editTextSenha = (EditText) findViewById(R.id.editTextPassword);
        editTextCadastrar = (TextView) findViewById(R.id.textViewCriar);

        editTextCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreCadastrar = new Intent(LoginActivity.this, CadastroLoginActivity.class);
                startActivity(abreCadastrar);
            }
        });

        btnCancelarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreVoltarTelaMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(abreVoltarTelaMain);
            }
        });
    }
}
