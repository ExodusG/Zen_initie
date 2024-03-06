package test.jeu.joueur;

import org.junit.*;
import static org.junit.Assert.*;
import jeu.joueur.JoueurHumain;
import jeu.pion.PionNormal;
import java.util.ArrayList;
import jeu.*;

/**
*cette classe est une classe test pour la class JoueurHumain et Joueur
*@author Q.Le Lan
*/
public class JoueurHumainTest {

  private JoueurHumain joueur;

  @Before()
  public void setJoueur() {
    this.joueur=new JoueurHumain("marc");
  }

  @After()
  public void tearDown() {
    this.joueur=null;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testJoueurWithNull() {
    this.joueur=new JoueurHumain(null);
  }

  @Test()
  public void testGetNom() {
    assertEquals("marc",this.joueur.getNom());
  }

  @Test()
  public void testSetListNormalCase() {
    ArrayList<PionNormal> listePion=new ArrayList<PionNormal>();
    for(int i=0;i<12;i++){
      listePion.add(new PionNormal(Couleur.NOIR));
    }
    this.joueur.setListePion(listePion);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetListWithNull() {
    this.joueur.setListePion(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetListSizeUpperThan12() {
    ArrayList<PionNormal> listePion=new ArrayList<PionNormal>();
    for(int i=0;i<15;i++){
      listePion.add(new PionNormal(Couleur.NOIR));
    }
    this.joueur.setListePion(listePion);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetListSizeUnderThan12() {
    ArrayList<PionNormal> listePion=new ArrayList<PionNormal>();
    for(int i=0;i<9;i++){
      listePion.add(new PionNormal(Couleur.NOIR));
    }
    this.joueur.setListePion(listePion);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAppartientWithNull() {
    this.joueur.appartient(null);
  }

  @Test()
  public void testAppartient() {
    ArrayList<PionNormal> listePion=new ArrayList<PionNormal>();
    for(int i=0;i<12;i++){
      listePion.add(new PionNormal(Couleur.NOIR));
    }
    this.joueur.setListePion(listePion);
    assertEquals(false,this.joueur.appartient(new PionNormal(Couleur.BLANC)));
    assertEquals(true,this.joueur.appartient(listePion.get(3)));
  }

  /**
  *test estValide pour des ligne non droite
  */
  @Test()
  public void testEstValideNotStraightLine() {
    JoueurHumain j2 = new JoueurHumain("patrick");
    Plateau plateau=new Plateau(this.joueur,j2);

    assertEquals(false,this.joueur.estValide(0,0,2,8,plateau));
    assertEquals(false,this.joueur.estValide(8,3,7,6,plateau));
    assertEquals(false,this.joueur.estValide(3,7,6,5,plateau));
    assertEquals(false,this.joueur.estValide(8,7,10,8,plateau));
  }

  /**
  *test estValide avec un déplacement de case non correct
  */
  @Test()
  public void testEstValideNotRightNBCase() {
    JoueurHumain j2 = new JoueurHumain("patrick");
    Plateau plateau=new Plateau(this.joueur,j2);

    assertEquals(false,this.joueur.estValide(0,0,1,0,plateau));
    assertEquals(false,this.joueur.estValide(0,0,4,0,plateau));
    assertEquals(false,this.joueur.estValide(2,3,5,6,plateau));
    assertEquals(false,this.joueur.estValide(2,3,1,2,plateau));
    assertEquals(false,this.joueur.estValide(2,7,1,8,plateau));
    assertEquals(false,this.joueur.estValide(2,7,6,3,plateau));
    assertEquals(false,this.joueur.estValide(2,7,6,3,plateau));
    assertEquals(false,this.joueur.estValide(6,9,6,10,plateau));
    assertEquals(false,this.joueur.estValide(6,9,6,4,plateau));
  }

  /**
  *test estValide dans des cas correct
  */
  @Test()
  public void testEstValideCorrect() {
    JoueurHumain j2 = new JoueurHumain("patrick");
    Plateau plateau=new Plateau(this.joueur,j2);

    assertEquals(true,this.joueur.estValide(0,0,3,0,plateau));
    plateau=new Plateau(this.joueur,j2);
    assertEquals(true,this.joueur.estValide(4,1,4,3,plateau));
    plateau=new Plateau(this.joueur,j2);
    assertEquals(true,this.joueur.estValide(4,9,4,7,plateau));
    plateau=new Plateau(this.joueur,j2);
    assertEquals(true,this.joueur.estValide(2,7,4,5,plateau));
    plateau=new Plateau(this.joueur,j2);
    assertEquals(true,this.joueur.estValide(2,7,0,9,plateau));
    plateau=new Plateau(this.joueur,j2);
    assertEquals(true,this.joueur.estValide(8,7,6,5,plateau));
    plateau=new Plateau(this.joueur,j2);
    assertEquals(true,this.joueur.estValide(8,7,10,9,plateau));
  }

  /**
  *test est valide quand le pions zen n'est pas connécté
  */
  @Test()
  public void testEstValidePionZenNotConnect() {
    JoueurHumain j2 = new JoueurHumain("patrick");
    Plateau plateau=new Plateau(this.joueur,j2);

    assertEquals(false,this.joueur.estValide(5,5,4,5,plateau));
  }


}
