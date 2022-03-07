package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Comida;
import com.example.demo.service.ComidaService;

@Controller
public class comidaController {
	@Autowired
	private ComidaService comidaJPA;

	@PostMapping("/crearComida")
	public ResponseEntity postCrearOferta(@RequestBody Map<String, String> json) {
	
		Comida comida = comidaJPA.crearComida(new Comida(json.get("nombre"), json.get("pais"),
		json.get("hiperenlace"), json.get("descripcion")));
		ResponseEntity<Object> com = new ResponseEntity<Object>(comida, HttpStatus.OK);
		return com;
	}
	
    @ResponseBody
	@GetMapping("/borrar/{id}")
	public void borrarProducto(@PathVariable long id) {
		comidaJPA.borrar(id);
	}
    
	@ResponseBody
	@PostMapping("/editar/comida/{id}")
	public ResponseEntity<Comida> editarOferta(@RequestBody Map<String, String> json, @PathVariable("id") long id) {
		Comida comida = comidaJPA.editarComida(id, new Comida (id, json.get("pais")));
		ResponseEntity<Comida> resp = new ResponseEntity<Comida>(comida, HttpStatus.OK);
		return resp;
    }
    
    
    
	
}
