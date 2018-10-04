package oTBGui;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

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
public class GuiElements {

    private HBox curLinkContainer = new HBox();
    private HBox body = new HBox();
    /**
     * Toplabel, place of implementation
     * FIXME character encoding when startet via terminal
     */
    private Label claim = new Label();
    private ImageView logo = new ImageView();
    private ImageView logo2 = new ImageView();
    private HBox logoContainer = new HBox();
    /**
     * Ok button first screen
     */
    private OBButton ok;
    private Label label;
    /**
     * Textfield first screen
     */
    private TextField textField;
    /**
     * Pane, wich contains gui elements
     */
    private BorderPane borderPane = new BorderPane();

    /**
     * Constructor sets gui elements like textfield, button, fonts
     * it defines spaces and sizes
     */
     public GuiElements(){

         curLinkContainer.setMinWidth(800);
         borderPane.setPadding(new Insets(10, 40, 40, 40));
         BorderPane.setMargin(body, new Insets(150, 40, 100, 0));
         label = new Label("Bitte geben Sie den aktuellen SessionLink ein: ");
         textField = new TextField();
         label.setFont(Font.font(20));
         textField.setFont(Font.font(15));
         textField.setPrefWidth(250);
         textField.setPrefHeight(20);
         curLinkContainer.getChildren().addAll(label, textField);
         curLinkContainer.setSpacing(40);
         ok = new OBButton("OK");
         BorderPane.setMargin(ok, new Insets(0, 100, 100, 100));
         body.getChildren().addAll(curLinkContainer, ok);
         borderPane.setCenter(body);
         logo2.setImage(new Image("oTBImages/logo_uzk.png"));
         logo.setImage(new Image("oTBImages/oTreeBrowser.png"));
         logoContainer.setSpacing(20);
         logoContainer.getChildren().addAll(logo2, logo);
         logoContainer.setPrefHeight(180);
         logoContainer.setPrefWidth(borderPane.getPrefWidth());
         borderPane.setTop(logo2);
         borderPane.setBottom(logo);
     }

    /**
     * @return textfield
     */
    public TextField getTextField() {
        return textField;
    }

    /**
     * @return ok button
     */
    public OBButton getOkButton() {
        return ok;
    }

    /**
     * @return border pane
     */
    public BorderPane getBorderPane(){
        return borderPane;
    }
}
