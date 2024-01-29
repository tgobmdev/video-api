package tgobmdev.videoapi.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tgobmdev.videoapi.entity.VideoEntity;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {

  List<VideoEntity> findAllByDeletedAtIsNull();

  Optional<VideoEntity> findByIdAndDeletedAtIsNull(UUID id);

  List<VideoEntity> findByTitleContainingIgnoreCaseAndDeletedAtIsNull(String title);

  List<VideoEntity> findByCategoriaEntitiesId(Long idCategoria);
}