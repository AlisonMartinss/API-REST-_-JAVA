package voll.med.api.DTOs;

import voll.med.api.DTOs.ENUMs.Especialidade;
import voll.med.api.Entidades.Endereco;
import voll.med.api.Entidades.Medico;

public record DadosExposicaoMedico(Long id,String nome, String email, String crm , Especialidade especialidade, Endereco endereco) {

    public DadosExposicaoMedico (Medico medico) {
        this(medico.getId(),medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade(),medico.getEndereco());
    }


}
