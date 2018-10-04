package oTBBrowser;
import oTBUtilities.OtreeBrowserError;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.util.Duration;
import netscape.javascript.JSObject;
import java.io.IOException;

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

public class InternetTask {

    private static Timeline timer;
    private static int internetWindowOpenTime = 0;
    private static int currentTime = 0;
    private static Process process = null;
    private static boolean isInternetWindowOpen = false;
    private static String devFFPath = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    private static String labFFPath = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
    private static ProcessHandle handle = null;
    private static WebEngine webEngine;
    private static Stage primaryStage;


    /**
     *
     * @param engine
     * @param stage
     */
    public InternetTask(WebEngine engine, Stage stage, int timeOut) {
        webEngine = engine;
        primaryStage = stage;
        internetWindowOpenTime = timeOut;
    }

    /***
     *  Called from Javascript
     */
    public void open() {
        if (currentTime < internetWindowOpenTime){
            if(!isInternetWindowOpen || (process != null && !handle.isAlive())) {
                try {
                    process = new ProcessBuilder(labFFPath, "-foreground").start();
                    handle = process.toHandle();
                    isInternetWindowOpen = true;
                } catch (IOException e) {
                    new OtreeBrowserError(e.getMessage());
                }
            }
        }

    }


    /**
     *
     */
    public static void timer(){

        timer = new Timeline(new KeyFrame(Duration.millis(1000), (event) -> {
            if(currentTime > internetWindowOpenTime){
                close();
            }
            else{
                setCurrentTime();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private static void setCurrentTime(){
        currentTime ++;
    }

    /**
     * Kill Process via Windows taskkill. process.destroy and  process.destroyForcibly() doesn't work in case we're losing
     * process control (if an new tab or site is opened and so on)
     * Maybe there is a better and stable solution
     */
    public static void close(){
        ProcessBuilder taskKill =  new ProcessBuilder().command("cmd", "/c", "taskkill /IM firefox.exe /F");
        try{
            taskKill.start();

        }catch(IOException e){
           new OtreeBrowserError(e.getMessage());
        }
        JSObject window = (JSObject) webEngine.executeScript("window");
        window.removeMember("app");
        currentTime = 0;
        internetWindowOpenTime = 0;
        primaryStage.toFront();
        primaryStage.requestFocus();
        isInternetWindowOpen = false;
        timer.stop();
        process = null;
        handle = null;
        timer = null;
    }
}
