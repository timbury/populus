/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.help;

import java.io.File;
import java.net.URI;
import edu.umn.ecology.populus.core.PopPreferencesStorage;
import edu.umn.ecology.populus.fileio.Logging;

public class PDFHelpFileMgr {
	/** Return location of help file outside of JAR, and copy the file out if needed
	 * 
	 * @param forceAlwaysUpdate - If true, it will always prompt the user to set up the file.
	 * @return name of file
	 */
	private static void setupHelpFileFirst(boolean forceAlwaysUpdate) {
		boolean shouldRun = forceAlwaysUpdate;
		if (!shouldRun && isLocalFile()) {
			File f = new File(getHelpFileAsFileName(false));
			if(null != f && (!f.canRead() || f.length() < 100)){
				Logging.log("Can't read file " + f + ", will attempt to set up.");
				shouldRun = true;
			}
		}
		
		if (shouldRun) {
			HelpConfigPanel.launchWindow();
		}
	}


	private static String getHelpFileLocation(boolean setupIfNeeded) {
		if (setupIfNeeded)
			setupHelpFileFirst(false);
		return PopPreferencesStorage.getHelpFileLocation();
	}
	public static String getHelpFileLocationURI() {
		return PDFHelpFileMgr.getHelpFileLocation(true);
	}

	public static String getHelpFileAsFileName(boolean setupIfNeeded) {
		String str = PDFHelpFileMgr.getHelpFileLocation(setupIfNeeded);
		try {
			return new File(new URI(str)).getAbsolutePath();
		} catch (Exception e) {
			Logging.log("File name not a file");
		}
		return "";
	}
	private static boolean isLocalFile() {
		String str = PopPreferencesStorage.getHelpFileLocation();
		return (str.startsWith("file:/") || str.isEmpty());
	}
	public static String getHelpLang() {
		return PopPreferencesStorage.getHelpLang();
	}
	public static String getHelpLangSourceFile() {
		String lang = getHelpLang();
		if (lang.equalsIgnoreCase("Portuguese")) {
			return "PopulusHelpPortuguese5.5.pdf";
		} else if (lang.equalsIgnoreCase("Spanish")) {
			return "PopulusHelpEspagnol5.5.pdf";		   
		} else {
			return "PopulusHelpEnglish5.5.pdf";		   
		}
	}
	
}
