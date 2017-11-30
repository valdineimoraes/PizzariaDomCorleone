package valdinei.domcorleone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.DAO.ProfessorDAO;

import Model.Professor;


public class ProfessorActivity extends AppCompatActivity {

    EditText editTextCodigoProf, nomeProf, telefoneProf, formacaoProf;
    Button btnSalvarProf, btnVoltarProf, btnExcluirProf, btnEditarProf;
    ListView listProf;
    ArrayList<Professor> professores;

    Professor professor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);


        //editTextCodigo = (EditText) findViewById(R.id.txtCodigoUser);
        nomeProf = (EditText) findViewById(R.id.txtCadNomeProf);
        telefoneProf = (EditText) findViewById(R.id.txtCadTelefoneProf);
        formacaoProf= (EditText) findViewById(R.id.txtCadFormacaoProf);

        btnExcluirProf = (Button) findViewById(R.id.btnExcluirProf);
        btnVoltarProf = (Button) findViewById(R.id.btnVoltarProf);
        btnSalvarProf = (Button) findViewById(R.id.btnSalvarProf);
        btnEditarProf = (Button) findViewById(R.id.btnEditarProf);

        listProf = (ListView) findViewById(R.id.listViewProf);

        btnVoltarProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSalvarProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                professor = new Professor();
                professor.setNome(nomeProf.getText().toString());
                professor.setTelefone(telefoneProf.getText().toString());
                professor.setFormacao(formacaoProf.getText().toString());

                ProfessorDAO professorDAO = new ProfessorDAO(getApplicationContext());

                if (nomeProf.length() == 0 || formacaoProf.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(valdinei.domcorleone.ProfessorActivity.this);
                    builder.setTitle("Erro ao cadastrar professor");
                    builder.setMessage("O campo nome ou formação ou senha nao podem estar vazio");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(valdinei.domcorleone.ProfessorActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                } else if (professorDAO.insertProfessor(professor)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(valdinei.domcorleone.ProfessorActivity.this);
                    builder.setTitle("Cadastro de professor");
                    builder.setMessage("Cadastro realizado com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();

                    limparCampos();
                    professor = null;
                    atualizaListaProfessores();

                    Intent serviceIntent = new Intent(ProfessorActivity.this, ServiceSample.class);
                    startService(serviceIntent);


                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(valdinei.domcorleone.ProfessorActivity.this);
                    builder.setTitle("Erro ao cadastrar professor");
                    builder.setMessage("Nao foi possivel cadastrar professor");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(valdinei.domcorleone.ProfessorActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }
        });

        btnEditarProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (professor == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(valdinei.domcorleone.ProfessorActivity.this);
                    builder.setMessage("Nenhum professor selecionado.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(professorsActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                } else {

                    if (nomeProf.length() == 0 || formacaoProf.length() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(valdinei.domcorleone.ProfessorActivity.this);
                        builder.setTitle("Erro ao atualizar o professor");
                        builder.setMessage("Nenhum professor selecionado para atualizar, os campos nao podem estar vazios.");
                        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(valdinei.domcorleone.ProfessorActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    } else {

                        ProfessorDAO professorDAO = new ProfessorDAO(getApplicationContext());

                        professor.setNome(nomeProf.getText().toString());
                        professor.setTelefone(telefoneProf.getText().toString());
                        professor.setFormacao(formacaoProf.getText().toString());

                        professorDAO.updateProfessor(professor);

                        AlertDialog.Builder builder = new AlertDialog.Builder(valdinei.domcorleone.ProfessorActivity.this);
                        builder.setTitle("Atualização do professor");
                        builder.setMessage("Cadastro Atualizado com sucesso");
                        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        builder.show();
                        atualizaListaProfessores();

                    }
                }

            }
        });

        btnExcluirProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProfessorDAO professorDAO = new ProfessorDAO(getApplicationContext());

                if (professor == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(valdinei.domcorleone.ProfessorActivity.this);
                    builder.setTitle("Erro ao excluir o professor");
                    builder.setMessage("Nenhum professor selecionado para excluir, os campos nao podem estar vazios.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(valdinei.domcorleone.ProfessorActivity.this, "Exclusao Negada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                } else {
                    professorDAO.deleteProfessor(professor);
                    AlertDialog.Builder builder = new AlertDialog.Builder(valdinei.domcorleone.ProfessorActivity.this);
                    builder.setTitle("Exclusão do professor");
                    builder.setMessage("Cadastro excluido com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();
                    professor = null;
                    limparCampos();
                    atualizaListaProfessores();


                }
            }
        });

        ProfessorDAO professorDAO = new ProfessorDAO(this);
        professores = professorDAO.getProfessores();

        listProf.setAdapter(
                new ListAdapterProfessor(this, professores)
        );

        listProf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                populaCampos(i);
            }
        });
    }

    public void atualizaListaProfessores(){
        ProfessorDAO professorDAO = new ProfessorDAO(this);
        professores = professorDAO.getProfessores();

        listProf.setAdapter(
                new ListAdapterProfessor(this, professores)
        );
    }

    public void populaCampos(int i){
        professor = professores.get(i);

        nomeProf.setText(professor.getNome());
        telefoneProf.setText(professor.getTelefone());
        formacaoProf.setText(professor.getFormacao());
    }

    public void limparCampos(){

        nomeProf.setText("");
        telefoneProf.setText("");
        formacaoProf.setText("");

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.meu_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_alunos){
            Intent menuAluno = new Intent(ProfessorActivity.this, AlunosActivity.class);
            startActivity(menuAluno);
            return true;
        }

        if(id == R.id.menu_professores){
            Intent menuProfessores = new Intent(ProfessorActivity.this, ProfessorActivity.class);
            startActivity(menuProfessores);
            return true;
        }

        if(id == R.id.menu_turmas){
            Intent menuTurma = new Intent(ProfessorActivity.this, TurmaActivity.class);
            startActivity(menuTurma);
            return true;
        }

        if(id == R.id.menu_usuario){
            Intent menuUsuario = new Intent(ProfessorActivity.this, UsuariosActivity.class);
            startActivity(menuUsuario);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}



