package com.example.practicapokemonexamen.data;

import java.util.Random;

import javafx.scene.image.Image;

public class PokemonUtils {

	public static Pokemon getPokemonAleatorio() {
		Random random = new Random();
		String nombre = "Pokemon Salvaje " + random.nextInt(100);
		Image imagen = ImageManager.getImagesById(random.nextInt(7) + 1);
		return new Pokemon(nombre, random.nextInt(99), 100, imagen);
	}
}
