package Model;

/**
 * Created by valdinei on 09/11/17.
 */

public class Turma {

    private int id;
    private String nome;
    private int totalAlunos;
    private int sala;
    private int professor;

    public Turma() {
    }

    public Turma(int id, String nome, int totalAlunos, int sala, int professor) {
        this.id = id;
        this.nome = nome;
        this.totalAlunos = totalAlunos;
        this.sala = sala;
        this.professor = professor;

    }

    public Turma(String nome, int totalAlunos, int sala, int professor) {
        this.nome = nome;
        this.totalAlunos = totalAlunos;
        this.sala = sala;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTotalAlunos() {
        return totalAlunos;
    }

    public void setTotalAlunos(int totalAlunos) {
        this.totalAlunos = totalAlunos;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return  nome;
    }
}
