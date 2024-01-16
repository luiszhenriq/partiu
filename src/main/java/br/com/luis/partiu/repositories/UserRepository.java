package br.com.luis.partiu.repositories;

import br.com.luis.partiu.models.Gender;
import br.com.luis.partiu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    List<User> findByGender(Gender gender);
}
