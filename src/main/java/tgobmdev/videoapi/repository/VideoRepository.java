package tgobmdev.videoapi.repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tgobmdev.videoapi.entity.VideoEntity;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {

  Set<VideoEntity> findAllByDeletedAtIsNull();

  Optional<VideoEntity> findByIdAndDeletedAtIsNull(UUID videoId);

  Set<VideoEntity> findByTitleContainingIgnoreCaseAndDeletedAtIsNull(String title);
}