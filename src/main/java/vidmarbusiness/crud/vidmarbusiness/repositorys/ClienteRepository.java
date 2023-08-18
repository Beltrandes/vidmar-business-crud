package vidmarbusiness.crud.vidmarbusiness.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import vidmarbusiness.crud.vidmarbusiness.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
