package valdinei.domcorleone;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.Aluno;
import Model.DAO.AlunoDAO;
import Model.DAO.TurmaDAO;
import Model.Turma;


public class AlunosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nomeAluno, telefoneAluno, responsavelAluno, enderecoAluno;
    Button btnSalvarAluno, btnVoltarAluno, btnExcluirAluno, btnEditarAluno;
    ListView listAluno;
    Spinner spinnerTurma;
    ArrayList<Aluno> alunosLista;
    Aluno aluno;
    private PendingIntent resultPendingIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);

        nomeAluno = (EditText) findViewById(R.id.txtNomeCompletoAluno);
        telefoneAluno = (EditText) findViewById(R.id.txtTelefoneAluno);
        responsavelAluno = (EditText) findViewById(R.id.txtResponsavelAluno);
        enderecoAluno = (EditText) findViewById(R.id.txtEnderecoAluno);
        btnSalvarAluno = (Button) findViewById(R.id.btnSalvarAluno);
        btnEditarAluno = (Button) findViewById(R.id.btnEditarAluno);
        btnVoltarAluno = (Button) findViewById(R.id.btnVoltarAluno);
        btnExcluirAluno = (Button) findViewById(R.id.btnExcluirAluno);

        spinnerTurma = (Spinner) findViewById(R.id.spinnerAlunoTurma);

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

                Turma turma = (Turma) spinnerTurma.getSelectedItem();
                aluno = new Aluno();
                aluno.setNome(nomeAluno.getText().toString());
                aluno.setTelefone(telefoneAluno.getText().toString());
                aluno.setResponsavel(responsavelAluno.getText().toString());
                aluno.setEndereco(enderecoAluno.getText().toString());
                aluno.setTurma(turma.getId());

                AlunoDAO alunoDAO = new AlunoDAO(getApplicationContext());

                if (nomeAluno.length() == 0 || responsavelAluno.length() == 0) {
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
                    atualizaLista();
                    notificar();

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

                    if (nomeAluno.length() == 0 || responsavelAluno.length() == 0) {
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
                        Turma turma = (Turma) spinnerTurma.getSelectedItem();
                        AlunoDAO alunoDAO = new AlunoDAO(getApplicationContext());

                        aluno.setNome(nomeAluno.getText().toString());
                        aluno.setTelefone(telefoneAluno.getText().toString());
                        aluno.setResponsavel(responsavelAluno.getText().toString());
                        aluno.setEndereco(enderecoAluno.getText().toString());
                        aluno.setTurma(turma.getId());

                        alunoDAO.updateAluno(aluno);

                        AlertDialog.Builder builder = new AlertDialog.Builder(AlunosActivity.this);
                        builder.setTitle("Atualização do aluno");
                        builder.setMessage("Cadastro Atualizado com sucesso");
                        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        builder.show();
                        atualizaLista();

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
                    atualizaLista();


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



        TurmaDAO turmaDAO = new TurmaDAO(this);

        List<Turma> labels = turmaDAO.getTurmasSpinner();

        ArrayAdapter turmaAdapter = new ArrayAdapter (this, R.layout.support_simple_spinner_dropdown_item, labels);

        spinnerTurma.setAdapter(turmaAdapter);

    }

    private void notificar() {
        int idNotificacao = 12345;

        NotificationCompat.Builder mBuilder =  new NotificationCompat.Builder(this);

        mBuilder.setSmallIcon(android.R.drawable.ic_menu_today);
        mBuilder.setContentTitle("Atualização System Schools");
        mBuilder.setContentText("Novo Aluno foi adicionado ao System Schools.");

        mBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(this, AlunosActivity.class);

        resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(idNotificacao, mBuilder.build());

    }



    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String label = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //=========================================================

    public void populaCampos(int i){
        aluno = alunosLista.get(i);

        nomeAluno.setText(aluno.getNome());
        telefoneAluno.setText(aluno.getTelefone());
        responsavelAluno.setText(aluno.getResponsavel());
        enderecoAluno.setText(aluno.getEndereco());

    }

    public void limparCampos(){

        nomeAluno.setText("");
        telefoneAluno.setText("");
        responsavelAluno.setText("");
        enderecoAluno.setText("");
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.meu_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_alunos){
            Intent menuAluno = new Intent(AlunosActivity.this, AlunosActivity.class);
            startActivity(menuAluno);
            return true;
        }

        if(id == R.id.menu_professores){
            Intent menuProfessores = new Intent(AlunosActivity.this, ProfessorActivity.class);
            startActivity(menuProfessores);
            return true;
        }

        if(id == R.id.menu_turmas){
            Intent menuTurma = new Intent(AlunosActivity.this, TurmaActivity.class);
            startActivity(menuTurma);
            return true;
        }

        if(id == R.id.menu_usuario){
            Intent menuUsuario = new Intent(AlunosActivity.this, UsuariosActivity.class);
            startActivity(menuUsuario);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void atualizaLista(){
        AlunoDAO alunoDAO = new AlunoDAO(this);

        alunosLista = alunoDAO.getAlunos();

        listAluno.setAdapter(
                new ListAdapterAluno(this, alunosLista)
        );
    }


}