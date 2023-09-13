package vidmarbusiness.crud.vidmarbusiness.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import vidmarbusiness.crud.vidmarbusiness.models.Work;

public interface WorkRepository extends JpaRepository <Work, Long> {
}
