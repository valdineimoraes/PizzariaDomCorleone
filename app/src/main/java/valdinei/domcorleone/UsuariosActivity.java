package valdinei.domcorleone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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


    EditText nomeCompletoUser, usuarioUser, senhaUser, cargoUser, telefoneUser;
    Button btnSalvarUser, btnVoltarUser, btnExcluirUser, btnEditarUser;
    ListView listUser;
    ArrayList<Usuario> usuarios;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        nomeCompletoUser = (EditText) findViewById(R.id.txtCadNomeCompletoUser);
        usuarioUser = (EditText) findViewById(R.id.txtCadUsuarioUser);
        senhaUser = (EditText) findViewById(R.id.txtCadPasswordUser);
        cargoUser = (EditText) findViewById(R.id.txtCadCargoUser);
        telefoneUser = (EditText) findViewById(R.id.txtCadPhoneUser);

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
                usuario.setNome(nomeCompletoUser.getText().toString());
                usuario.setUsuario(usuarioUser.getText().toString());
                usuario.setSenha(senhaUser.getText().toString());
                usuario.setTelefone(telefoneUser.getText().toString());

                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                if (nomeCompletoUser.length() == 0 || usuarioUser.length() == 0 || senhaUser.length() == 0) {
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
                    atualizaListaUsuarios();

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

                    if (nomeCompletoUser.length() == 0 || usuarioUser.length() == 0 || senhaUser.length() == 0) {
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

                        usuario.setNome(nomeCompletoUser.getText().toString());
                        usuario.setUsuario(usuarioUser.getText().toString());
                        usuario.setSenha(senhaUser.getText().toString());
                        usuario.setTelefone(telefoneUser.getText().toString());

                        usuarioDAO.updateUsuario(usuario);

                        AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                        builder.setTitle("Atualização do Usuario");
                        builder.setMessage("Cadastro Atualizado com sucesso");
                        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        builder.show();
                        limparCampos();
                        atualizaListaUsuarios();

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
                    atualizaListaUsuarios();


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

    public void atualizaListaUsuarios(){
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        usuarios = usuarioDAO.getUsuarios();

        listUser.setAdapter(
                new ListAdapterUsuario(this, usuarios)
        );
    }

    public void populaCampos(int i){
        usuario = usuarios.get(i);

        nomeCompletoUser.setText(usuario.getNome());
        usuarioUser.setText(usuario.getUsuario());
        senhaUser.setText(usuario.getSenha());
        cargoUser.setText(usuario.getCargo());
        telefoneUser.setText(usuario.getTelefone());
    }

    public void limparCampos(){

        nomeCompletoUser.setText("");
        usuarioUser.setText("");
        senhaUser.setText("");
        telefoneUser.setText("");
        cargoUser.setText("");

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.meu_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_alunos){
            Intent menuAluno = new Intent(UsuariosActivity.this, AlunosActivity.class);
            startActivity(menuAluno);
            return true;
        }

        if(id == R.id.menu_professores){
            Intent menuProfessores = new Intent(UsuariosActivity.this, ProfessorActivity.class);
            startActivity(menuProfessores);
            return true;
        }

        if(id == R.id.menu_turmas){
            Intent menuTurma = new Intent(UsuariosActivity.this, TurmaActivity.class);
            startActivity(menuTurma);
            return true;
        }

        if(id == R.id.menu_usuario){
            Intent menuUsuario = new Intent(UsuariosActivity.this, UsuariosActivity.class);
            startActivity(menuUsuario);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
