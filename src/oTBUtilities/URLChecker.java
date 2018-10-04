package oTBUtilities;
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

/**
 *  URLChecker class does a small test, if the URL is valid or not
 *  TODO: when webengine bugs are fixed by future javaFX versions, this class should be expanded
 */
public class URLChecker {

    public static URL checkURL(String url){
        URL checkURL = null;
        try {
            checkURL = new URL(url);
        } catch (MalformedURLException e) {
            new OtreeBrowserError("FEHLER: Bitte geben Sie eine VALIDE URL (http://, https://) an!");
            return null;
        }
        return checkURL;
    }
}
