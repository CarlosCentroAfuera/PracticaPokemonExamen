package com.example.practicapokemonexamen.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import javafx.scene.image.Image;

public class ImageManager {

	public static Image getImagesById(int id){
		String url = "images/P" + id + ".png";
		System.out.println("Buscando la imagen del Pokemon en la ruta\n" + url);
		
		File file = new File(url);
		return new Image(file.toURI().toString());
	}
}
