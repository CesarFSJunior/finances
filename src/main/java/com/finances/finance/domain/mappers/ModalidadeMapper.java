package com.finances.finance.domain.mappers;

import com.finances.finance.domain.entities.modalidade.Modalidade;
import com.finances.finance.domain.entities.modalidade.ModalidadeDto;
import com.finances.finance.domain.entities.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModalidadeMapper {

    @Autowired
    UserMapper userMapper;

    public ModalidadeDto modalidateToDto(Modalidade modalidade) {

        UserDto userDto = userMapper.userToDto(modalidade.getOwner());

        ModalidadeDto modalidadeDto = new ModalidadeDto(
                modalidade.getIdCartao(),
                modalidade.getNomeCartao(),
                modalidade.getTipoModalidade(),
                userDto
        );

        return modalidadeDto;
    }

}
