package tgobmdev.videoapi.mapper;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import tgobmdev.videoapi.dto.request.VideoRequest;
import tgobmdev.videoapi.dto.response.VideoResponse;
import tgobmdev.videoapi.entity.VideoEntity;

@Mapper(componentModel = "spring")
public interface VideoMapper {

  VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "deletedAt", ignore = true),
      @Mapping(target = "deleted", ignore = true),
      @Mapping(target = "categoryEntities", ignore = true)})
  VideoEntity toEntity(VideoRequest videoRequest);

  VideoResponse toResponse(VideoEntity videoEntity);

  List<VideoResponse> toResponses(Set<VideoEntity> videoEntities);
}