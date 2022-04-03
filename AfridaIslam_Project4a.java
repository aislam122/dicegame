package AfridaIslam_Project4a;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AfridaIslam_Project4a extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Instantiate object
		// Die label this prints the values of the dies on the scene
		Label lDie1 = new Label("5");
		Label lDie2 = new Label("5");
		Label lDie3 = new Label("7");
		Label lDie4 = new Label("2");

		// The second roll dies
		Label lDie5 = new Label("3");
		Label lDie6 = new Label("2");
		Label lDie7 = new Label("2");
		Label lDie8 = new Label("8");

		// The label for the game title and other important labels

		Label lGame = new Label(" Welcome to the game of 4 dice draw poker");
		TextField tName = new TextField();
		Label lName = new Label(" Enter Name");
		Label lDice = new Label("Dice");
		Label lDraw = new Label("Draw");
		Label lOutcome = new Label(" Pair");
		Label lDice2 = new Label ( "Dice");
		Label lOutcome2 = new Label ( "Pair");
		// setting the layout of the dice and draw label
		
		
		// Initiating the radio buttons
		RadioButton r1 = new RadioButton("");
		RadioButton r2 = new RadioButton("");
		RadioButton r3 = new RadioButton("");
		RadioButton r4 = new RadioButton("");

		// the button label message
		Button button1 = new Button("Click for 1st roll");
		Button button2 = new Button("Play Again");
		Button button3 = new Button("Click for the 2nd roll");

		HBox hbox = new HBox(r1, r2, r3, r4);
		hbox.setSpacing(15);
		hbox.setLayoutX(120);
		hbox.setLayoutY(250);
		// Testing I tried using several method to change the color before using the correct one
		// r1.setTextFill(Color.LAVENDER);
		// r1.setTextFill(Color.AQUAMARINE);
		// r3.setTextFill(Color.LIGHTPINK);
		// rect3.setFill(Color.LIGHTSTEELBLUE);

		hbox.setAlignment(Pos.CENTER);

		// hbox.getChildren().add(rect);

		// pane.setPadding(new Insets(20, 50, 10, 50));

		// Instantiate our Pane object and set object properties
		Pane pane = new Pane();
		pane.getChildren().addAll(lGame, tName, lName, button1, button2, lDie1, lDie2, lDie3, lDie4, hbox, lDice, lDraw,
				lOutcome, lDie5, lDie6, lDie7, lDie8, button3, lDice2,lOutcome2);

		// Setting the style and the colors of the buttons
		button1.setStyle("-fx-base:Lightpink");
		button2.setStyle("-fx-base:Lightpink");
		button3.setStyle("-fx-base:Lightpink");
		// Changing the color of the radio buttons
		r1.setStyle("-fx-base:Papayawhip ");
		r2.setStyle("-fx-base:AQUAMARINE");
		r3.setStyle("-fx-base:Lightpink");
		r4.setStyle("-fx-base:LIGHTSTEELBLUE");
		// the layout of the important titles and labels
		lGame.setLayoutX(100);lGame.setLayoutY(10);
		tName.setLayoutX(120);tName.setLayoutY(50);
		lName.setLayoutX(30);lName.setLayoutY(55);
		lOutcome.setLayoutX(200);lOutcome.setLayoutY(190);
		lDice.setLayoutX(50);lDice.setLayoutY(190);
		lDraw.setLayoutX(50);lDraw.setLayoutY(250);
		lDice2.setLayoutX(50);lDice2.setLayoutY(350);
		lOutcome2.setLayoutX(200);lOutcome2.setLayoutY(350);
		// The layout  and the position of the dies
		lDie1.setLayoutX(120);lDie1.setLayoutY(190);
		lDie2.setLayoutX(140);lDie2.setLayoutY(190);
		lDie3.setLayoutX(160);lDie3.setLayoutY(190);
		lDie4.setLayoutX(180);lDie4.setLayoutY(190);
		lDie5.setLayoutX(120);lDie5.setLayoutY(350);
		lDie6.setLayoutX(140);lDie6.setLayoutY(350);
		lDie7.setLayoutX(160);lDie7.setLayoutY(350);
		lDie8.setLayoutX(180);lDie8.setLayoutY(350);
		
		
		// the layout and the color of the buttons
		button1.setLayoutX(120);button1.setLayoutY(120);
		button2.setLayoutX(130);button2.setLayoutY(410);
		button3.setLayoutX(120);button3.setLayoutY(300);
		// Setting the text color to white
		button2.setTextFill(Color.WHITE);
		button3.setTextFill(Color.WHITE);
		button1.setTextFill(Color.WHITE);

		// setting the fonts of the texts
		Font font = Font.font("Times New Roman", FontWeight.BOLD, 15);
		Font font2 = Font.font( "Times New Roman", FontWeight.BOLD,18);
		button1.setFont(font);
		button2.setFont(font);
		button3.setFont(font);
		lGame.setFont(font2);
		

		// changing the background color to lavender
		pane.setStyle("-fx-background:Lavender");

		Scene scene = new Scene(pane, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
