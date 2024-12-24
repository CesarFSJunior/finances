package com.finances.finance.domain.entities.modalidade;

import com.finances.finance.domain.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "MODALIDADE_PAGAMENTO")
public class Modalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    UUID idCartao;
    @Column(name = "NOME_MODALIDADE")
    String nomeCartao;
    @Column(name = "TIPO_MODALIDADE")
    TipoModalidade tipoModalidade;
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    User owner;
    Date created_at;

}
