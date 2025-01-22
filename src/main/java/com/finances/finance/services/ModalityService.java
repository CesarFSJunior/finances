package com.finances.finance.services;

import com.finances.finance.domain.entities.modalidade.Modality;
import com.finances.finance.domain.entities.modalidade.ModalityCreateDto;
import com.finances.finance.domain.entities.modalidade.ModalityDto;
import com.finances.finance.domain.entities.user.User;
import com.finances.finance.domain.mappers.ModalityMapper;
import com.finances.finance.errors.ModalityCreationError;
import com.finances.finance.errors.PaymentMethodNotFindError;
import com.finances.finance.infra.security.SecurityUtils;
import com.finances.finance.repositories.ModalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModalityService {

    @Autowired
    private ModalityRepository modalityRepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private ModalityMapper ModalityMapper;

    public ModalityDto getById(UUID id) {

        Optional<Modality> optionalModality = modalityRepository.findById(id);

        if(optionalModality.isEmpty()) {
            throw new PaymentMethodNotFindError("Metodo de pagamento não encontrado");
        }

        Modality modality = optionalModality.get();

        return ModalityMapper.modalityToDto(modality);

    }

    public ModalityDto createModality(ModalityCreateDto modalityCreateDto) {

        User owner = securityUtils.getAuthenticatedUser();

        List<ModalityDto> myModalitys = this.getAllMy();

        myModalitys = myModalitys.stream().filter(x -> x.nomeCartao().equals(modalityCreateDto.nomeCartao())).toList();

        if (!myModalitys.isEmpty()) {
            throw new ModalityCreationError("Already exists a modality with this name");
        }

        Modality modality = ModalityMapper.modalityCreateDtoToModality(modalityCreateDto);

        modality.setCreated_at(new Date());
        modality.setOwner(owner);

        modality = modalityRepository.save(modality);

        return ModalityMapper.modalityToDto(modality);

    }

    public List<ModalityDto> getAllMy() {
        User owner = securityUtils.getAuthenticatedUser();

        List<Modality> modalities = modalityRepository.findAllByOwner(owner);

        return modalities.stream().map(x -> ModalityMapper.modalityToDto(x)).toList();

    }

}
