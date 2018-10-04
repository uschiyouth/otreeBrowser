package oTBGui;
import oTBBrowser.Browser;
import oTBUtilities.OtreeBrowserError;
import oTBUtilities.URLChecker;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

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

public class Gui extends Group {

    /**
     * Stage, given by JavaFX
     */
    private static Stage primaryStage;
    /**
     * Textfield, expects valid Url
     */
    private TextField textField;
    /**
     * The Ok Button on the first Screen
     */
    private OBButton ok;

    /**
     *
     */
    private URL url;

    /**
     * Constructor, calls Methods to init gui elements and handlers
     * @param pStage, pUrl
     */
    public Gui(Stage pStage, String pUrl) {
        primaryStage = pStage;
        if (pUrl != null) {
            byte ptext[] = pUrl.getBytes();
            String tempUrl = null;
            try {
                tempUrl = new String(ptext, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                new OtreeBrowserError(e.getMessage());
            }
            try {
                url = new URL(tempUrl);
            } catch (MalformedURLException e) {
                new OtreeBrowserError(e.getMessage());
            }
            initBrowser();
        }
        else {
            this.initGui();
            this.initGuiHandler();
        }

    }

    /**
     * Method instantiates gui elements class and
     * adds gui elements to the root (this)
     */
    private void initGui(){
        GuiElements guiElements = new GuiElements();
        this.getChildren().add(guiElements.getBorderPane());
        textField = guiElements.getTextField();
        ok = guiElements.getOkButton();
    }

    /***
     * Sets event listeners for textfield and
     * ok button on startscreen.
     */
    private void initGuiHandler(){

        textField.setOnAction((event) ->  {
            processInput();
        });

        ok.setOnMouseClicked((event) -> {
            processInput();
        });

    }

    /**
     * Check Url to validity
     */
    private void processInput(){
        url = URLChecker.checkURL(textField.getText());
        if(url!=null) {
            initBrowser();
        }
    }

    /**
     * Initializes Webview and adds it to the root (this)
     */
    private void initBrowser(){
        this.getChildren().add(Browser.initBrowser(primaryStage, url.toString()));
    }

}







