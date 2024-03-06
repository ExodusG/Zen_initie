package test.jeu.pion;

import org.junit.*;
import static org.junit.Assert.*;
import jeu.pion.PionZen;
import jeu.Couleur;

/**
*cette classe est une classe test pour la class PionZen
*@author Q.Le Lan
*/
public class PionZenTest {

  private PionZen pion;

  @Before()
  public void setUp() {
    this.pion=new PionZen();
  }

  @After()
  public void tearDown() {
    this.pion=null;
  }

  @Test()
  public void testgetCouleur() {
    assertEquals(Couleur.ROUGE,this.pion.getCouleur());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetLastPosWithIllegalValue1() {
    this.pion.setLastPos(-1,-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetLastPosWithIllegalValue2() {
   this.pion.setLastPos(1,-2);
  }

   @Test(expected = IllegalArgumentException.class)
   public void testSetLastPosWithIllegalValue3() {
     this.pion.setLastPos(9,12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetLastPosWithIllegalValue4() {
    this.pion.setLastPos(15,8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetLastPosWithIllegalValue5() {
    this.pion.setLastPos(15,-20);
  }

  @Test()
  public void testSetLastPosValidValues() {
    this.pion.setLastPos(5,9);
    assertEquals(5,this.pion.getLastX());
    assertEquals(9,this.pion.getLastY());
  }

  @Test()
  public void testGetXYBeforeLastPos() {
    assertEquals(-1,this.pion.getLastX());
    assertEquals(-1,this.pion.getLastY());
  }

  @Test()
  public void testToString() {
    assertEquals("Z",this.pion.toString());
  }
}
