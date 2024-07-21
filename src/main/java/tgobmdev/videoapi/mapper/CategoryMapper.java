package tgobmdev.videoapi.mapper;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tgobmdev.videoapi.dto.response.CategoryResponse;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.CategoryEntity;
import tgobmdev.videoapi.entity.VideoEntity;

@Mapper(componentModel = "spring", uses = {VideoMapper.class})
public interface CategoryMapper {

  @Mapping(target = "videos", expression = "java(includeVideos ? mapVideos(category.getVideoEntities()) : null)")
  CategoryResponse toResponse(CategoryEntity category, boolean includeVideos);

  List<CategoryResponse> toResponses(List<CategoryEntity> categoryEntities);

  default List<VideoResponse> mapVideos(Set<VideoEntity> videos) {
    return videos.stream()
        .map(VideoMapper.INSTANCE::toResponse)
        .toList();
  }
}