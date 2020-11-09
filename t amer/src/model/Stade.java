/***********************************************************************
 * Module:  Stade.java
 * Author:  UTI311
 * Purpose: Defines the Class Stade
 ***********************************************************************/
package model;

import java.util.*;

/** @pdOid 8a0ad0f1-d67e-4c94-8f29-94338e0ada68 */
public class Stade {
   /** @pdOid dc9f85a1-bb61-41a2-b776-d1761397332f */
   private java.lang.String nomStade;
   
   /** @pdRoleInfo migr=no name=Mesure assc=association1 mult=1..1 side=A */
   public Mesure mesure;

}