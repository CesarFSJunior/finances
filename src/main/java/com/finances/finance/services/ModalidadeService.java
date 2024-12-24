package com.finances.finance.services;

import com.finances.finance.domain.entities.modalidade.Modalidade;
import com.finances.finance.domain.entities.modalidade.ModalidadeDto;
import com.finances.finance.domain.entities.user.User;
import com.finances.finance.domain.entities.user.UserDto;
import com.finances.finance.domain.mappers.ModalidadeMapper;
import com.finances.finance.errors.PaymentMethodNotFindError;
import com.finances.finance.errors.UserNotFindError;
import com.finances.finance.infra.security.SecurityUtils;
import com.finances.finance.repositories.ModalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModalidadeService {

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private ModalidadeMapper modalidadeMapper;

    public ModalidadeDto getById(UUID id) {

        Optional<Modalidade> optionalModalidade = modalidadeRepository.findById(id);

        if(optionalModalidade.isEmpty()) {
            throw new PaymentMethodNotFindError("Metodo de pagamento não encontrado");
        }

        Modalidade modalidade = optionalModalidade.get();
        ModalidadeDto modalidadeDto = modalidadeMapper.modalidateToDto(modalidade);

        return modalidadeDto;

    }

    public ModalidadeDto createModalidade(Modalidade modalidade) {

        // TODO: proteção contra multiplos cartões com o mesmo nome por usuario

        User owner = securityUtils.getAuthenticatedUser();

        modalidade.setCreated_at(new Date());
        modalidade.setOwner(owner);

        modalidade = modalidadeRepository.save(modalidade);

        ModalidadeDto modalidadeDto = modalidadeMapper.modalidateToDto(modalidade);

        return modalidadeDto;

    }

    public List<ModalidadeDto> getAllMy() {
        User owner = securityUtils.getAuthenticatedUser();

        List<Modalidade> modalidades = modalidadeRepository.findAllByOwner(owner);

        List<ModalidadeDto> modalidadeDtos = modalidades.stream().map(x -> modalidadeMapper.modalidateToDto(x)).toList();

        return modalidadeDtos;

    }

}
