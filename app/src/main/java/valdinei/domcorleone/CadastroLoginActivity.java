package valdinei.domcorleone;

import android.content.DialogInterface;
import android.content.Intent;
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
import Model.Usuario;

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

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pegando os valores
                String nome = editTextNomeCompleto.getText().toString();
                String usuario = editTextUsuario.getText().toString();
                String senha = editTextSenha.getText().toString();
                String cargo = editTextCargo.getText().toString();
                String telefone = editTextTelefone.getText().toString();

                //salvando os dados
                Usuario usuario1 = new Usuario();
                usuario1.setNome(editTextNomeCompleto.getText().toString());
                usuario1.setUsuario(editTextUsuario.getText().toString());
                usuario1.setSenha(editTextSenha.getText().toString());
                usuario1.setTelefone(editTextTelefone.getText().toString());

                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                if (nome.length() == 0 || usuario.length() == 0 || senha.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CadastroLoginActivity.this);
                    builder.setTitle("Erro ao entrar no sistema");
                    builder.setMessage("O campo nome ou usuario ou senha nao podem estar vazio");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(CadastroLoginActivity.this, "Cadastro Negado=" + arg1, Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                } else if (usuarioDAO.insertUsuario(usuario1)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(CadastroLoginActivity.this);
                    builder.setTitle("Cadastro de Usuario");
                    builder.setMessage("Cadastro realizado com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent telaLogin = new Intent(CadastroLoginActivity.this, LoginActivity.class);
                            startActivity(telaLogin);
                        }
                    });
                    builder.show();

                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(CadastroLoginActivity.this);
                    builder.setTitle("Erro ao cadastrar usuario");
                    builder.setMessage("Nao foi possivel cadastrar usuario");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(CadastroLoginActivity.this, "Cadastro Negado=" + arg1, Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }

            }
        });
    }
}
