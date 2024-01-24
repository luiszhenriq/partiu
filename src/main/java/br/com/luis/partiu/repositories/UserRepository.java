package br.com.luis.partiu.repositories;

import br.com.luis.partiu.models.Gender;
import br.com.luis.partiu.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    Page<User> findByGender(Gender gender, Pageable pageable);

    UserDetails findByEmail(String email);
}
