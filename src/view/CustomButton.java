package view;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.net.URL;
import java.awt.Graphics;

/**
*class qui permet de créer des JButton custom qui permet de gérer les images plus simplement
*@author Q.Le Lan
*/
public class CustomButton extends JButton{

  /**
  *l'image du bouton
  */
  private ImageIcon image_background;

  /**
  *le construteur de CustomButton
  *@param image_path le chemin de l'image à afficher dans le bouton
  */
  public CustomButton(URL image_path){
      this.image_background = new ImageIcon(image_path);
      this.setBorderPainted(false);
  }

  /**
  *setter de l'image du bouton
  *@param image_path le chemin de l'image à afficher dans le bouton
  */
  public void setImageBackground(URL image_path){
      this.image_background = new ImageIcon(image_path);
  }

  /**
  *permet de peindre le bouton avec l'image
  *@param g le graphics à ajouter au bouton
  */
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(image_background.getImage(), 0, 0, getWidth(), getHeight(), this);
  }
}
