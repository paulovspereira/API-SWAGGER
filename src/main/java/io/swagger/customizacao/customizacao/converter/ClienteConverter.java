package io.swagger.customizacao.customizacao.converter;

import java.util.ArrayList;
import java.util.Arrays;

import io.swagger.customizacao.customizacao.entity.ClienteEntity;
import io.swagger.customizacao.customizacao.entity.ContaEntity;
import io.swagger.model.Cliente;
import io.swagger.model.Conta;

//Conversão de Dados
public class ClienteConverter {

	private static final int AGENCIA = 8756;
	
	//Metodo que convere toCliente(Json) para toClientEntidada(Banco de dados)
	public static ClienteEntity toClienteEntity(Cliente cliente, String tokenBasicAuth) {
    	
		ClienteEntity clienteEntity = new ClienteEntity();
    	
		clienteEntity.setTitular(cliente.getTitular());
		clienteEntity.setCpf(cliente.getCpf());
		clienteEntity.setPassword(tokenBasicAuth);

		ContaEntity contaEntity = new ContaEntity();
		contaEntity.setAgencia(AGENCIA);
		contaEntity.setDigito(0);
		contaEntity.setSaldo(0d);
		contaEntity.setCliente(clienteEntity);
		
		clienteEntity.setContas(Arrays.asList(contaEntity));

		return clienteEntity;
	}
	
	//Metodo que convere toClientEntidada(Banco de dados) para toCliente(Json)

	public static Cliente toCliente(ClienteEntity clienteEntity) {
		
		Cliente cliente = new Cliente();
		cliente.setCpf(clienteEntity.getCpf());
		cliente.setId(clienteEntity.getId());
		cliente.setTitular(clienteEntity.getTitular());
		cliente.setContas(new ArrayList<Conta>());
		
		if(clienteEntity.getContas() != null) {
			for(ContaEntity contaE: clienteEntity.getContas()) {
				Conta conta = new Conta();
				conta.setAgencia(contaE.getAgencia());
				conta.setNumero(contaE.getNumero());
				conta.setDigito(contaE.getDigito());
				conta.setSaldo(contaE.getSaldo());
				cliente.getContas().add(conta);
			}
		}
		
		return cliente;
	}
}
