import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class GUI {

    private List<Match> matches = PremierLeagueManager.matchList;
    private List<FootballClub> clubList = PremierLeagueManager.leagueList;


    public Stage getStage() {

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Premier League");
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #B0C4DE;");
        Scene scene = new Scene(pane, 1250, 700);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());   //CSS embedded to the GUI
        Platform.setImplicitExit(false);

        pane.setPadding(new Insets(20, 20, 20, 40));

        TableView tableView = new TableView();
        Label l1 = new Label("Football Premier League Standings");
        l1.setId("l1");


        TableColumn<FootballClub, String> column1 = new TableColumn<>("Club");
        column1.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.17));   //Set table column width
        column1.setResizable(false);   //Set column as non resizable


        TableColumn<FootballClub, String> column2 = new TableColumn<>("Location");
        column2.setCellValueFactory(new PropertyValueFactory<>("location"));
        column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.13));
        column2.setResizable(false);


        TableColumn<FootballClub, String> column3 = new TableColumn<>("Wins");
        column3.setCellValueFactory(new PropertyValueFactory<>("wins"));
        column3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.09));
        column3.setResizable(false);

        TableColumn<FootballClub, String> column4 = new TableColumn<>("Draws");
        column4.setCellValueFactory(new PropertyValueFactory<>("draws"));
        column4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.09));
        column4.setResizable(false);

        TableColumn<FootballClub, String> column5 = new TableColumn<>("Losses");
        column5.setCellValueFactory(new PropertyValueFactory<>("losses"));
        column5.prefWidthProperty().bind(tableView.widthProperty().multiply(0.09));
        column5.setResizable(false);

        TableColumn<FootballClub, String> column6 = new TableColumn<>("Received Goals");
        column6.setCellValueFactory(new PropertyValueFactory<>("receivedGoals"));
        makeHeaderWrappable(column6);
        column6.setPrefWidth(90);
        column6.setResizable(false);


        TableColumn<FootballClub, String> column7 = new TableColumn<>("Scored Goals");
        column7.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));
        makeHeaderWrappable(column7);
        column7.setPrefWidth(90);
        column7.setResizable(false);

        TableColumn<FootballClub, String> column8 = new TableColumn<>("Points");
        column8.setCellValueFactory(new PropertyValueFactory<>("points"));
        column8.setPrefWidth(75);
        column8.setResizable(false);


        TableColumn<FootballClub, String> column9 = new TableColumn<>("Matches Played");
        column9.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));
        makeHeaderWrappable(column9);
        column9.setPrefWidth(90);
        column9.setResizable(false);


        HBox indBox = new HBox();
        indBox.setId("hbox-custom");
        indBox.getChildren().addAll(l1);
        indBox.setAlignment(Pos.CENTER);
        pane.setTop(indBox);


        pane.setCenter(tableView);

        VBox vbox = new VBox();
        vbox.setId("vbox-custom");
        vbox.setPadding(new Insets(5, 25, 5, 30));

        Button b1 = new Button("Sort according to\nthe goals scored");
        b1.textAlignmentProperty().set(TextAlignment.CENTER);
        vbox.setAlignment(Pos.CENTER);

        b1.setOnAction(e -> {
            tableView.getSortOrder().clear();
            column7.setSortType(TableColumn.SortType.DESCENDING);
            tableView.getSortOrder().add(column7);
            tableView.sort();

        });



        Button b2 = new Button("Sort according to\nthe number of wins");
        b2.textAlignmentProperty().set(TextAlignment.CENTER);

        b2.setOnAction(e -> {
            tableView.getSortOrder().clear();
            column3.setSortType(TableColumn.SortType.DESCENDING);
            tableView.getSortOrder().add(column3);
            tableView.sort();
        });

        Button b3 = new Button("Display all matches");
        b3.textAlignmentProperty().set(TextAlignment.CENTER);

        b3.setOnAction(new EventHandler<ActionEvent>() {     // new EventHandler to display a GUI with the table of all matches played
            @Override
            public void handle(ActionEvent e) {
                Stage stage = new Stage();
                StackPane pane1 = new StackPane();
                Scene scene1 = new Scene(pane1);
                stage.setTitle("Matches Played");

                pane1.setPadding(new Insets(10, 10, 10, 10));
                TableView tableView1 = new TableView();
                tableView1.setId("newTable");


                TableColumn<Match, String> column10 = new TableColumn<>("Home Team");
                column10.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));
                column10.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
                column10.setResizable(false);
                column10.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Match, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Match, String> m) {
                        return new SimpleStringProperty(m.getValue().getHomeTeam().getClubName());  //Retrieving only the club name from the FootballClub object
                    }
                });

                TableColumn<Match, String> column11 = new TableColumn<>("Score");
                column11.setCellValueFactory(new PropertyValueFactory<>("homeScore"));
                column11.prefWidthProperty().bind(tableView.widthProperty().multiply(0.16));
                column11.setResizable(false);

                TableColumn<Match, String> column12 = new TableColumn<>("Away Team");
                column12.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));
                column12.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
                column12.setResizable(false);
                column12.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Match, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Match, String> m) {
                        return new SimpleStringProperty(m.getValue().getAwayTeam().getClubName()); //Retrieving only the club name from the FootballClub object
                    }
                });

                TableColumn<Match, String> column13 = new TableColumn<>("Score");
                column13.setCellValueFactory(new PropertyValueFactory<>("awayScore"));
                column13.prefWidthProperty().bind(tableView.widthProperty().multiply(0.16));
                column13.setResizable(false);

                TableColumn<Match, String> column14 = new TableColumn<>("Date played");
                column14.setCellValueFactory(new PropertyValueFactory<>("date"));
                column14.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
                column14.setResizable(false);

                for (Match match : matches) {
                    tableView1.getItems().add(match);
                }

                tableView1.getColumns().addAll(column10, column11, column12, column13, column14);
                pane1.getChildren().add(tableView1);
                stage.setResizable(false);
                tableView1.sort();
                stage.sizeToScene();

                scene1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                stage.setScene(scene1);
                stage.centerOnScreen();
                stage.show();
            }
        });

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        Button b4 = new Button("Generate a\nrandom played match");
        b4.textAlignmentProperty().set(TextAlignment.CENTER);

        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                if (reachLimit()) {   //Checks if all possible matches have been played with regards to the number of clubs in the premier league
                    // set alert type
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    // set content text
                    a.setContentText("All possible matches have been played!");
                    // show the dialog
                    a.show();


                } else {
                    FootballClub home = null;
                    FootballClub away = null;
                    Random rand = new Random();

                    while ((home == null & away == null) || matchPlayed(home, away)) {
                        int n1 = 0;
                        int n2 = 0;
                        while (isEqual(n1, n2)) {
                            n1 = rand.nextInt(clubList.size());
                            n2 = rand.nextInt(clubList.size());
                        }
                        home = clubList.get(n1);
                        away = clubList.get(n2);
                    }

                    int s1 = rand.nextInt(50);
                    int s2 = rand.nextInt(50);


                    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                    //getTime() returns the current date in default time zone

                    int day = calendar.get(Calendar.DATE);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int year = calendar.get(Calendar.YEAR);

                    Random random = new Random();
                    int minDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();  //The earliest date considered when generating a random match is 1/1/2000
                    int maxDay = (int) LocalDate.of(year, month, day).toEpochDay();     //The final date considered when generating a random match is the current date
                    long randomDay = minDay + random.nextInt(maxDay - minDay);   //The match is generated using any day between the min and max days

                    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

                    String date = String.valueOf(randomDate);

                    String[] timepart = date.split("-");   //The generated date is split by the dash to attain the day, month and year separately
                    int rYear = Integer.parseInt(timepart[0]);
                    int rMonth = Integer.parseInt(timepart[1]);
                    int rDay = Integer.parseInt(timepart[2]);


                    matches.add(new Match(home, away, s1, s2, new Date(rDay, rMonth, rYear)));  //The generated match is added to the match list
                    Score.setScore(home, away, s1, s2);  //The scores are updated based on the match played


                    tableView.refresh();   //Table is refreshed based on the newly generated match

                    String homeTeam = home.getClubName();
                    String awayTeam = away.getClubName();
                    String repeatedLines = new String(new char[47]).replace("\0", "-");
                    String data0 = "Date Played: " + date;
                    String data1 = String.format("%10s %-17s %10s %5d", "Home Team:", homeTeam, "Goals Scored:", s1);
                    String data2 = String.format("%10s %-17s %10s %5d", "Away Team:", awayTeam, "Goals Scored:", s2);
                    textArea.setText(repeatedLines + "\n" + data0 + "\n" + data1 + "\n" + data2);   //Display the details of the new match in the textArea

                }
            }

        });


        Label l2 = new Label("Enter Date to search for played matches:");

        TextField textField = new TextField();
        textField.setPromptText("dd/mm/yyyy");

        Separator separator = new Separator();

        Button b5 = new Button("Search for matches");
        b5.textAlignmentProperty().set(TextAlignment.CENTER);

        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                boolean found = false;
                for (Match match : matches) {
                    if (textField.getText().equals(match.getDate().toString())) {
                        String homeTeam = match.getHomeTeam().getClubName();
                        int homeScore = match.getHomeScore();
                        String awayTeam = match.getAwayTeam().getClubName();
                        int awayScore = match.getAwayScore();
                        String repeatedLines = new String(new char[47]).replace("\0", "-");
                        String data1 = String.format("%10s %-15s %10s %5d", "Home Team:", homeTeam, "Goals Scored:", homeScore);
                        String data2 = String.format("%10s %-15s %10s %5d", "Away Team:", awayTeam, "Goals Scored:", awayScore);
                        textArea.setText(repeatedLines + "\n" + data1 + "\n" + data2);
                        found = true;
                    }
                }
                if (!found) {   //Show Alert if the no matches were played on the entered date
                    // set alert type
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    // set content text
                    a.setContentText("No matches were played on the entered date!");
                    // show the dialog
                    a.show();

                }
            }
        });

        vbox.getChildren().addAll(b1, b2, b3, b4, separator, l2, textField, b5, textArea);
        pane.setRight(vbox);

        for (FootballClub club : clubList) {
            tableView.getItems().add(club);
        }


        tableView.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9);

        primaryStage.setResizable(false);   //Set the primaryStage as non resizable
        primaryStage.setScene(scene);
        primaryStage.show();
        return primaryStage;
    }



    private void makeHeaderWrappable(TableColumn col) {   //Method to wrap the table heading with two lines
        Label label = new Label(col.getText());
        label.setStyle("-fx-padding: 3px;");
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().add(label);
        stack.prefWidthProperty().bind(col.widthProperty().subtract(4));
        label.prefWidthProperty().bind(stack.prefWidthProperty());
        col.setText(null);
        col.setGraphic(stack);
    }

    private boolean isEqual(int n1, int n2){
        return n1==n2;
    }

    private boolean matchPlayed(FootballClub home, FootballClub away) {

        for (Match match : matches) {
            if (home.equals(match.getHomeTeam()) & away.equals(match.getAwayTeam())) {
                return true;
            }
        }
        return false;
    }

    public boolean reachLimit(){
        int m = matches.size();
        int c = clubList.size();
        if (m==(c*(c-1))){  return true; }   //if c is the number of clubs in premier league, the the maximum number of matches that could be played is c*(c-1)
        return false;
    }
}
