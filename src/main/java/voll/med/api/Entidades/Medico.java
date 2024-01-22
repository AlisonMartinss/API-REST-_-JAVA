package voll.med.api.Entidades;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import voll.med.api.DTOs.DadosAtualizacaoMedico;
import voll.med.api.DTOs.ENUMs.Especialidade;
import voll.med.api.DTOs.dadosCadastroMedicos;

@Table(name = "medicos")
@Entity(name= "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String email;
  private String crm;

  @Enumerated(EnumType.STRING)
  private Especialidade especialidade;

  @Embedded
  private Endereco endereco;

  public Medico(dadosCadastroMedicos json) {
    this.nome = json.nome();
    this.email = json.email();;
    this.crm = json.crm();
    this.especialidade = json.especialidade();
    this.endereco = new Endereco(json.endereco());

  }

    public void atualizarCadastro(DadosAtualizacaoMedico dadosA) {

    if (dadosA.nome() != null) {
      this.nome = dadosA.nome();
    }

    if (dadosA.email() != null){
        this.email = dadosA.email();
    }

    if (dadosA.endereco() != null){
        this.endereco.atualizarinformacoes(dadosA.endereco());
    }

    }
}
