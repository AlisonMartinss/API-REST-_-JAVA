package voll.med.api.DTOs;

public record DTOLogininfo(String usuario, String senha) {


    public String getusuario() {
        return usuario;
    }

    public String getsenha() {
        return senha;
    }
}
