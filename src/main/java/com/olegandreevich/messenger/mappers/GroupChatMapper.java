package com.olegandreevich.messenger.mappers;

import com.olegandreevich.messenger.entities.chats.GroupChat;
import com.olegandreevich.messenger.dto.chats.GroupChatDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GroupChatMapper implements EntityMapper<GroupChatDto, GroupChat> {

    private final ModelMapper modelMapper;

    public GroupChatMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GroupChat toEntity(GroupChatDto dto) {
        if (dto == null) {
            return null;
        }

        GroupChat groupChat = modelMapper.map(dto, GroupChat.class);
        // Устанавливаем id вручную, если оно отсутствует в DTO
        if (groupChat.getId() == null) {
            groupChat.setId(UUID.randomUUID().toString());
        }
        return groupChat;
    }

    @Override
    public GroupChatDto toDto(GroupChat entity) {
        if (entity == null) {
            return null;
        }

        return modelMapper.map(entity, GroupChatDto.class);
    }

    @Override
    public List<GroupChat> toEntity(List<GroupChatDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupChatDto> toDto(List<GroupChat> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
