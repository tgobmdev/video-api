package tgobmdev.videoapi.repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tgobmdev.videoapi.dto.request.VideoFilter;
import tgobmdev.videoapi.entity.VideoEntity;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {

  @Query("SELECT v FROM VideoEntity v WHERE v.deletedAt IS NULL AND (:#{#filter.search} IS NULL OR LOWER(v.title) LIKE LOWER(CONCAT('%', :#{#filter.search}, '%')))")
  Set<VideoEntity> findVideosByFilter(VideoFilter filter);

  Optional<VideoEntity> findByIdAndDeletedAtIsNull(UUID videoId);
}