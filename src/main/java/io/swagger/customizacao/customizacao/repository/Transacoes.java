package io.swagger.customizacao.customizacao.repository;

import org.springframework.data.repository.CrudRepository;

import io.swagger.customizacao.customizacao.entity.TransacaoEntity;


public interface Transacoes extends CrudRepository<TransacaoEntity, Long> {
 
}
