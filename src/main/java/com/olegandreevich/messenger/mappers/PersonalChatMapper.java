package com.olegandreevich.messenger.mappers;

import com.olegandreevich.messenger.entities.chats.PersonalChat;
import com.olegandreevich.messenger.dto.chats.PersonalChatDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonalChatMapper implements EntityMapper<PersonalChatDto, PersonalChat> {

    private final ModelMapper modelMapper;

    public PersonalChatMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonalChat toEntity(PersonalChatDto dto) {
        if (dto == null) {
            return null;
        }

        PersonalChat personalChat = modelMapper.map(dto, PersonalChat.class);
        // Устанавливаем id вручную, если оно отсутствует в DTO
        if (personalChat.getId() == null) {
            personalChat.setId(UUID.randomUUID().toString());
        }
        return personalChat;
    }

    @Override
    public PersonalChatDto toDto(PersonalChat entity) {
        if (entity == null) {
            return null;
        }

        return modelMapper.map(entity, PersonalChatDto.class);
    }

    @Override
    public List<PersonalChat> toEntity(List<PersonalChatDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonalChatDto> toDto(List<PersonalChat> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
