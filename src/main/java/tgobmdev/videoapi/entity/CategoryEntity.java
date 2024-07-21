package tgobmdev.videoapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "category")
@SequenceGenerator(name = "category_seq", sequenceName = "category_id_seq", allocationSize = 1)
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String color;

  @ManyToMany(mappedBy = "categoryEntities")
  private Set<VideoEntity> videoEntities;
}