package test.jeu;

import org.junit.*;
import static org.junit.Assert.*;
import jeu.pion.*;
import jeu.*;
import jeu.joueur.*;

/**
*cette classe est une classe test pour la class Plateau
*@author Q.Le Lan
*/
public class PlateauTest {

  private Plateau plateau;
  private JoueurHumain j1;
  private JoueurHumain j2;

  @Before()
  public void setPlateau() {
    j1 = new JoueurHumain("marc");
    j2 = new JoueurHumain("dupond");
    this.plateau=new Plateau(j1,j2);
  }

  @After()
  public void tearDown() {
    this.plateau=null;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlateauWithNull() {
    this.plateau=new Plateau(null,null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeplacePionWithNull() {
    this.plateau.deplacePion(1,2,3,4,j1,null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeplacePionUpperThan11() {
    this.plateau.deplacePion(1,2,15,4,j1,j2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeplacePionUnderThan0() {
    this.plateau.deplacePion(1,-5,11,4,j1,j2);
  }

  @Test()
  public void testZenConnect() {
    assertEquals(false,this.plateau.checkZen(5,5));
    assertEquals(true,this.plateau.checkZen(1,0));
  }

  /**
  *test pour éliminer un jeu du plateau
  */
  @Test()
  public void testMangePion() {
    Case[][] plat=this.plateau.getPlateau();
    Pion pion=plat[0][0].getPion();
    this.plateau.deplacePion(0,0,5,0,j1,j2);
    assertEquals(pion,plat[5][0].getPion());
  }


  @Test()
  public void testVainqueurFalse() {
    assertEquals(false,this.plateau.checkVainqueur(j2));
    this.plateau.deplacePion(3,2,0,0,j1,j2);
    this.plateau.deplacePion(7,2,1,0,j1,j2);
    this.plateau.deplacePion(1,4,2,0,j1,j2);
    this.plateau.deplacePion(9,4,3,0,j1,j2);
    this.plateau.deplacePion(1,6,4,0,j1,j2);
    this.plateau.deplacePion(9,6,6,0,j1,j2);
    this.plateau.deplacePion(3,8,7,0,j1,j2);
    this.plateau.deplacePion(7,8,8,0,j1,j2);
    this.plateau.deplacePion(0,10,9,0,j1,j2);
    //il manque 1 pion dans la chaine
    assertEquals(false,this.plateau.checkVainqueur(j2));
  }

  @Test()
  public void testVainqueurTrue() {
    this.plateau.deplacePion(3,2,0,0,j1,j2);
    this.plateau.deplacePion(7,2,1,0,j1,j2);
    this.plateau.deplacePion(1,4,2,0,j1,j2);
    this.plateau.deplacePion(9,4,3,0,j1,j2);
    this.plateau.deplacePion(1,6,4,0,j1,j2);
    this.plateau.deplacePion(9,6,6,0,j1,j2);
    this.plateau.deplacePion(3,8,7,0,j1,j2);
    this.plateau.deplacePion(7,8,8,0,j1,j2);
    this.plateau.deplacePion(0,10,9,0,j1,j2);
    this.plateau.deplacePion(5,10,1,1,j1,j2);
    this.plateau.deplacePion(3,2,3,1,j1,j2);
    assertEquals(false,this.plateau.checkVainqueur(j2));

    this.plateau.deplacePion(1,1,1,2,j1,j2);
    this.plateau.deplacePion(1,0,1,3,j1,j2);
    assertEquals(false,this.plateau.checkVainqueur(j2));

    this.plateau.deplacePion(5,5,1,1,j1,j2);
    this.plateau.deplacePion(1,1,2,2,j1,j2);
    //avec le pion zen dans la chaine
    assertEquals(true,this.plateau.checkVainqueur(j2));
  }

  @Test()
  public void testVainqueurTrueZen() {
    this.plateau.deplacePion(3,2,0,0,j1,j2);
    this.plateau.deplacePion(7,2,1,0,j1,j2);
    this.plateau.deplacePion(1,4,2,0,j1,j2);
    this.plateau.deplacePion(9,4,3,0,j1,j2);
    this.plateau.deplacePion(1,6,4,0,j1,j2);
    this.plateau.deplacePion(9,6,6,0,j1,j2);
    this.plateau.deplacePion(3,8,7,0,j1,j2);
    this.plateau.deplacePion(7,8,8,0,j1,j2);
    this.plateau.deplacePion(0,10,9,0,j1,j2);
    this.plateau.deplacePion(5,10,1,1,j1,j2);
    this.plateau.deplacePion(3,2,5,1,j1,j2);
    this.plateau.deplacePion(5,5,2,1,j1,j2);
    this.plateau.deplacePion(1,1,3,2,j1,j2);
    assertEquals(true,this.plateau.checkVainqueur(j2));
    assertEquals(false,this.plateau.checkVainqueur(j1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVainqueurWithNull() {
    assertEquals(false,this.plateau.checkVainqueur(null));
  }
}
