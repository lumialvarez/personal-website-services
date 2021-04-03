package com.lmalvarez.services.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmalvarez.services.conocimiento.Conocimiento;
import com.lmalvarez.services.conocimiento.ConocimientoService;

@RestController
@RequestMapping(path = "api/ext/v1/conocimiento")
public class ConocimientoExtController {
	@Autowired
	private ConocimientoService conocimientoService;
	
	@GetMapping
	public List<Conocimiento> getConocimientos() {
		return conocimientoService.getConocimientos();
	}

}
