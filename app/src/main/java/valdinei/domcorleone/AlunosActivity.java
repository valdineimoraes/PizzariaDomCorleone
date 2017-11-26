package valdinei.domcorleone;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Aluno;
import Model.DAO.AlunoDAO;


public class AlunosActivity extends AppCompatActivity {

    EditText txtNomeAluno, txtTelefoneAluno, txtResponsavelAluno, txtEnderecoAluno;
    Button btnSalvarAluno, btnVoltarAluno, btnExcluirAluno, btnEditarAluno;
    ListView listAluno;
    ArrayList<Aluno> alunosLista;
    Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);

        txtNomeAluno = (EditText) findViewById(R.id.txtNomeCompletoAluno);
        txtTelefoneAluno = (EditText) findViewById(R.id.txtTelefoneAlunoLista);
        txtResponsavelAluno = (EditText) findViewById(R.id.txtResponsavelAluno);
        txtEnderecoAluno = (EditText) findViewById(R.id.txtEnderecoAlunoLista);
        btnSalvarAluno = (Button) findViewById(R.id.btnSalvarAluno);
        btnEditarAluno = (Button) findViewById(R.id.btnEditarAluno);
        btnVoltarAluno = (Button) findViewById(R.id.btnVoltarAluno);
        btnExcluirAluno = (Button) findViewById(R.id.btnExcluirAluno);

        listAluno = (ListView) findViewById(R.id.listViewAlunos);

        btnVoltarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSalvarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aluno = new Aluno();
                aluno.setNome(txtNomeAluno.getText().toString());
                aluno.setTelefone(txtTelefoneAluno.getText().toString());
                aluno.setResponsavel(txtResponsavelAluno.getText().toString());
                aluno.setEndereco(txtEnderecoAluno.getText().toString());

                AlunoDAO alunoDAO = new AlunoDAO(getApplicationContext());

                if (txtNomeAluno.length() == 0 || txtResponsavelAluno.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AlunosActivity.this);
                    builder.setTitle("Erro ao cadastrar aluno");
                    builder.setMessage("O campo nome ou responsavel nao podem estar vazio");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(AlunosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else if (alunoDAO.insertAluno(aluno)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AlunosActivity.this);
                    builder.setTitle("Cadastro de aluno");
                    builder.setMessage("Cadastro realizado com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();

                    limparCampos();
                    aluno = null;

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AlunosActivity.this);
                    builder.setTitle("Erro ao cadastrar aluno");
                    builder.setMessage("Nao foi possivel cadastrar aluno");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(AlunosActivity.this, "Cadastro Negado" , Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }
        });

        btnEditarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (aluno==null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AlunosActivity.this);
                    builder.setMessage("Nenhum aluno selecionado.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(alunosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }else {

                    if (txtNomeAluno.length() == 0 || txtResponsavelAluno.length() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AlunosActivity.this);
                        builder.setTitle("Erro ao cadastrar aluno");
                        builder.setMessage("O campo nome ou responsavel nao podem estar vazio");
                        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(AlunosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    } else{

                        AlunoDAO alunoDAO = new AlunoDAO(getApplicationContext());

                        aluno.setNome(txtNomeAluno.getText().toString());
                        aluno.setTelefone(txtTelefoneAluno.getText().toString());
                        aluno.setResponsavel(txtResponsavelAluno.getText().toString());
                        aluno.setEndereco(txtEnderecoAluno.getText().toString());

                        alunoDAO.updateAluno(aluno);

                        AlertDialog.Builder builder = new AlertDialog.Builder(AlunosActivity.this);
                        builder.setTitle("Atualização do aluno");
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

        btnExcluirAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlunoDAO alunoDAO = new AlunoDAO(getApplicationContext());

                if (aluno==null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AlunosActivity.this);
                    builder.setTitle("Erro ao excluir o aluno");
                    builder.setMessage("Nenhum aluno selecionado para excluir, os campos nao podem estar vazios.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(AlunosActivity.this, "Exclusao Negada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }else {
                    alunoDAO.deleteAluno(aluno);
                    AlertDialog.Builder builder = new AlertDialog.Builder(AlunosActivity.this);
                    builder.setTitle("Exclusão do aluno");
                    builder.setMessage("Cadastro excluido com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();
                    aluno = null;
                    limparCampos();


                }
            }
        });

        AlunoDAO alunoDAO = new AlunoDAO(this);
        alunosLista = alunoDAO.getAlunos();

        listAluno.setAdapter(
                new ListAdapterAluno(this, alunosLista)
        );

        listAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                populaCampos(i);
            }
        });
    }

    public void populaCampos(int i){
        aluno = alunosLista.get(i);

        txtNomeAluno.setText(aluno.getNome());
        txtTelefoneAluno.setText(aluno.getTelefone());
        txtResponsavelAluno.setText(aluno.getResponsavel());
        txtEnderecoAluno.setText(aluno.getEndereco());

    }

    public void limparCampos(){

        txtNomeAluno.setText("");
        txtTelefoneAluno.setText("");
        txtResponsavelAluno.setText("");
        txtEnderecoAluno.setText("");
    }

}