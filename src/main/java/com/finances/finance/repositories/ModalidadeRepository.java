package com.finances.finance.repositories;

import com.finances.finance.domain.entities.modalidade.Modalidade;
import com.finances.finance.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ModalidadeRepository extends JpaRepository<Modalidade, UUID> {

    @Query(
            "SELECT \n" +
            "M \n" +
            "FROM Modalidade M \n" +
            "WHERE M.owner = :ownerId"
    )
    List<Modalidade> findAllByOwner(User ownerId);

}
