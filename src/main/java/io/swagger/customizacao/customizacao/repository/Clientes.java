package io.swagger.customizacao.customizacao.repository;

import org.springframework.data.repository.CrudRepository;//Cruad é a classe já tem os metodos predefino como excluir, editar e buscar

import io.swagger.customizacao.customizacao.entity.ClienteEntity;

public interface Clientes extends CrudRepository<ClienteEntity, Long> {

	ClienteEntity findByCpfAndPassword(String cpf, String password); //Vai busca o cliente por cpf e password
}