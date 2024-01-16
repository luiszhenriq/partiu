package br.com.luis.partiu.repositories;

import br.com.luis.partiu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


}
