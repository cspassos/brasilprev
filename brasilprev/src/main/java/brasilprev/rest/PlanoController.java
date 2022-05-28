package brasilprev.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import brasilprev.dto.PlanoDTO;
import brasilprev.dto.ResgateDTO;
import brasilprev.model.entity.Plano;
import brasilprev.service.PlanoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Plano")
@RestController
@RequestMapping("/plano")
public class PlanoController {
	
	@Autowired
	private PlanoService planoService;
	
	@ApiOperation(value = "Salvar Plano")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Plano salvar(@RequestBody PlanoDTO plano) {
		return planoService.salvar(plano);
	}
	
	@ApiOperation(value = "Contratar Plano/Aporte")
	@PostMapping("contratar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void contratarPlano(@RequestBody PlanoDTO plano) {
		planoService.contratarPlano(plano);
	}
	
	@ApiOperation(value = "Resgatar Plano")
	@PostMapping("resgatar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void resgatePlano(@RequestBody ResgateDTO resgate) {
		planoService.resgatePlano(resgate);
	}
	
	@ApiOperation(value = "Aporte Extra")
	@PostMapping("aporte-extra")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void aporteExtra(@RequestBody PlanoDTO plano) {
		planoService.aporteExtra(plano);
	}
	
}
