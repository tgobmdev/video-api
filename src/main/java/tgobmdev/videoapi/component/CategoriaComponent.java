package tgobmdev.videoapi.component;

import java.util.List;
import java.util.Optional;
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

  public Optional<CategoriaEntity> findCategoryById(Long idCategoria) {
    return categoriaRepository.findById(idCategoria);
  }
}