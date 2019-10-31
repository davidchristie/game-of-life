package gameoflife;

import javax.swing.JFrame;

public class GameOfLifeApplication {

   public static void main(String[] arguments) {
      showFrame();
   }

   private static void showFrame() {
      final GameOfLifeFrame frame = new GameOfLifeFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}
