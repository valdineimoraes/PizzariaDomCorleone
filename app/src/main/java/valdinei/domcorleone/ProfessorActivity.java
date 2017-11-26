package valdinei.domcorleone;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

    EditText editTextCodigoProf, editTextNomeProf, editTextTelefoneProf, editTextFormacaoProf;
    Button btnSalvarProf, btnVoltarProf, btnExcluirProf, btnEditarProf;
    ListView listProf;
    ArrayList<Professor> professores;

    Professor professor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);


        //editTextCodigo = (EditText) findViewById(R.id.txtCodigoUser);
        editTextNomeProf = (EditText) findViewById(R.id.txtNomeProf);
        editTextTelefoneProf = (EditText) findViewById(R.id.txtTelefoneProf);
        editTextFormacaoProf= (EditText) findViewById(R.id.txtFormacaoProf);

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

                Professor professor = new Professor();
                professor.setNome(editTextNomeProf.getText().toString());
                professor.setTelefone(editTextTelefoneProf.getText().toString());
                professor.setFormacao(editTextFormacaoProf.getText().toString());

                ProfessorDAO professorDAO = new ProfessorDAO(getApplicationContext());

                if (editTextNomeProf.length() == 0 || editTextFormacaoProf.length() == 0) {
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

                    if (editTextNomeProf.length() == 0 || editTextFormacaoProf.length() == 0) {
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

                        professor.setNome(editTextNomeProf.getText().toString());
                        professor.setTelefone(editTextTelefoneProf.getText().toString());
                        professor.setFormacao(editTextFormacaoProf.getText().toString());

                        professorDAO.updateProfessor(professor);

                        AlertDialog.Builder builder = new AlertDialog.Builder(valdinei.domcorleone.ProfessorActivity.this);
                        builder.setTitle("Atualização do professor");
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



    public void populaCampos(int i){
        professor = professores.get(i);

        editTextNomeProf.setText(professor.getNome());
        editTextTelefoneProf.setText(professor.getTelefone());
        editTextFormacaoProf.setText(professor.getFormacao());
    }

    public void limparCampos(){

        editTextNomeProf.setText("");
        editTextTelefoneProf.setText("");
        editTextFormacaoProf.setText("");

    }

}



