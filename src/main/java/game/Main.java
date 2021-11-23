
package game;

import controller.GameFrameController;

import java.awt.*;

/***
 *Main Method calls the GameFrameModel Class's initialize method
 */
public class Main {

    public static void main(String[] args){

        EventQueue.invokeLater(() -> new GameFrameController().initialize());
    }

}