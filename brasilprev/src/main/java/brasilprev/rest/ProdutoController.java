package brasilprev.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import brasilprev.dto.ProdutoDTO;
import brasilprev.model.entity.Produto;
import brasilprev.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@ApiOperation(value = "Salvar Produto")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Produto salvar(@RequestBody ProdutoDTO produto) {
		return produtoService.salvar(produto);
	}
	
}
