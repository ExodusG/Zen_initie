package test.jeu;

import org.junit.*;
import static org.junit.Assert.*;
import jeu.*;
import jeu.joueur.*;

/**
*cette classe est une classe test pour la class Jeu
*@author Q.Le Lan
*/
public class JeuTest{

  private Jeu jeu;

  @Test(expected = IllegalArgumentException.class)
  public void testJeuWithNull() {
    this.jeu=new Jeu(null,null,null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testJeuWithNull2() {
    this.jeu=new Jeu(null,null,Mode.HA);
  }

  @Test()
  public void testJeuWithCorrectValues() {
    this.jeu=new Jeu("marc","luc",Mode.HH);
  }

  @Test()
  public void testgetCourant() {
    this.jeu=new Jeu("marc","luc",Mode.HH);
    assertNotEquals(this.jeu.getCourant(),this.jeu.getNonCourant());
  }

  @Test()
  public void testChangeCourant() {
    this.jeu=new Jeu("marc","luc",Mode.HH);
    Joueur j=this.jeu.getCourant();
    this.jeu.changeCourant();
    assertEquals(j,this.jeu.getNonCourant());
  }
}
