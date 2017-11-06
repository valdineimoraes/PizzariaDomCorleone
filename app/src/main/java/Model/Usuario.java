package Model;

/**
 * Created by valdinei on 04/11/17.
 */

public class Usuario {

    private int id;
    private String nomeCompleto;
    private String usuario;
    private String senha;
    private String cargo;
    private String telefone;

    public Usuario(String nomeCompleto, String usuario, String senha, String cargo, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.usuario = usuario;
        this.senha = senha;
        this.cargo = cargo;
        this.telefone = telefone;
    }

    public Usuario(int id, String nomeCompleto, String usuario, String senha, String cargo, String telefone) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.usuario = usuario;
        this.senha = senha;
        this.cargo = cargo;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
