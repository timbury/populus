/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.ihpi;
import edu.umn.ecology.populus.plot.*;
import java.util.*;

public class IHPIModel extends BasicPlotModel {
   static ResourceBundle res = ResourceBundle.getBundle( "edu.umn.ecology.populus.model.ihpi.Res" );
   
   public String getThisModelInputName() {
      return res.getString( "Discrete_Predator" );
   }
   
   public Object getModelHelpText() {
      return "IHPIHELP";
   }
   
   public IHPIModel() {
      this.setModelInput( new IHPIPanel() );
   }
   
   public static String getModelName() {
      return res.getString( "Host_Parasitoid_with" );
   }
   
   protected String getHelpId() {
      return "appdhpi.overview";
   }
}
