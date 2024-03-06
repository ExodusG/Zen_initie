package test.jeu.pion;

import org.junit.*;
import static org.junit.Assert.*;
import jeu.pion.PionNormal;
import jeu.Couleur;

/**
*cette classe est une classe test pour la class PionNormal et Pion
*@author Q.Le Lan
*/
public class PionNormalTest {

  private PionNormal pion;

  @After()
  public void tearDown() {
    this.pion=null;
  }

  @Test()
  public void testgetCouleur() {
    this.pion=new PionNormal(Couleur.NOIR);
    assertEquals(Couleur.NOIR,this.pion.getCouleur());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPionWithNull() {
    this.pion=new PionNormal(null);
  }

  @Test()
  public void testtoString() {
    this.pion=new PionNormal(Couleur.NOIR);
    assertEquals("N",this.pion.toString());
    this.pion=new PionNormal(Couleur.BLANC);
    assertEquals("B",this.pion.toString());
  }


}
