package voll.med.api.Entidades.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import voll.med.api.Entidades.Logins;

public interface LoginRepository extends JpaRepository<Logins,Long> {

    UserDetails findByusuario(String username);
}
