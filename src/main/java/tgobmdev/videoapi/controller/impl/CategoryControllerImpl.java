package tgobmdev.videoapi.controller.impl;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.controller.CategoryController;
import tgobmdev.videoapi.dto.response.CategoriaResponse;
import tgobmdev.videoapi.dto.response.CategoriaVideoResponse;
import tgobmdev.videoapi.service.CategoryService;

@Log4j2
@RestController
@RequestMapping(value = "categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryControllerImpl implements CategoryController {

  private final CategoryService categoryService;

  public CategoryControllerImpl(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  @GetMapping(value = "/list")
  public ResponseEntity<List<CategoriaResponse>> findAllCategories() {
    log.info("Request [GET] received em [/categories/list]");
    List<CategoriaResponse> categoriaResponses = categoryService.findAllCategories();
    log.info("Request [GET] finished em [/categories/list]");
    return ResponseEntity.ok(categoriaResponses);
  }

  @Override
  @GetMapping("/{categoryId}/videos")
  public ResponseEntity<CategoriaVideoResponse> findVideosByCategoryId(
      @PathVariable Long categoryId) {
    log.info("Request [GET] received em [/categories/{}/videos]", categoryId);
    CategoriaVideoResponse categoriaVideoResponses = categoryService.findVideosByCategoryId(
        categoryId);
    log.info("Request [GET] finished em [/categories/{}/videos]", categoryId);
    return ResponseEntity.ok(categoriaVideoResponses);
  }
}