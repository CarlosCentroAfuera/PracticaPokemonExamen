package com.example.practicapokemonexamen.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import javafx.scene.image.Image;

public class ListaPokemon {

	private final ArrayList<Pokemon> listaPokemon = new ArrayList<>();

	public ListaPokemon() {
		try (Stream<Path> fileList = Files.walk(Paths.get("images/"))){
			fileList.forEach((file)-> {
				if (file.toFile().isFile()) {
					System.out.println(file.getFileName());
					Image image = new Image(file.toUri().toString());
					Pokemon pokemon = new Pokemon(file.getFileName().toString().substring(0, 2), 100, 100, image);
					listaPokemon.add(pokemon);

				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Pokemon getPokemon(int position) {
		return listaPokemon.get(position);
	}

	public int getSize() {
		return listaPokemon.size();
	}

}
