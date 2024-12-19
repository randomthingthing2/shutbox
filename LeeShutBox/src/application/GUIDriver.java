/**
 * Trinity Lee
 * create a java effect of a the dice game
 * 14/19/24
 */
package application;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	Die d1 = new Die(); // Ensure Die class is implemented

	@Override
	public void start(Stage stage) throws Exception {
		VBox vbox = new VBox(10);

		// Create and display the title
		Label title = new Label("Shut The Box");
		vbox.getChildren().add(title);

		HBox tileBox = new HBox(10);

		Button[] tileBtns = new Button[9];
		Tile[] tiles = new Tile[9];

		for (int i = 0; i < tileBtns.length; i++) {
			tileBtns[i] = new Button(String.valueOf(i + 1));
			tiles[i] = new Tile(i + 1); // Ensure Tile class is implemented
			tileBtns[i].setStyle("-fx-background-color: lightgray;");

			// Toggle button color on click
			tileBtns[i].setOnAction(e -> {
				Button clickedButton = (Button) e.getSource();
				String currentStyle = clickedButton.getStyle();
				if (currentStyle.contains("lightgray")) {
					clickedButton.setStyle("-fx-background-color: silver;");
				} else if (currentStyle.contains("silver")) {
					clickedButton.setStyle("-fx-background-color: lightgray;");
				}
			});

			tileBox.getChildren().add(tileBtns[i]);
		}

		tileBox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(tileBox);

		Button btnRoll = new Button("ROLL DICE");
		Button twobtnRoll = new Button("ROLL Two DICE");
		Button lockinbtn = new Button("Lock In");
		Button donebtn = new Button("Done");

		Label result1 = new Label("Result: ");
		Label resultDice = new Label("0");

		HBox rollBox = new HBox(20); // Horizontal box for buttons and result
		rollBox.setAlignment(Pos.CENTER);
		rollBox.getChildren().addAll(btnRoll, twobtnRoll, lockinbtn, donebtn, result1, resultDice);

		vbox.getChildren().add(rollBox);
		vbox.setAlignment(Pos.CENTER);

		btnRoll.setDisable(true);

		btnRoll.setOnAction(e -> {
			resultDice.setText(String.valueOf(d1.roll()));
			lockinbtn.setDisable(false);
			btnRoll.setDisable(true);
		});

		twobtnRoll.setOnAction(e -> {
			twobtnRoll.setText("ROLL two DICE");
			result1.setText("result");

			if (twobtnRoll.getText() == "ROLL two DICE") {
				int diceTotal = d1.roll() + d1.roll();
				twobtnRoll.setDisable(true);
				resultDice.setText(String.valueOf(diceTotal));
				lockinbtn.setDisable(false);

			}
		});
		
		donebtn.setOnAction(e -> {
			int score = 0;

			for (int i = 0; i < tileBtns.length; i++) {
				if (tileBtns[i].getStyle().contains("lightgray")) {
					score += Integer.valueOf(tileBtns[i].getText());
				}

			}

			resultDice.setText(String.valueOf(score));
			result1.setText("Score");

			if (score == 0) {
				result1.setText("you win");
			}
			result1.setText("score");
			if (result1.getText() == "score" || result1.getText() == "you win") {
				for (int i = 0; i < tileBtns.length; i++) {
					if (tileBtns[i].getStyle().contains("darkslategrey")) {
						tileBtns[i].setStyle("-fx-background-color: lightgray;");
						twobtnRoll.setText("Play again");
						twobtnRoll.setDisable(false);
						btnRoll.setDisable(true);

					}
				}
			}

		});

		lockinbtn.setOnAction(e -> {
			int total = 0;

			for (int i = 0; i < tileBtns.length; i++) {
				if (tileBtns[i].getStyle().contains("silver")) {
					total += Integer.valueOf(tileBtns[i].getText());
				}

			}

			if (total == Integer.valueOf(resultDice.getText())) {
				for (int i = 0; i < tileBtns.length; i++) {
					if (tileBtns[i].getStyle().contains("silver")) {
						tileBtns[i].setStyle("-fx-background-color: darkslategrey;");
						lockinbtn.setDisable(true);
						twobtnRoll.setDisable(false);
						btnRoll.setDisable(true);

					}
				}
			} else {
				for (int i = 0; i < tileBtns.length; i++) {

					if (tileBtns[i].getStyle().contains("silver")) {
						tileBtns[i].setStyle("-fx-background-color: lightgray;");
					}
				}
			}
			if (tileBtns[6].getStyle().contains("darkslategrey") && tileBtns[7].getStyle().contains("darkslategrey")
					&& tileBtns[8].getStyle().contains("darkslategrey")) {
				twobtnRoll.setDisable(true);
				btnRoll.setDisable(false);
				lockinbtn.setDisable(true);
			}
		});
	//size of playable screen
		Scene scene = new Scene(vbox, 500, 200);
		stage.setScene(scene);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}