package io.swagger.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.swagger.customizacao.customizacao.service.ClienteService;
import io.swagger.customizacao.customizacao.util.RespostasUtil;
import io.swagger.model.Cliente;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-09T20:21:55.904Z")

@Controller
public class ClienteApiController implements ClienteApi {

	@Autowired //Enjetar a classe não precisa ficando dando new
	private ClienteService clienteService;
	
	@Autowired
    private RespostasUtil respostaUtil;
	
	public ResponseEntity<Cliente> cadastraCliente(@ApiParam(value = "") @Valid @RequestBody Cliente cliente_) {
		
		try {
			return clienteService.salva(cliente_);// Vai chamar o service
			
		} catch (Exception e) {
			return respostaUtil.getErroInternoCliente(ClienteService.FALHA_AO_TENTAR_CADASTRAR_UM_CLIENTE);
		}
		
	}

}
