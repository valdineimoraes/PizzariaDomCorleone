package valdinei.domcorleone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.DAO.UsuarioDAO;
import Model.Usuario;

public class UsuariosActivity extends AppCompatActivity {


    EditText editTextCodigo, editTextNomeCompleto, editTextUsuario, editTextSenha, editTextCargo, editTextTelefone;
    Button btnSalvarUser, btnVoltarUser, btnExcluirUser, btnEditarUser;
    ListView listUser;
    ArrayList<Usuario> usuarios;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        //editTextCodigo = (EditText) findViewById(R.id.txtCodigoUser);
        editTextNomeCompleto = (EditText) findViewById(R.id.txtNomeCompletoUser);
        editTextUsuario = (EditText) findViewById(R.id.txtUsuarioUser);
        editTextSenha = (EditText) findViewById(R.id.txtPasswordUser);
        editTextCargo = (EditText) findViewById(R.id.txtCargoUser);
        editTextTelefone = (EditText) findViewById(R.id.txtPhoneUser);

        btnExcluirUser = (Button) findViewById(R.id.btnExcluirUser);
        btnVoltarUser = (Button) findViewById(R.id.btnVoltarUser);
        btnSalvarUser = (Button) findViewById(R.id.btnSalvarUser);
        btnEditarUser = (Button) findViewById(R.id.btnEditarUser);

        listUser = (ListView) findViewById(R.id.listViewUsuarios);

        btnVoltarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSalvarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setNome(editTextNomeCompleto.getText().toString());
                usuario.setUsuario(editTextUsuario.getText().toString());
                usuario.setSenha(editTextSenha.getText().toString());
                usuario.setTelefone(editTextTelefone.getText().toString());

                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                if (editTextNomeCompleto.length() == 0 || editTextUsuario.length() == 0 || editTextSenha.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Erro ao cadastrar usuario");
                    builder.setMessage("O campo nome ou usuario ou senha nao podem estar vazio");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(UsuariosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else if (usuarioDAO.insertUsuario(usuario)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Cadastro de Usuario");
                    builder.setMessage("Cadastro realizado com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();

                    limparCampos();
                    usuario = null;

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Erro ao cadastrar usuario");
                    builder.setMessage("Nao foi possivel cadastrar usuario");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(UsuariosActivity.this, "Cadastro Negado" , Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }
        });

        btnEditarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usuario==null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setMessage("Nenhum usuario selecionado.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(UsuariosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }else {

                    if (editTextNomeCompleto.length() == 0 || editTextUsuario.length() == 0 || editTextSenha.length() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                        builder.setTitle("Erro ao atualizar o usuario");
                        builder.setMessage("Nenhum usuario selecionado para atualizar, os campos nao podem estar vazios.");
                        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(UsuariosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    } else{

                        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                        usuario.setNome(editTextNomeCompleto.getText().toString());
                        usuario.setUsuario(editTextUsuario.getText().toString());
                        usuario.setSenha(editTextSenha.getText().toString());
                        usuario.setTelefone(editTextTelefone.getText().toString());

                        usuarioDAO.updateUsuario(usuario);

                        AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                        builder.setTitle("Atualização do Usuario");
                        builder.setMessage("Cadastro Atualizado com sucesso");
                        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        builder.show();

                    }
                }

            }
        });

        btnExcluirUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                if (usuario==null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Erro ao excluir o usuario");
                    builder.setMessage("Nenhum usuario selecionado para excluir, os campos nao podem estar vazios.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(UsuariosActivity.this, "Exclusao Negada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }else {
                    usuarioDAO.deleteUsuario(usuario);
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Exclusão do Usuario");
                    builder.setMessage("Cadastro excluido com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();
                    usuario = null;
                    limparCampos();


                }
            }
        });

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        usuarios = usuarioDAO.getUsuarios();

        listUser.setAdapter(
                new ListAdapterUsuario(this, usuarios)
        );

        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                populaCampos(i);
            }
        });
    }

    public void populaCampos(int i){
        usuario = usuarios.get(i);

        editTextNomeCompleto.setText(usuario.getNome());
        editTextUsuario.setText(usuario.getUsuario());
        editTextSenha.setText(usuario.getSenha());
        editTextCargo.setText(usuario.getCargo());
        editTextTelefone.setText(usuario.getTelefone());
    }

    public void limparCampos(){

        editTextNomeCompleto.setText("");
        editTextUsuario.setText("");
        editTextSenha.setText("");
        editTextTelefone.setText("");
        editTextCargo.setText("");

    }

}
