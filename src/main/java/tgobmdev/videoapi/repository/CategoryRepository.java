package tgobmdev.videoapi.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tgobmdev.videoapi.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

  Set<CategoryEntity> findByIdIn(Set<Long> categoryIds);
}