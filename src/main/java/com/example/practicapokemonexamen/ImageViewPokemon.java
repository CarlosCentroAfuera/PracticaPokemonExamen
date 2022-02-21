package com.example.practicapokemonexamen;

import com.example.practicapokemonexamen.data.Pokemon;
import javafx.scene.image.ImageView;

public class ImageViewPokemon extends ImageView{
	
	Pokemon pokemon;
	
	public ImageViewPokemon(Pokemon pokemon){
		super();
		setImage(pokemon.imagen);
		this.pokemon = pokemon;
	}
	
}
