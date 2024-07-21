package tgobmdev.videoapi.controller.impl;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tgobmdev.videoapi.annotation.Logging;
import tgobmdev.videoapi.controller.CategoryController;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.service.CategoryService;

@RestController
@RequestMapping(value = "categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryControllerImpl implements CategoryController {

  private final CategoryService categoryService;

  public CategoryControllerImpl(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Logging
  @Override
  @GetMapping
  public ResponseEntity<List<CategoryResponse>> findAllCategories() {
    return ResponseEntity.ok(categoryService.findAllCategories());
  }

  @Logging
  @Override
  @GetMapping("/{categoryId}/videos")
  public ResponseEntity<CategoryResponse> findVideosByCategoryId(@PathVariable Long categoryId) {
    return ResponseEntity.ok(categoryService.findVideosByCategoryId(categoryId));
  }

  @Logging
  @Override
  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long categoryId) {
    return ResponseEntity.ok(categoryService.findCategoryById(categoryId));
  }

  @Logging
  @Override
  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity.noContent()
        .build();
  }
}