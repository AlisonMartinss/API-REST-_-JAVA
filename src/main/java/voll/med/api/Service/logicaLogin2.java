package voll.med.api.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voll.med.api.DTOs.DTOLogininfo;
import voll.med.api.DTOs.DTOtoken;
import voll.med.api.Entidades.Logins;
import voll.med.api.Entidades.Repository.LoginRepository;
import voll.med.api.DTOs.UsuarioPosteriorRegistro;

@RestController
@RequestMapping("/login")
public class logicaLogin2 {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private LoginRepository loginRepository;

    private final PasswordEncoder passwordEncoder;

    public logicaLogin2(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private TokenService tokenService;




    @PostMapping("/entrada")
    public ResponseEntity efetuarlogin (@RequestBody @Valid  DTOLogininfo usuario){

        var token = new UsernamePasswordAuthenticationToken(usuario.usuario(),usuario.getsenha());
        var loginre = manager.authenticate(token);
        var tokenP = tokenService.gerarToken((Logins) loginre.getPrincipal());


        return ResponseEntity.ok(new DTOtoken(tokenP));


    }





    @PostMapping("/cadastroUsuario")
    @Transactional
    public ResponseEntity cadastro (@RequestBody DTOLogininfo usuario){

        String usuarioP = usuario.usuario();
        String senhaP =  passwordEncoder.encode(usuario.getsenha());

        UsuarioPosteriorRegistro usuarioPosteriRegistro = new UsuarioPosteriorRegistro(usuarioP,senhaP);

        var usuario01 = new Logins(usuarioPosteriRegistro);
        loginRepository.save(usuario01);


        return ResponseEntity.ok(usuario01);

    }

}
