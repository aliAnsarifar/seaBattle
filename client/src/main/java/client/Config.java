package client;

import client.graphics.GraphicalAgent;
import client.graphics.panels.game.GraphicBoard;
import client.graphics.panels.game.GraphicCell;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("port" , "9000");
        properties.setProperty("GraphicBoardWith" , "600");
        properties.setProperty("GraphicBoardHeight" , "600");
        properties.setProperty("GraphicCellWidth" , "50");
        properties.setProperty("GraphicalCellHeight" , "50");
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\config\\MainConfig.txt");
        properties.store(fileWriter , "save");
        fileWriter.flush();
        fileWriter.close();

    }

    public Config() {
        try {
            Properties properties = new Properties();
            FileReader fileReader = new FileReader("src\\main\\resources\\config\\MainConfig.txt");
            properties.load(fileReader);
            Main.port = Integer.parseInt(properties.getProperty("port"));
            GraphicBoard.Width = Integer.parseInt(properties.getProperty("GraphicBoardWith"));
            GraphicBoard.Height = Integer.parseInt(properties.getProperty("GraphicBoardHeight"));
            GraphicCell.Width = Integer.parseInt(properties.getProperty("GraphicCellWidth"));
            GraphicCell.Height = Integer.parseInt(properties.getProperty("GraphicalCellHeight"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


