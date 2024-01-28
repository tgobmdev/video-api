package tgobmdev.videoapi.component;

import java.util.List;
import org.springframework.stereotype.Component;
import tgobmdev.videoapi.entity.CategoriaEntity;
import tgobmdev.videoapi.repository.CategoriaRepository;

@Component
public class CategoriaComponent {

  private final CategoriaRepository categoriaRepository;

  public CategoriaComponent(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public List<CategoriaEntity> findAllCategories() {
    return categoriaRepository.findAll();
  }
}