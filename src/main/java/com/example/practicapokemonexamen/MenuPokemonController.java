package com.example.practicapokemonexamen;

import com.example.practicapokemonexamen.data.Pokemon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MenuPokemonController implements PeleaPokemonInterface {

	private final int WIDTH = 300;

	@FXML
	public GridPane gridPanePokemons;

	private final ArrayList<ProgressBar> listVida = new ArrayList<>();

    public void initialize() {
    	int maxColumns = 3;
		int maxRows = 9;

		int currentColumn = 0;
		int currentRow = 0;
		int contadorPokemons = 0;
		gridPanePokemons.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		for (currentRow = 0; currentRow < maxRows; currentRow++) {
			for (currentColumn = 0; currentColumn < maxColumns; currentColumn++) {

				if (contadorPokemons >= Main.listaPokemon.getSize()){
					System.out.println("Hemos terminado de a�adir Pokemons en el n�mero  " + contadorPokemons);
					return;
				}

				BorderPane borderPane = new BorderPane();
	
				ImageViewPokemon pokemonImage = new ImageViewPokemon(Main.listaPokemon.getPokemon(contadorPokemons));

				EventHandler<MouseEvent> onClickListener = event -> {
					ImageViewPokemon imageViewPokemon = (ImageViewPokemon)event.getSource();
					System.out.println("Soy el Pokemon " + imageViewPokemon.pokemon.nombre);
					abrirLuchaPokemon(imageViewPokemon.pokemon);
				};
				pokemonImage.setOnMouseClicked(onClickListener);
				pokemonImage.setFitWidth(WIDTH);
				pokemonImage.setPreserveRatio(true);
				pokemonImage.setSmooth(true);
				borderPane.setCenter(pokemonImage);

				ProgressBar progressBarVida = new ProgressBar(Main.listaPokemon.getPokemon(contadorPokemons).puntosVida);
				progressBarVida.setPrefSize(WIDTH, 20);
				listVida.add(progressBarVida);
				borderPane.setTop(progressBarVida);
				
				
				Label labelNombrePokemon = new Label(Main.listaPokemon.getPokemon(contadorPokemons).nombre);
				labelNombrePokemon.setPrefWidth(WIDTH);
				labelNombrePokemon.setAlignment(Pos.CENTER);
				labelNombrePokemon.setTextFill(Color.WHITESMOKE);

				borderPane.setBottom(labelNombrePokemon);	
				borderPane.setPadding(new Insets(20, 20, 20, 20));

				
				gridPanePokemons.add(borderPane, currentColumn, currentRow);
				contadorPokemons++;
			}
		}
	}
    
    public void abrirLuchaPokemon(Pokemon pokemon){
		if (!pokemon.estaMuerto()) {
			System.out.println("Abriendo nueva ventana...");
			try {
				FXMLLoader loader = new FXMLLoader(MenuPokemonController.class.getResource("PeleaPokemon.fxml"));
				AnchorPane secondPane = loader.load();
				Stage secondStage = new Stage();
				secondStage.setScene(new Scene(secondPane, 750, 680));
				secondStage.show();
				PeleaPokemonController peleaPokemonController = loader.getController();
				peleaPokemonController.iniciarLucha(pokemon, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Alert alerta = new Alert(Alert.AlertType.ERROR);
		alerta.setContentText(pokemon.nombre + " está muerto");


	}

	@Override
	public void update() {
		for (int i = 0; i< listVida.size(); i++) {
			listVida.get(i).setProgress(Main.listaPokemon.getPokemon(i).getVidaPorcentual());
		}
	}
}
