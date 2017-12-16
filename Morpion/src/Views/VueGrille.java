package Views;
import com.sun.org.apache.xml.internal.security.utils.JDKXPathAPI;

import java.awt.*;
import javax.swing.*;

public class VueGrille {

        private final JFrame window;

        public VueGrille(){

            window = new JFrame();
            window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            window.setSize(1280,720);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
            window.setTitle("Jeu du Morpion");

            window.setLayout(new BorderLayout());

            /*window.getContentPane().add(new JButton("Center"),BorderLayout.CENTER);
            window.getContentPane().add(new JButton("North"),BorderLayout.NORTH);
            window.getContentPane().add(new JButton("South"),BorderLayout.SOUTH);
            window.getContentPane().add(new JButton("East"),BorderLayout.EAST);
            window.getContentPane().add(new JButton("West"),BorderLayout.WEST);

            window.getContentPane().setPreferredSize(new Dimension(100,100 )); */

            JPanel mainPanel = new JPanel(new BorderLayout());
            window.add(mainPanel);

            JButton boutton1 = new JButton("Nord");
            boutton1.setPreferredSize(new Dimension(100,100));

            //Construction panel principal

            JPanel hautPanel = new JPanel();
            mainPanel.add(hautPanel, BorderLayout.NORTH);
            hautPanel.add(boutton1);

            JPanel basPanel = new JPanel();
            mainPanel.add(basPanel, BorderLayout.SOUTH);
            basPanel.add(new JLabel("bas"));

            JPanel droitPanel = new JPanel();
            mainPanel.add(droitPanel, BorderLayout.EAST);
            droitPanel.add(new JButton("droit"));

            JPanel gauchePanel = new JPanel();
            mainPanel.add(gauchePanel, BorderLayout.WEST);
            gauchePanel.add(new JLabel("gauche"));

            JPanel centrePanel = new JPanel();
            mainPanel.add(centrePanel, BorderLayout.CENTER);

            //Construction panel grille (dans centre de panel principal)

            JPanel grillePanel = new JPanel(new BorderLayout());
            centrePanel.add(grillePanel);

            JPanel centreGrillePanel = new JPanel(new GridLayout(3,3));
            grillePanel.add(centreGrillePanel, BorderLayout.CENTER);

            for (int i = 0; i < 9 ; i++){
                centreGrillePanel.add(new JButton("lol"));
            }

            JPanel basGrillePanel = new JPanel();
            grillePanel.add(basGrillePanel, BorderLayout.SOUTH);

            JButton btnAnnuler = new JButton("Annuler");
            basGrillePanel.add(btnAnnuler);





            window.setVisible(true);

        }

    public void afficher(){
        this.window.setVisible(true);
    }

}

