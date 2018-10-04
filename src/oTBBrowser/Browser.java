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
import oTBUtilities.OtreeBrowserError;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.GeneralSecurityException;

/**
 *
 */


public class Browser {

    private static WebView webView = new WebView();
    private static WebEngine webEngine = webView.getEngine();
    private static String personalLink;
    private static Stage primaryStage;

    /***
     *
     * @param pStage
     * @param pLink
     * @return
     */
    public static WebView initBrowser(Stage pStage, String pLink){
        primaryStage = pStage;
        personalLink = pLink;
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
            new OtreeBrowserError(e.getMessage());
        }

        primaryStage.setFullScreen(true);
        webEngine.setJavaScriptEnabled(true);
        webEngine.setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
        webView.setContextMenuEnabled(false);
        webView.setPrefWidth(primaryStage.getWidth());
        webView.setPrefHeight(primaryStage.getHeight());
        webEngine.load(personalLink);
        initHandler();
        return webView;
    }

    /***
     *
     * Note: The Browserbot class ist for my personal use for testing javascript behaviour
     * //FIXME noch mal komplett durchlaufen lassen und checken, ob exception noch mal auftritt
     */
    private static void initHandler(){

        webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState)-> {
            BrowserBot bB = null;
            if (newState == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                if (window.getMember("internet_window_open") instanceof Boolean && (boolean) window.getMember("internet_window_open") ) {
                    window.setMember("app", new InternetTask(webEngine, primaryStage, (int) window.getMember("timeout_seconds")));
                    InternetTask.timer();
                }
                if(window.getMember("otree_browser_bot") instanceof JSObject) {
                    bB = new BrowserBot( (JSObject) window.getMember("otree_browser_bot"), (int) window.getMember("timeout_seconds"), window);
                }
            }
        });
    }

}
