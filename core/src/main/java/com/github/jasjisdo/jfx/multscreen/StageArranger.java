package com.github.jasjisdo.jfx.multscreen;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;


public class StageArranger {

    /**
     * Show stage in absolute center of the primary screen.
     *
     * @param stage which will be arranged on the primary screen.
     */
    public static void showOnPrimaryScreen(final Stage stage, StageAlignment alignment) {
        arrangeStageOnScreen(getPrimaryScreen(), stage, alignment);
        stage.show();
    }

    /**
     * Show stage in absolute center of the active screen. An screen is considered as active when the mouse cursor is
     * located in its bounds.
     *
     * @param stage which will be arranged on the active screen.
     */
    public static void showOnActiveScreen(final Stage stage, StageAlignment alignment) {
        arrangeStageOnScreen(getScreenByMousePosition(), stage, alignment);
        stage.show();
    }

    /**
     * Show stage in absolute center of a recent non active screen. An screen is considered as non active when the mouse
     * cursor is not located in its bounds.
     *
     * @param stage which will be arranged on the active screen.
     */
    public static void showOnRecentNonActiveScreen(final Stage stage, StageAlignment alignment) {
        arrangeStageOnScreen(getRecentScreenWithoutCursor(), stage, alignment);
        stage.show();
    }

    /**
     * Show stage in absolute center of a recent non active screen. An screen is considered as non active when the mouse
     * cursor is not located in its bounds.
     *
     * @param stage which will be arranged on the active screen.
     */
    public static void showOnHighestResolutionScreen(final Stage stage, StageAlignment alignment) {
        arrangeStageOnScreen(getHighestResolutionScreen(), stage, alignment);
        stage.show();
    }

    /*// set the stage on the primary screen (centered).
    private static void centerStageOnPrimaryScreen(final Stage stage, StageAlignment alignment) {
        arrangeStageOnScreen(getPrimaryScreen(), stage, alignment);
    }

    // set the stage on the screen (centered) where the mouse pointer is located.
    private static void centerStageOnActiveScreen(final Stage stage, StageAlignment alignment) {
        arrangeStageOnScreen(getScreenByMousePosition(), stage, alignment);
    }*/

    private static void arrangeStageOnScreen(final int screen, final Stage stage, StageAlignment alignment) {

        switch (alignment) {

            case TOP_LEFT:

                break;

            case TOP_CENTER:

                break;

            case TOP_RIGHT:

                break;

            case CENTER_LEFT:

                break;

            case CENTER:

                Rectangle2D bounds = Screen.getScreens().get(screen).getVisualBounds();

                double x = Math.max(bounds.getWidth() - stage.getWidth(), 0.0);
                double y = Math.max(bounds.getHeight() - stage.getHeight(), 0.0);

                stage.setX( (x * 0.5) + bounds.getMinX() );
                stage.setY( (y * 0.5) + bounds.getMinY() );

                break;

            case CENTER_RIGHT:

                break;

            case BOTTOM_LEFT:

                break;

            case BOTTOM_CENTER:

                break;

            case BOTTOM_RIGHT:

                break;
        }


    }

    private static int getScreenByMousePosition() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        int screenNumber = 0;
        int devices = Screen.getScreens().size();
        for(int i = 0; i < devices; i++) {
            Screen screen = Screen.getScreens().get(i);
            Rectangle2D bounds = screen.getVisualBounds();
            if(bounds.contains(new Point2D(point.x, point.y))){
                screenNumber = i;
            }
        }
        return screenNumber;
    }

    private static int getRecentScreenWithoutCursor() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        int screenNumber = 0;
        int devices = Screen.getScreens().size();
        for(int i = 0; i < devices; i++) {
            Screen screen = Screen.getScreens().get(i);
            Rectangle2D bounds = screen.getVisualBounds();
            if(!bounds.contains(new Point2D(point.x, point.y))){
                screenNumber = i;
            }
        }
        return screenNumber;
    }

    private static int getHighestResolutionScreen() {
        int screenNumber = 0;
        int devices = Screen.getScreens().size();
        Rectangle2D highestBounds = new Rectangle2D(0,0,0, 0);
        for(int i = 0; i < devices; i++) {
            Rectangle2D bounds = Screen.getScreens().get(i).getVisualBounds();
            if(bounds.getWidth() > highestBounds.getWidth() &&
                    bounds.getHeight() > highestBounds.getHeight()){
                highestBounds = bounds;
                screenNumber = i;
            }
        }
        return screenNumber;
    }


    private static int getPrimaryScreen() {
        return Screen.getScreens().indexOf(Screen.getPrimary());
    }


    public static void main(String[] args) {

    }
}