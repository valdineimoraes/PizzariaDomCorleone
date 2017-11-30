package valdinei.domcorleone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Model.DAO.UsuarioDAO;
import bd.CriaBanco;

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
        editTextUsuario = (EditText) findViewById(R.id.editTextUserLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextPasswordLogin);
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
                System.exit(0);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UsuarioDAO dao = new UsuarioDAO(LoginActivity.this);

                if (editTextUsuario.getText().length() == 0 || editTextSenha.getText().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Erro ao entrar no sistema");
                    builder.setMessage("O campo usuario ou senha nao podem estar vazio");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(LoginActivity.this, "Acesso negado " + arg1, Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }else{
                    if (dao.checkLogin(editTextUsuario.getText().toString(), editTextSenha.getText().toString())){
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("Conectado no sistema");
                        builder.setMessage(editTextUsuario.getText()+" você está conectado no sistema");
                        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent telaAdm = new Intent(LoginActivity.this, ADMPrincipalActivity.class);
                                startActivity(telaAdm);
                            }
                        });
                        builder.show();


                    }else{
                        Snackbar.make(view, "Usuario ou senha invalida!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        Intent telaLogin = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(telaLogin);
                    }


                }

            }
        });
    }
}
