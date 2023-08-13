package vidmarbusiness.crud.vidmarbusiness.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import vidmarbusiness.crud.vidmarbusiness.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
