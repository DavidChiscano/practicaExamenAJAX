package com.example.demo.service;

import com.example.demo.entity.Comida;


public interface ComidaService {
	Comida crearComida(Comida comida);
	void borrar(Long id);
	Comida editarComida(Long id, Comida comida);
}
