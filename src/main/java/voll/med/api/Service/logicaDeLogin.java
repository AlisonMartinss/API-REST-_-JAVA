package voll.med.api.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import voll.med.api.Entidades.Repository.LoginRepository;

@Service
public class logicaDeLogin implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         return loginRepository.findByusuario(username);
        }
    }

