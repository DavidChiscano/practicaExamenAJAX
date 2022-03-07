package com.example.demo.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Comida;
import com.example.demo.repository.ComidaRepository;

@Controller
public class indexController {

	@Autowired
	ComidaRepository comidaJPA;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value="/mostrar")
	public List<Comida> obtenerComidas(){
		return comidaJPA.findAll();
	}
	
	@GetMapping("/index")
	public String getIndex(Model modelo){
		return "index";
	}
	
}
