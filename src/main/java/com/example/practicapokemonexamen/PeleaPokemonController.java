package com.example.practicapokemonexamen;


import com.example.practicapokemonexamen.data.Pokemon;
import com.example.practicapokemonexamen.data.PokemonUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;

public class PeleaPokemonController  {

	private Pokemon pokemonAliado;
	private Pokemon pokemonEnemigo;
	private Random random = new Random();

	@FXML
	private TextArea textAreaReporte;
	@FXML
	private ImageView imagePokemonAliado;
	@FXML
	private ImageView imagePokemonEnemigo;
	@FXML
	private Pane panelAtaques;
	@FXML
	private Pane panelOpciones;
	@FXML
	private ProgressBar progressBarPokemonAliado;
	@FXML
	private ProgressBar progressBarPokemonEnemigo;
	@FXML
	private Text labelNombrePokemonAliado;
	@FXML
	private Text labelNombrePokemonEnemigo;
	@FXML
	private Text labelNivelPokemonAliado;
	@FXML
	private Text labelNivelPokemonEnemigo;
	
	private final int CURACION_DEFECTO = 15;

	PeleaPokemonInterface callback = null;

	public void iniciarLucha(Pokemon pokemon, PeleaPokemonInterface callback) {
		pokemonAliado = pokemon;
		pokemonEnemigo = PokemonUtils.getPokemonAleatorio();
		this.callback = callback;
		mostrarAtaques(false);
		iniciarVistas();
	}
	
	private void iniciarVistas() {
		imagePokemonAliado.setImage(pokemonAliado.imagen);	
		labelNombrePokemonAliado.setText(pokemonAliado.nombre);
		labelNivelPokemonAliado.setText(String.valueOf(pokemonAliado.nivel));
		progressBarPokemonAliado.setProgress(pokemonAliado.getVidaPorcentual());
		
		imagePokemonEnemigo.setImage(pokemonEnemigo.imagen);	
		labelNombrePokemonEnemigo.setText(pokemonEnemigo.nombre);
		labelNivelPokemonEnemigo.setText(String.valueOf(pokemonEnemigo.nivel));
		progressBarPokemonEnemigo.setProgress(pokemonEnemigo.getVidaPorcentual());
	}
	
	private void actualizarVidaPokemonEnemigo() {
		progressBarPokemonEnemigo.setProgress(pokemonEnemigo.getVidaPorcentual());
	}
	
	private void actualizarVidaPokemonAliado() {
		progressBarPokemonAliado.setProgress(pokemonAliado.getVidaPorcentual());
	}
	
	@FXML
	private void ataqueMuyArriesgado() {
		atacar(random.nextInt(50));
	}
	
	@FXML
	private void ataqueArriesgado() {
		atacar(random.nextInt(15) + 10);
	}
	
	@FXML
	private void ataqueNormal() {
		atacar(20);
	}
	
	@FXML
	private void volver() {
		mostrarAtaques(false);
	}
	
	@FXML
	private void abrirAtaques() {
		mostrarAtaques(true);
	}
	
	@FXML
	private void curar() {
		anadirReporteDeBattala(pokemonAliado.nombre + " se ha curado en " + CURACION_DEFECTO + " puntos de vida.\n");
		pokemonAliado.curar(15);
		progressBarPokemonAliado.setProgress(pokemonAliado.getVidaPorcentual());
		callback.update();
	}
	
	@FXML
	private void mostrarVidaPokemonAliado(MouseEvent event) {
		System.out.println("mostrarVidaPokemonAliado");
		mostrarTexoDesdeEvento(event, String.valueOf(pokemonAliado.puntosVida));
	}
	
	@FXML
	private void ocultarVidaPokemonAliado(MouseEvent event) {
		System.out.println("ocultarVidaPokemonAliado");
		mostrarTexoDesdeEvento(event, "PS");
	}
	
	@FXML
	private void mostrarVidaPokemonEnemigo(MouseEvent event) {
		System.out.println("mostrarVidaPokemonEnemigo");
		mostrarTexoDesdeEvento(event, String.valueOf(pokemonEnemigo.puntosVida));
	}
	
	@FXML
	private void ocultarVidaPokemonEnemigo(MouseEvent event) {
		System.out.println("ocultarVidaPokemonEnemigo");
		mostrarTexoDesdeEvento(event, "PS");
	}
	
	
	private void mostrarTexoDesdeEvento(MouseEvent event, String texto) {
		Text puntosDeVida = (Text)event.getSource();
		puntosDeVida.setText(texto);
	}
	
	private void mostrarAtaques(boolean mostrar) {
		panelOpciones.setVisible(!mostrar);
		panelAtaques.setVisible(mostrar);
	}
	
	private void atacar(int dano) {
		anadirReporteDeBattala(pokemonAliado.nombre + " ha atacado quitando " + dano + " puntos de vida.\n");
		pokemonEnemigo.recibirDano(dano);
		actualizarVidaPokemonEnemigo();

		if (pokemonEnemigo.estaMuerto()) {
			anadirReporteDeBattala(pokemonEnemigo.nombre + " ha sido derrotado.\n");
			anadirReporteDeBattala("Has ganado.\n");
			mostrarAlerta("Has ganado", "El Pokemon enemigo ha sido derrotado. Que quieres hacer ahora?", imagePokemonAliado);
			return;
		}
		anadirReporteDeBattala("A " + pokemonEnemigo.nombre + " le quedan " + pokemonEnemigo.puntosVida + " puntos de vida.\n");

		int danoContraataque = random.nextInt(24) + 1;
		anadirReporteDeBattala(pokemonEnemigo.nombre + " ha contraatacado quitando " + danoContraataque + " puntos de vida.\n");
		pokemonAliado.recibirDano(danoContraataque);
		actualizarVidaPokemonAliado();
		callback.update();

		if (pokemonAliado.estaMuerto()) {
			anadirReporteDeBattala(pokemonAliado.nombre + " ha sido derrotado.\n");
			anadirReporteDeBattala("Has perdido.\n");
			mostrarAlerta("Has perdido", "Tu Pokemon ha sido derrotado. Que quieres hacer ahora?", imagePokemonAliado);
			return;
		}
		anadirReporteDeBattala("A " + pokemonAliado.nombre + " le quedan " + pokemonEnemigo.puntosVida + " puntos de vida.\n");

	}
	
	private void mostrarAlerta(String titulo, String content, ImageView imagenPokemon) {
		ButtonType volver = new ButtonType("Volver al menu", ButtonData.OK_DONE);
		ButtonType salir = new ButtonType("Salir", ButtonData.CANCEL_CLOSE);
		Alert alerta = new Alert(AlertType.NONE, content, salir, volver);
		alerta.setGraphic(imagenPokemon);

		alerta.setTitle(titulo);
		
		Optional<ButtonType> resultado = alerta.showAndWait();
		if(resultado.isEmpty()) {
			System.out.println("Dialogo cerrado con la X");
			Stage stage = (Stage) textAreaReporte.getScene().getWindow();
			stage.close();
		} else if(resultado.get() == volver) {
			System.out.println("Resultado = OK = Volver");
			Stage stage = (Stage) textAreaReporte.getScene().getWindow();
			stage.close();
		} else if (resultado.get() == salir) {
			System.out.println("Resultado = CANCEL = Salir");
			System.exit(0);
		}
	}
	
	
	
	private void anadirReporteDeBattala(String informe) {
		textAreaReporte.appendText(informe);
	}


}
