package valdinei.domcorleone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.DAO.ProfessorDAO;
import Model.DAO.TurmaDAO;
import Model.DAO.UsuarioDAO;
import Model.Professor;
import Model.Turma;
import Model.Usuario;

public class TurmaActivity extends AppCompatActivity {

    EditText salaNumero, totalAlunos, nomeTurma;
    Spinner spinnerProfessor;
    Button btnSalvarTurma, btnEditarTurma, btnVoltarTurma, btnExcluirTurma;
    ListView lsViewTurma;
    ArrayList<Turma> turmaArrayList;
    ArrayList<Professor> optionsProfessores;
    ArrayList<Professor> arrayProf;

    Turma turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma);

        nomeTurma = (EditText) findViewById(R.id.edtCadNomeTurma);
        salaNumero = (EditText) findViewById(R.id.edtCadSalaNumeroTurma);
        totalAlunos = (EditText) findViewById(R.id.edtCadTotalAlunosTurma);

        spinnerProfessor = (Spinner) findViewById(R.id.spinnerProfessores);

        btnSalvarTurma = (Button) findViewById(R.id.btnSalvarTurma);
        btnEditarTurma = (Button) findViewById(R.id.btnEditarTurma);
        btnVoltarTurma = (Button) findViewById(R.id.btnVoltarTurma);
        btnExcluirTurma = (Button) findViewById(R.id.btnExcluirTurma);
        lsViewTurma = (ListView) findViewById(R.id.listViewTurma);

        btnVoltarTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSalvarTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Professor professor = (Professor) spinnerProfessor.getSelectedItem();
                turma = new Turma();

                turma.setNome(nomeTurma.getText().toString());
                turma.setTotalAlunos(Integer.parseInt(totalAlunos.getText().toString()));
                turma.setSala(Integer.parseInt(salaNumero.getText().toString()));
                turma.setProfessor(professor.getId());

                TurmaDAO turmaDAO = new TurmaDAO(getApplicationContext());

                if (nomeTurma.length() == 0 || salaNumero.length() == 0 || totalAlunos.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Erro ao cadastrar turma");
                    builder.setMessage("O campo nome ou numero da sala ou total de alunos nao podem estar vazio");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(TurmaActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else if (turmaDAO.insertTurma(turma)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Cadastro da Turma");
                    builder.setMessage("Cadastro realizado com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();
                    limparCampos();
                    turma = null;
                    atualizaListaTurma();

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Erro ao cadastrar turma");
                    builder.setMessage("Nao foi possivel cadastrar turma");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(TurmaActivity.this, "Cadastro Negado" , Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }
        });

        btnEditarTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (turma==null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setMessage("Nenhuma turma selecionada.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(UsuariosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else {

                    if (nomeTurma.length() == 0 || salaNumero.length() == 0 || totalAlunos.length() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                        builder.setTitle("Erro ao atualizar turma");
                        builder.setMessage("O campo nome ou numero da sala ou total de alunos nao podem estar vazio");
                        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Toast.makeText(TurmaActivity.this, "Atualização Negada", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }else {
                        Professor professor = (Professor) spinnerProfessor.getSelectedItem();
                        TurmaDAO turmaDAO = new TurmaDAO(getApplicationContext());

                        turma.setNome(nomeTurma.getText().toString());
                        turma.setTotalAlunos(Integer.parseInt(totalAlunos.getText().toString()));
                        turma.setSala(Integer.parseInt(salaNumero.getText().toString()));
                        turma.setProfessor(professor.getId());

                        turmaDAO.updateTurma(turma);

                        AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                        builder.setTitle("Cadastro da Turma");
                        builder.setMessage("Atualização realizada com sucesso");
                        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        builder.show();
                        atualizaListaTurma();

                    }
                }

            }
        });

        btnExcluirTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TurmaDAO turmaDAO = new TurmaDAO(getApplicationContext());

                if (turma == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Erro ao excluir turma");
                    builder.setMessage("Nenhuma turma selecionada para excluir");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(TurmaActivity.this, "Exclusao Negada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else{
                    turmaDAO.deleteTurma(turma);
                    AlertDialog.Builder builder = new AlertDialog.Builder(TurmaActivity.this);
                    builder.setTitle("Excluir Turma");
                    builder.setMessage("Exclusao realizada com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();
                    limparCampos();
                    turma = null;
                    atualizaListaTurma();

                }
            }
        });

        //=============== spinner Professor populado

        ProfessorDAO professorDAO = new ProfessorDAO(this);
        List<Professor> labelsProf= professorDAO.getProfessoSpinner();

        ArrayAdapter profAdapter = new ArrayAdapter (this, R.layout.support_simple_spinner_dropdown_item, labelsProf);

        spinnerProfessor.setAdapter(profAdapter);



        //============================================

        TurmaDAO turmaDAO = new TurmaDAO(this);
        turmaArrayList = turmaDAO.getTurmas();

        lsViewTurma.setAdapter(
                new ListAdapterTurma(this, turmaArrayList)
        );

        lsViewTurma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                populaCampos(i);
            }
        });
    }

    public void populaCampos(int i){
        turma = turmaArrayList.get(i);

        nomeTurma.setText(turma.getNome());
        salaNumero.setText(String.valueOf(turma.getSala()));
        totalAlunos.setText(String.valueOf(turma.getTotalAlunos()));
        //spinnerProfessor.setSelection(indiceProfessor(turma));
    }

    public void limparCampos(){

        nomeTurma.setText("");
        salaNumero.setText("");
        totalAlunos.setText("");
        //spinnerProfessor.setSelection(0);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.meu_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_alunos){
            Intent menuAluno = new Intent(TurmaActivity.this, AlunosActivity.class);
            startActivity(menuAluno);
            return true;
        }

        if(id == R.id.menu_professores){
            Intent menuProfessores = new Intent(TurmaActivity.this, ProfessorActivity.class);
            startActivity(menuProfessores);
            return true;
        }

        if(id == R.id.menu_turmas){
            Intent menuTurma = new Intent(TurmaActivity.this, TurmaActivity.class);
            startActivity(menuTurma);
            return true;
        }

        if(id == R.id.menu_usuario){
            Intent menuUsuario = new Intent(TurmaActivity.this, UsuariosActivity.class);
            startActivity(menuUsuario);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void atualizaListaTurma(){
        TurmaDAO turmaDAO = new TurmaDAO(this);
        turmaArrayList = turmaDAO.getTurmas();

        lsViewTurma.setAdapter(
                new ListAdapterTurma(this, turmaArrayList)
        );
    }

}
