/**
 * Created by mmons on 9/22/2016.
 */

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.util.Random;

public class TheGameOfLife extends Application
{
  //groups
  private Group root = new Group();
  private Group camera = new Group();
  private Group world = new Group();

  private Scene scene;
  private LinearGradient bg;
  private Random random = new Random();


  private static final Stop WHITE_END = new Stop(.75, Color.BLACK);
  private static final Stop AQUA = new Stop(0, Color.AQUA);

  private Cell[][][] cells = new Cell[32][32][32];

  private void buildCamera()
  {

  }

   private void createGame()
  {
    int seperator;
    for(int y = 1; y < 31; y++)
    {
      for(int x = 1; x < 31; x++)
      {
        for(int z = 1; z < 31; z++)
        {
          cells[x][y][z] = new Cell(random.nextBoolean());
          cells[x][y][z].setTranslateX(x*cells[x][y][z].BOX_WIDTH );
          cells[x][y][z].setTranslateY(y*cells[x][y][z].BOX_WIDTH);
          cells[x][y][z].setTranslateZ(z*cells[x][y][z].BOX_WIDTH);
          root.getChildren().add(cells[x][y][z]);
        }
      }
    }
  }

  public static void main(String[] args)
  {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage)
  {
    root.getChildren().add(world);
    root.setDepthTest(DepthTest.ENABLE);

    bg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, AQUA, WHITE_END);
    scene = new Scene(root, 1024, 768, true);
    scene.setFill(bg);

    buildCamera();
    createGame();

    primaryStage.setScene(scene);

//    primaryStage.setFullScreen(true);
    primaryStage.setTitle("The Game of Life");
    primaryStage.show();
  }
}
