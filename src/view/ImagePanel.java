package view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
*class qui permet de créer des JPanel customisé qui permet de gérer les images de fond du panel plus simplement
*@author Q.Le Lan
*/
class ImagePanel extends JPanel{

  /**
  *définir la versionUID
  */
  private static final long serialVersionUID= 1L;

  /**
  l'image de fond du panel
  */
  private Image image;

  /**
  *constructeur du ImagePanel
  *@param image l'image à mettre en fond du panel
  */
  public ImagePanel(Image image){
    super();
    this.image = image;
  }

  /**
  *permet de peindre le panel avec l'image de fond
  *@param g le graphics à ajouter au panel
  */
  protected void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
  }
}
