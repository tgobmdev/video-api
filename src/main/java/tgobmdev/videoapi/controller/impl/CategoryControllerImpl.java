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
import tgobmdev.videoapi.dto.response.CategoryResponse;
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
  public ResponseEntity<List<CategoryResponse>> findAllCategories() {
    log.info("Request [GET] received at [/categories/list]");
    List<CategoryResponse> categoryResponses = categoryService.findAllCategories();
    log.info("Request [GET] finished at [/categories/list]");
    return ResponseEntity.ok(categoryResponses);
  }

  @Override
  @GetMapping("/{categoryId}/videos")
  public ResponseEntity<CategoryResponse> findVideosByCategoryId(@PathVariable Long categoryId) {
    log.info("Request [GET] received at [/categories/{}/videos]", categoryId);
    CategoryResponse categoryResponse = categoryService.findVideosByCategoryId(categoryId);
    log.info("Request [GET] finished at [/categories/{}/videos]", categoryId);
    return ResponseEntity.ok(categoryResponse);
  }

  @Override
  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long categoryId) {
    log.info("Request [GET] received at [/categories/{}]", categoryId);
    CategoryResponse categoryResponse = categoryService.findCategoryById(categoryId);
    log.info("Request [GET] finished at [/categories/{}]", categoryId);
    return ResponseEntity.ok(categoryResponse);
  }
}