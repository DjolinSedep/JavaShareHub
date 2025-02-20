package kg.attractor.javasharehub.repository;

import kg.attractor.javasharehub.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    Page<File> findAllByStatus(String status, Pageable pageable);
    Page<File> findAllByStatusAndCategoryId(String status, Long category_id, Pageable pageable);
}
