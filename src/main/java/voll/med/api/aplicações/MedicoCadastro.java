package voll.med.api.aplicações;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import voll.med.api.DTOs.DadosAtualizacaoMedico;
import voll.med.api.DTOs.DadosExposicaoMedico;
import voll.med.api.DTOs.DadosListagemMedicos;
import voll.med.api.DTOs.dadosCadastroMedicos;
import voll.med.api.Entidades.Medico;
import voll.med.api.Entidades.Repository.MedicoRepository;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoCadastro {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro (@RequestBody @Valid dadosCadastroMedicos Json, UriComponentsBuilder uriComponentsBuilder){
       var medico = new Medico(Json);
       repository.save(medico);

       var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

       return ResponseEntity.created(uri).body(new DadosExposicaoMedico(medico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar (@PageableDefault(size=2, sort = {"nome"}) Pageable paginacao) {
        var lista = repository.findAll(paginacao).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(lista);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid DadosAtualizacaoMedico dadosA){
        Medico medico = repository.getReferenceById(dadosA.id());
        medico.atualizarCadastro(dadosA);

        return ResponseEntity.ok(new DadosExposicaoMedico(medico));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/mdetalhada/{id}")
    public  ResponseEntity listagemdetalhada (@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosExposicaoMedico(usuario));
    }



}
