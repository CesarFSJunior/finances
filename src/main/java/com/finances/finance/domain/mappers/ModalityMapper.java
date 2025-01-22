package com.finances.finance.domain.mappers;

import com.finances.finance.domain.entities.modalidade.Modality;
import com.finances.finance.domain.entities.modalidade.ModalityCreateDto;
import com.finances.finance.domain.entities.modalidade.ModalityDto;
import com.finances.finance.domain.entities.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModalityMapper {

    @Autowired
    UserMapper userMapper;

    public ModalityDto modalityToDto(Modality modality) {

        UserDto userDto = userMapper.userToDto(modality.getOwner());

        return new ModalityDto(
                modality.getIdCartao(),
                modality.getNomeCartao(),
                modality.getModalityType(),
                userDto
        );
    }

    public Modality modalityCreateDtoToModality(ModalityCreateDto modalityCreateDto) {

        Modality modality = new Modality();
        modality.setNomeCartao(modalityCreateDto.nomeCartao());
        modality.setModalityType(modalityCreateDto.modalityType());
        return modality;
    }
}
