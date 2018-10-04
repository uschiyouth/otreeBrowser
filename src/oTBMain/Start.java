package oTBMain;

import oTBGui.Gui;
import oTBUtilities.OtreeBrowserError;
import oTBUtilities.URLChecker;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/***
 * @author Ruth Neeßen
 *
 * Copyright 2018 by Ruth Neeßen, Universiät zu Köln, ruth.neessen@wiso.uni-koeln.de
 *
 * This file is part of OtreeBrowser.
 *
 * OtreeBrowser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OtreeBrowser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
//FIXME Webengine doesnt load when called by windows cli.
//FIXME jar-file doesnt react to args, when called by cli
public class Start extends Application {

    private static String url = null;
    private static String[] cliArgs = null;

    public static void main(String[] args) {

        cliArgs = args;

        launch(args);
    }

    /**
     * This method creates a new scene.
     * It sets some attributes for the stage, like fullscreen-behaviour and
     * adds a handle events for escape and F12 keys in therms of showing fullscreen or not.
     * Starts an JavaFX Application.
     * @param primaryStage: the primary stage given by the JavaFX Framework
     */

    public void start(Stage primaryStage) {

        Scene startScreen = new Scene(new Gui(primaryStage, url));
        primaryStage.setTitle("Otree oTBBrowser");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(startScreen);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("F12"));
        primaryStage.setFullScreenExitHint("");

        startScreen.setOnKeyPressed((event) -> {
            if(event.getCode()==KeyCode.ESCAPE)
                    primaryStage.setFullScreen(true);
        });

        primaryStage.getIcons().add(new Image("oTBImages/logo.png"));
        primaryStage.show();

        if(cliArgs.length > 1) {
            System.out.println("Zu viele Parameter");
            new OtreeBrowserError("Fehler: Bitte geben Sie genau EINEN Link an!");
        }
        if(cliArgs.length == 1) {
            URLChecker.checkURL(cliArgs[0]);
            url = cliArgs[0];
        }

    }


}
