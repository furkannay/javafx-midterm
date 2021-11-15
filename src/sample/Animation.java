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
import java.util.ArrayList;
import java.util.Random;



public class Animation extends Application {
    static ArrayList<double[]> ballsizeArraylist = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Circle btn = new Circle(60);
        int width = 800;
        int height = 800;
        Canvas canvas=new Canvas(width,height);
        Group group = new Group(canvas);
        Scene scene = new Scene(group, width, height);




        for (int i = 0; i<50; i++){
            addBallwithTransition((int) (Math.random()*25),primaryStage,group,scene);
//            Thread.sleep(100);
        }
        saveToFile();


        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
    private Circle addBall(Stage primaryStage, Group grup, Scene scen, int radius){


        Circle ball = new Circle(radius);
        double random = Math.random()*800;
        double random2 = Math.random()*800;
        ball.setCenterX(random);
        ball.setCenterY(random2);
        double[] ballxy = new double[] {random,random2};
        ballsizeArraylist.add(ballxy);
        ball.setFill(Color.color(Math.random(), Math.random(), Math.random()));
//        ball.setFill(Paint.valueOf("green"));
        grup.getChildren().add(ball);
        return ball;
    }

    private void addBallwithTransition(int radius,Stage primaryStage, Group grup, Scene scen){


        Circle my_ball = addBall(primaryStage,grup,scen,radius);

        Duration duration = Duration.millis(Math.random()*10000);
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
                    String thii = "x: "+ xy + "  ";
                    writer.write(thii);
                    System.out.print(thii);
                    }
                    else{String thii = "y: "+ xy + "  ";
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
