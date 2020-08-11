package io.swagger.customizacao.customizacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.swagger.customizacao.customizacao.converter.ClienteConverter;
import io.swagger.customizacao.customizacao.entity.ClienteEntity;
import io.swagger.customizacao.customizacao.repository.Clientes;
import io.swagger.customizacao.customizacao.util.AuthUtil;
import io.swagger.customizacao.customizacao.util.RespostasUtil;
import io.swagger.model.Cliente;

@Service
public class ClienteService {

	public static final String MENSAGEM_DADOS_INVALIDOS = "Titular, Cpf ou Password nao foi informado.";
	
	public static final String MENSAGEM_FALHA_CADASTRAR_CLIENTE = "Falha ao tentar cadastrar o cliente";
	
	public static final String FALHA_AO_TENTAR_CADASTRAR_UM_CLIENTE = "Falha ao tentar cadastrar um cliente.";
	
	@Autowired
    private Clientes repository;
	
    @Autowired
    private RespostasUtil respostasUtil;

	public ResponseEntity<Cliente> salva(Cliente cliente_) {

		if (ehClienteValidoParaCadastro(cliente_)) { // Verifica se o cliente é valido
			return respostasUtil.getRequisicaoInvalidaCliente(MENSAGEM_DADOS_INVALIDOS);
		}

		return new ResponseEntity<Cliente>(cadastraNovoCliente(cliente_), HttpStatus.CREATED);// Vai chamar cadastro NovoCliente
		
	}
    
	public Cliente cadastraNovoCliente(Cliente cliente_) {
		
		//Faz uma conversão para aceita na tabela de dados
		ClienteEntity clienteEntity = ClienteConverter.toClienteEntity(cliente_, AuthUtil.getBasicAuth(cliente_.getCpf(), cliente_.getSenha()));
		
		//Manda para repository para ser salva
		clienteEntity = repository.save(clienteEntity);
			
		return ClienteConverter.toCliente(clienteEntity); //Converte novamento para Cliente que vai chamado na api
	}
	
	public ClienteEntity getClienteByAuthorization(String authorization) {
		String[] basicAuth = AuthUtil.getBasicAuth(authorization);
		String cpf = basicAuth[0];
		String password = basicAuth[1];
		
		ClienteEntity clienteEntity = repository.findByCpfAndPassword(cpf, password);
		return clienteEntity;
	}
	
	public boolean ehClienteValidoParaCadastro(Cliente cliente_) {
		return StringUtils.isEmpty(cliente_.getTitular()) || StringUtils.isEmpty(cliente_.getCpf()) || StringUtils.isEmpty(cliente_.getSenha());
	}
}
