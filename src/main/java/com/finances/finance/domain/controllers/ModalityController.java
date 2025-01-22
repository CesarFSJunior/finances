package com.finances.finance.domain.controllers;


import com.finances.finance.domain.entities.modalidade.Modality;
import com.finances.finance.domain.entities.modalidade.ModalityCreateDto;
import com.finances.finance.domain.entities.modalidade.ModalityDto;
import com.finances.finance.domain.entities.user.User;
import com.finances.finance.infra.security.SecurityUtils;
import com.finances.finance.services.ModalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/paymentMethod")
public class ModalityController {

    @Autowired
    ModalityService modalityService;

    @Autowired
    private SecurityUtils securityUtils;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModalityDto> getById(@PathVariable UUID id) {

        User authenticatedUser = securityUtils.getAuthenticatedUser();
        ModalityDto modality = modalityService.getById(id);
        if (!modality.owner().id().equals(authenticatedUser.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(modality);

    }

    @PostMapping
    public ResponseEntity<ModalityDto> createmodality(@RequestBody ModalityCreateDto modality) {

        ModalityDto modalityDto = modalityService.createModality(modality);
        return ResponseEntity.ok(modalityDto);

    }

    @GetMapping
    public ResponseEntity<List<ModalityDto>> getAllMy() {

        List<ModalityDto> modalityDtos = modalityService.getAllMy();

        return ResponseEntity.ok(modalityDtos);

    }
}
