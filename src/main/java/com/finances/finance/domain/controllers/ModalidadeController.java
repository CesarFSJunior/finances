package com.finances.finance.domain.controllers;

import com.finances.finance.domain.entities.modalidade.Modalidade;
import com.finances.finance.domain.entities.modalidade.ModalidadeDto;
import com.finances.finance.services.ModalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/paymentMethod")
public class ModalidadeController {

    @Autowired
    ModalidadeService modalidadeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModalidadeDto> getById(@PathVariable UUID id) {

        ModalidadeDto modalidade = modalidadeService.getById(id);

        return ResponseEntity.ok(modalidade);

    }

    @PostMapping
    public ResponseEntity<ModalidadeDto> createModalidade(@RequestBody Modalidade modalidade) {

        ModalidadeDto modalidadeDto = modalidadeService.createModalidade(modalidade);
        return ResponseEntity.ok(modalidadeDto);

    }

    @GetMapping
    public ResponseEntity<List<ModalidadeDto>> getAllMy() {

        List<ModalidadeDto> modalidadeDtos = modalidadeService.getAllMy();

        return ResponseEntity.ok(modalidadeDtos);

    }
}
