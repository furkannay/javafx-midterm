package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;



public class Animation extends Application {
    static ArrayList<double[]> ballsizeArraylist = new ArrayList<>();
    int numofShapes = 111;
    int countofball = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Circle btn = new Circle(60);
        int width = 800;
        int height = 800;
        Canvas canvas=new Canvas(width,height);
        Group group = new Group(canvas);
        Scene scene = new Scene(group, width, height);

        double[][] positions = readFile();
        for (int i = 0; i<numofShapes; i++){
            addBallwithTransition((int) (Math.random()*25),primaryStage,group,scene);
//            Thread.sleep(100);
        }
        if (numofShapes > positions.length)
        {saveToFile();}


        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
    private Circle addBall(Stage primaryStage, Group grup, Scene scen, int radius,double[]... posxy){
        Circle ball = new Circle(radius);

        if (posxy.length != 0){

            ball.setCenterX(posxy[countofball][0]);
            ball.setCenterX(posxy[countofball][1]);

        }
        else{
            double random = Math.random()*800;
            double random2 = Math.random()*800;
            ball.setCenterX(random);
            ball.setCenterY(random2);
            double[] ballxy = new double[] {random,random2};
            ballsizeArraylist.add(ballxy);

        }

        ball.setFill(Color.color(Math.random(), Math.random(), Math.random()));
//        ball.setFill(Paint.valueOf("green"));
        grup.getChildren().add(ball);
        countofball++;
        return ball;
    }

    private void addBallwithTransition(int radius,Stage primaryStage, Group grup, Scene scen, double[]... posxy){


        Circle my_ball = addBall(primaryStage,grup,scen,radius,posxy);

        Duration duration = Duration.millis(Math.random()*1);
        //Create new translate transition
        TranslateTransition transition = new TranslateTransition(duration, my_ball);
        //Move in X axis by +200
        int rand1 = -500 + new Random().nextInt(1000);
        int rand2 = -500 + new Random().nextInt(1000);

        transition.setByX(rand1);
        //Move in Y axis by +100
        transition.setByY(rand2);
        //Go back to previous position after 2.5 seconds
        transition.setAutoReverse(true);
        //Repeat animation twice
        transition.setCycleCount((int) (Math.random()*20));
        transition.play();
    }

    public double[][] readFile() {
        double[][] places = new double[numofShapes][2];
        try {

            String location = "ballsizes.txt";
            String all = Files.readString(Path.of(location));
            String[] lines = all.split("\n");
            int i = 0;
            for (String line:lines
            ) {

                String[] valuesList = line.split(" ");
                double x = Double.parseDouble(valuesList[0]);
                double y = Double.parseDouble(valuesList[1]);
                places[i] = new double[] {x,y};
                i++;

            }
            System.out.println("biyudy");
            System.out.println(all);
            System.out.println("buydu");

        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("bruh");


        }
        for (double[]a :places
        ) {
            for (double b:a
            ) {
                System.out.println(b);

            }

        }
        return places;

    }

    public String saveToFile() {
        try {
            FileWriter writer = new FileWriter("ballsizes.txt");
            for (double[] ballxy:ballsizeArraylist
                 ) {
                int i = 0;
                for (double xy:ballxy
                     ) {

                    String xy1 = Double.toString(xy);
                    if (i ==0){
                    String thii = String.valueOf(xy);
                    writer.write(thii);
                    System.out.print(thii);
                    }
                    else{String thii = " "+ xy;
                    System.out.print(thii);
                    writer.write(thii);}
                    i++;

                }
                writer.write("\n");

            }

            writer.close();
            System.out.println("Success");
            return "Success";
        }

        catch (Exception e){
            System.out.println("gg");
            return "Failed";
        }


    }
}
