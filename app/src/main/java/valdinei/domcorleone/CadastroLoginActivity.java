package valdinei.domcorleone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroLoginActivity extends AppCompatActivity {

    Button btnCadastrar, btnCancelar;
    TextView txtTitle;
    EditText editTextNomeCompleto, editTextUsuario, editTextSenha, editTextCargo, editTextTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);

        txtTitle = (TextView) findViewById(R.id.textViewCadastroTitle);
        editTextNomeCompleto = (EditText) findViewById(R.id.editTextCadastroNomeCompleto);
        editTextUsuario = (EditText) findViewById(R.id.editTextCadastroUsuario);
        editTextSenha = (EditText) findViewById(R.id.editTextCadastroSenha);
        editTextCargo = (EditText)findViewById(R.id.editTextCadastroCargo);
        editTextTelefone = (EditText) findViewById(R.id.editTextCadastroTelefone);
        btnCadastrar = (Button) findViewById(R.id.buttonCadastrarUsuario);
        btnCancelar = (Button) findViewById(R.id.buttonCancelarCadastro);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
