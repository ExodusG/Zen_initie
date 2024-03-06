package test.jeu;

import org.junit.*;
import static org.junit.Assert.*;
import jeu.pion.PionNormal;
import jeu.pion.PionZen;
import jeu.*;

/**
*cette classe est une classe test pour la class Case
*@author Q.Le Lan
*/
public class CaseTest {

  private Case ca;

  @After()
  public void tearDown() {
    this.ca=null;
  }

  /**
  *test de créer une case avec les différents constructeurs
  */
  @Test()
  public void testCreateCaseWithCorrectValue() {
    this.ca=new Case(5,5,new PionNormal(Couleur.BLANC));
    this.ca=new Case(8,9);
    this.ca=new Case(1,3,new PionZen());
  }

  /**
  *test de créer une Case avec de mauvais paramètres
  */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateCaseWithNull() {
    this.ca=new Case(5,5,null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateCaseWithUpper11() {
    this.ca=new Case(15,5,new PionNormal(Couleur.NOIR));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateCaseWithUnder0() {
    this.ca=new Case(8,-3,new PionNormal(Couleur.NOIR));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateCaseWithUnder0_2() {
    this.ca=new Case(7,-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateCaseWithUpper11_2() {
    this.ca=new Case(18,3);
  }

  @Test()
  public void testCreateCaseWithEqualsTo0() {
    this.ca=new Case(0,0);
  }

  @Test()
  public void testCreateCaseWithEqualsTo11() {
    this.ca=new Case(11,11);
  }

  /**
  *test la méthode estOccupe
  */
  @Test()
  public void testestOccupe() {
    this.ca=new Case(11,11);
    assertEquals(false,this.ca.estOccupe());
    this.ca=new Case(11,11,new PionNormal(Couleur.BLANC));
    assertEquals(true,this.ca.estOccupe());
  }

  @Test()
  public void testGetPion() {
    this.ca=new Case(11,11);
    assertEquals(null,this.ca.getPion());
    PionNormal pion= new PionNormal(Couleur.BLANC);
    this.ca=new Case(11,11,pion);
    assertEquals(pion,this.ca.getPion());
  }

  @Test()
  public void testSetPion() {
    PionNormal pion= new PionNormal(Couleur.BLANC);
    this.ca=new Case(11,11);
    this.ca.setPion(pion);
    assertEquals(pion,this.ca.getPion());
    this.ca=new Case(11,11,pion);
    this.ca.setPion(null);
    assertEquals(null,this.ca.getPion());
  }

  @Test()
  public void testToString() {
    PionNormal pion= new PionNormal(Couleur.BLANC);
    this.ca=new Case(11,11,pion);
    assertEquals("B",this.ca.toString());
    this.ca=new Case(11,11,new PionNormal(Couleur.NOIR));
    assertEquals("N",this.ca.toString());
    this.ca=new Case(11,11,new PionZen());
    assertEquals("Z",this.ca.toString());
  }

}
