package com.finances.finance.repositories;

import com.finances.finance.domain.entities.modalidade.Modality;
import com.finances.finance.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ModalityRepository extends JpaRepository<Modality, UUID> {

    @Query(
            "SELECT \n" +
            "M \n" +
            "FROM Modality M \n" +
            "WHERE M.owner = :ownerId"
    )
    List<Modality> findAllByOwner(User ownerId);

}
