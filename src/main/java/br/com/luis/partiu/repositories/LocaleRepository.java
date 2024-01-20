package br.com.luis.partiu.repositories;

import br.com.luis.partiu.models.Locale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocaleRepository extends JpaRepository<Locale, UUID> {
}
