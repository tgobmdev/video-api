package tgobmdev.videoapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "video")
public class VideoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String url;

  private LocalDateTime deletedAt;

  private Boolean deleted;

  @ManyToMany
  @JoinTable(
      name = "video_categoria",
      joinColumns = @JoinColumn(name = "video_id"),
      inverseJoinColumns = @JoinColumn(name = "categoria_id"))
  private Set<CategoryEntity> categoriaEntities;
}