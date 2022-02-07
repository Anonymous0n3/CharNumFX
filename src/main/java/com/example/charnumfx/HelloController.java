package com.example.charnumfx;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class HelloController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private TextField word1;

    @FXML
    private TextField word2;

    @FXML
    private TextField word3;

    @FXML
    private TextField word4;

    @FXML
    private TextField word5;

    @FXML
    private void onButtonClick() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        Stage stage = (Stage)anchorPane.getScene().getWindow();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);
        Scanner sc = new Scanner(file);
        ArrayList<char[]> strings = new ArrayList<>();
        HashMap<String, Integer> words = new HashMap<>();

        while(sc.hasNextLine()){
            String string = sc.nextLine().toLowerCase(Locale.ROOT);
            strings.add(string.toCharArray());
            String[] wordCollection = string.split(" ");

            for (String wordEach: wordCollection) {

                    if (words.containsKey(wordEach)){
                        words.put(wordEach, words.get(wordEach) + 1);
                    } else {
                            words.put(wordEach, 1);
                    }

            }
        }
        sc.close();

        HashMap<Character, Integer> characters = new HashMap<Character, Integer>();
        for (char[] string: strings) {
            for (char eachChar: string) {
                if (characters.containsKey(eachChar)){
                    characters.put(eachChar, characters.get(eachChar) + 1);
                } else {
                    if(eachChar == 32) {
                    }
                    else{
                        characters.put(eachChar, 1);
                    }
                }
            }
        }
        for (char eachChar: characters.keySet()) {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(String.valueOf(eachChar).toUpperCase(Locale.ROOT));
            series.getData().add(new XYChart.Data<>(String.valueOf(eachChar), characters.get(eachChar)));
            barChart.getData().add(series);
        }

        String[] mostUsedWords = new String[5];

        Integer[] values = words.values().toArray(new Integer[words.size()]);

        Arrays.sort(values);

        ArrayList<String> usedValues = new ArrayList<>();
        int i1 = 0;
        for (int i =values.length-1; i1<5; i--){
            for (String key:words.keySet()) {
                if (words.get(key).equals(values[i])){
                    if (usedValues.contains(key)){}else{
                    mostUsedWords[i1] = key;
                    usedValues.add(key);
                    }
                }
            }
            i1++;
        }
        word1.setText(mostUsedWords[0]);
        word2.setText(mostUsedWords[1]);
        word3.setText(mostUsedWords[2]);
        word4.setText(mostUsedWords[3]);
        word5.setText(mostUsedWords[4]);
    }
}