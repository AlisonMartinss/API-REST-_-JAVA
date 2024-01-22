package voll.med.api.Service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import voll.med.api.Entidades.Repository.LoginRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("ENTRANDO NO FILTRO 'SecurityFilter'");
        System.out.println("o que veio no 'request'" + request);

        var TokenJWT = RecuperarToken(request);

        if (TokenJWT != null) {
            System.out.println("ENTRANDO NO 'if'");


            var TokenJWvalidado = tokenService.verificaVeraci(TokenJWT);
            System.out.println("TOKEN VALIDADO");
            var usuario = loginRepository.findByusuario(TokenJWvalidado);

            var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }





    private String RecuperarToken (HttpServletRequest http){

        System.out.println("ENTRAMOS EM RecuperarToken");
        System.out.println("'http header' dentro " + http.getHeader("Authorization"));

        var TokenRecuperado = http.getHeader("Authorization");

        System.out.println(TokenRecuperado);

        if (TokenRecuperado != null) {
            return TokenRecuperado.replace("Bearer","").trim();
        }

        return null;




    }
}
