package oTBUtilities;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

/**
 * This class creates a oTBGui to Display Errors caused by OtreeBrowser
 */
public class OtreeBrowserError {

    public OtreeBrowserError(String errorMessage) {
        Stage secondStage = new Stage();
        HBox box = new HBox();
        box.setPadding(new Insets(50));
        box.setAlignment(Pos.TOP_CENTER);
        Label label = new Label();
        label.setText(errorMessage);
        label.setFont(Font.font("Verdana", 15));
        label.setTextFill(Color.RED);
        box.getChildren().add(label);
        secondStage.setScene(new Scene(box));
        secondStage.setTitle("FEHLER");
        secondStage.show();
        secondStage.toFront();
        secondStage.setAlwaysOnTop(true);
        System.err.println(errorMessage);
    }
}
