package bd;

/**
 * Created by valdinei on 07/11/17.
 */

public class ScriptSQL {

    public static String getCreateUsuarios(){
        //junção de diversas string sql
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE Usuarios( ");
        stringBuilder.append("_id INTEGER PRIMARY KEY, ");
        stringBuilder.append("nome VARCHAR(100), ");
        stringBuilder.append("usuario VARCHAR(50), ");
        stringBuilder.append("senha VARCHAR(20), ");
        stringBuilder.append("cargo VARCHAR(50), ");
        stringBuilder.append("telefone VARCHAR(14)); ");

        return stringBuilder.toString();
    }

    public static String getCreateProfessores(){
        //junção de diversas string sql
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE Professores( ");
        stringBuilder.append("_id INTEGER PRIMARY KEY, ");
        stringBuilder.append("nome VARCHAR(100), ");
        stringBuilder.append("telefone VARCHAR(14), ");
        stringBuilder.append("formacao VARCHAR(60)); ");

        return stringBuilder.toString();
    }

    public static String getCreateTurmas(){
        //junção de diversas string sql
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE Turmas( ");
        stringBuilder.append("_id INTEGER PRIMARY KEY, ");
        stringBuilder.append("nome VARCHAR(100), ");
        stringBuilder.append("total_aluno INTEGER, ");
        stringBuilder.append("professor INTEGER, ");
        stringBuilder.append("FOREIGN KEY(professor) REFERENCES Professores(_id)); ");

        return stringBuilder.toString();
    }

    public static String getCreateAlunos(){
        //junção de diversas string sql
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE Alunos( ");
        stringBuilder.append("_id INTEGER PRIMARY KEY, ");
        stringBuilder.append("nome VARCHAR(100), ");
        stringBuilder.append("telefone VARCHAR(14), ");
        stringBuilder.append("responsavel VARCHAR(50), ");
        stringBuilder.append("endereco VARCHAR(80), ");
        stringBuilder.append("turma INTEGER, ");
        stringBuilder.append("FOREIGN KEY(turma) REFERENCES Turma(_id)); ");

        return stringBuilder.toString();
    }

}
