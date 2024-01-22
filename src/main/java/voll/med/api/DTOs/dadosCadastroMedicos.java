package voll.med.api.DTOs;

import voll.med.api.DTOs.ENUMs.Especialidade;

public record dadosCadastroMedicos(String nome, String email, String crm , Especialidade especialidade, DadosEndereco endereco) {
}
