package voll.med.api.DTOs;

import voll.med.api.DTOs.ENUMs.Especialidade;
import voll.med.api.Entidades.Medico;

public record DadosListagemMedicos(Long id, String nome, String crm, Especialidade especialidade) {

    public DadosListagemMedicos(Medico medico) {
       this(medico.getId(),medico.getNome(),medico.getCrm(),medico.getEspecialidade());
    }
}
