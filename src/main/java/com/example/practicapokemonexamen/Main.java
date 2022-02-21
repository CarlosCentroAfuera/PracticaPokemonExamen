package com.example.practicapokemonexamen;

import java.io.IOException;

import com.example.practicapokemonexamen.data.ListaPokemon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static ListaPokemon listaPokemon =  new ListaPokemon();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			initMenuPokemon(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void initMenuPokemon(Stage primaryStage) throws IOException {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPokemon.fxml"));
		BorderPane root = loader.load();

		Scene scene = new Scene(root,1050,900);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
