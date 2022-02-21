package com.example.practicapokemonexamen.data;

import javafx.scene.image.Image;


public class Pokemon {
	public String nombre;
	public int nivel;
	public int puntosVida;
	public Image imagen;
	
	private final int MAX_PUNTOS_VIDA = 100;
	private final int MIN_PUNTOS_VIDA = 0;


	public Pokemon(String nombre, int nivel, int puntosVida, Image imagen) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.puntosVida = puntosVida;
		this.imagen = imagen;
	}


	public void recibirDano(int dano) {
		this.puntosVida = this.puntosVida - dano;
		if (this.puntosVida < MIN_PUNTOS_VIDA) {
			this.puntosVida = MIN_PUNTOS_VIDA;
		}
	}
	public void curar(int curacion) {
		this.puntosVida = this.puntosVida + curacion;
		if (this.puntosVida > MAX_PUNTOS_VIDA) {
			this.puntosVida = MAX_PUNTOS_VIDA;
		}
	}

	public boolean estaMuerto() {
		return this.puntosVida == 0;
	}

	public double getVidaPorcentual() {
		return puntosVida / (MAX_PUNTOS_VIDA * 1.0);
	}
}
