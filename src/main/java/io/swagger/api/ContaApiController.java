package io.swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import io.swagger.annotations.ApiParam;
import io.swagger.customizacao.customizacao.service.ContaService;
import io.swagger.customizacao.customizacao.util.RespostasUtil;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-09T20:21:55.904Z")

@Controller
public class ContaApiController implements ContaApi {

	@Autowired
	private ContaService contaService;

	@Autowired
	private RespostasUtil respostasUtil;

	public ResponseEntity<Double> consultaSaldo(
			@ApiParam(value = "", required = true) @PathVariable("agencia") Integer agencia,
			@ApiParam(value = "", required = true) @PathVariable("numero") Long numero,
			@ApiParam(value = "", required = true) @PathVariable("digito") Integer digito,
			@ApiParam(value = "", required = true) @RequestHeader(value = "Authorization", required = true) String authorization) {

		try {
			return contaService.consultaSaldo(authorization, agencia, numero, digito);
		} catch (Exception e) {
			return respostasUtil.getErroInternoDouble(respostasUtil.MENSAGEM_FALHA_AO_SALVAR_TRANSACAO);
		}
	}

}
