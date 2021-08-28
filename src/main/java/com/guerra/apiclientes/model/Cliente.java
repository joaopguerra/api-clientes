package com.guerra.apiclientes.model;

import com.guerra.apiclientes.model.enums.RegimeTributario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "regime_tributario")
    private RegimeTributario regimeTributario;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private boolean ativo;
}
