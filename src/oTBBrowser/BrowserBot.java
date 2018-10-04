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

package oTBBrowser;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import netscape.javascript.JSObject;

import java.util.Hashtable;

/***
 * This Class is needesdto simulate JS Interactions for Testing
 */
class BrowserBot {

    private String action;
    private String element;
    private int actionCounter;
    private int timeOut;
    private Timeline timer;
    private Hashtable <String, String> params;

    public BrowserBot(JSObject jsObject, int timeout, JSObject window){

        action = (String) jsObject.getMember("action");
        element = (String) jsObject.getMember("element");
        actionCounter = (int) jsObject.getMember("actionCounter");
        timeOut = timeout;
        int cycleTime = timeOut/actionCounter;
        if( actionCounter > 1){
            timer = new Timeline(new KeyFrame(Duration.seconds(cycleTime), (event) -> {
                window.call("jsTest", new Object[]{element, action});
            }));
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();
        } else {
            timer = new Timeline(new KeyFrame(Duration.seconds(5), (event) -> {
                window.call("jsTest", new Object[]{element, action});
            }));
            timer.setCycleCount(1);
            timer.play();
        }

    }


}
