
package Proyecte_Civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

    private Civilization civ;
    private TopPanel topPanel;

    public MainWindow(Civilization civ) 
    {
        this.civ = civ;

        setTitle("Civilizations - Kai Edition");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        topPanel = new TopPanel(civ);
        add(topPanel, BorderLayout.NORTH);
        civ.startResourceTimer(topPanel);

        add(new CenterPanel(), BorderLayout.CENTER);
        add(new BottomPanel(civ, this), BorderLayout.SOUTH);

        setVisible(true);
    }

    public TopPanel getTopPanel() 
    {
        return topPanel;
    }
}

class TopPanel extends JPanel {

    private Civilization civ;

    private JLabel lblFood, lblWood, lblIron, lblMana;
    private JLabel lblFarm, lblCarp, lblSmithy, lblMagic, lblChurch;

    public TopPanel(Civilization civ) 
    {
        this.civ = civ;

        setLayout(new GridLayout(1, 9));
        setPreferredSize(new Dimension(1280, 90));
        setBackground(new Color(30, 30, 30));

        lblFood   = crearHUDItem("/img/Comida.png", civ.getFood());
        lblWood   = crearHUDItem("/img/Madera.png", civ.getWood());
        lblIron   = crearHUDItem("/img/Hierro.png", civ.getIron());
        lblMana   = crearHUDItem("/img/Mana.png", civ.getMana());

        lblFarm   = crearHUDItem("/img/Granja.png", civ.getFarm());
        lblCarp   = crearHUDItem("/img/Carpinteria.png", civ.getCarpentry());
        lblSmithy = crearHUDItem("/img/Herreria.png", civ.getSmithy());
        lblMagic  = crearHUDItem("/img/TorreMagica.png", civ.getMagicTower());
        lblChurch = crearHUDItem("/img/Iglesia.png", civ.getChurch());

        add(lblFood);
        add(lblWood);
        add(lblIron);
        add(lblMana);
        add(lblFarm);
        add(lblCarp);
        add(lblSmithy);
        add(lblMagic);
        add(lblChurch);
    }

    private JLabel crearHUDItem(String ruta, int valor) 
    {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(String.valueOf(valor), new ImageIcon(img), JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        return label;
    }

    public void refresh() 
    {
        lblFood.setText(String.valueOf(civ.getFood()));
        lblWood.setText(String.valueOf(civ.getWood()));
        lblIron.setText(String.valueOf(civ.getIron()));
        lblMana.setText(String.valueOf(civ.getMana()));

        lblFarm.setText(String.valueOf(civ.getFarm()));
        lblCarp.setText(String.valueOf(civ.getCarpentry()));
        lblSmithy.setText(String.valueOf(civ.getSmithy()));
        lblMagic.setText(String.valueOf(civ.getMagicTower()));
        lblChurch.setText(String.valueOf(civ.getChurch()));
    }
}

class CenterPanel extends JPanel {

    private Image fondo;

    public CenterPanel() 
    {
        fondo = new ImageIcon(getClass().getResource("/img/Fondo.png")).getImage();
    }

    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}

class BottomPanel extends JPanel {

    public BottomPanel(final Civilization civ, final MainWindow mainWindow) 
    {
        setLayout(new GridLayout(1, 4));
        setBackground(new Color(50, 50, 50));

        JButton edificios = botonImagen("/img/Carpinteria.png", "Edificios");
        JButton tropas = botonImagen("/img/Tropas.png", "Tropas");
        JButton tecnologia = botonImagen("/img/libromagico.png", "Tecnología");
        JButton stats = botonImagen("/img/escudo.png", "Stats Tropas");

        edificios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowEdificios(civ, mainWindow);
            }
        });

        tropas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowTropas(civ, mainWindow);
            }
        });

        tecnologia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowTecnologia(civ, mainWindow);
            }
        });

        stats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowStatsTropas(civ);
            }
        });

        add(edificios);
        add(tropas);
        add(tecnologia);
        add(stats);
    }

    private JButton botonImagen(String ruta, String texto) 
    {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        Image img = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);

        JButton b = new JButton(texto, new ImageIcon(img));

        b.setHorizontalTextPosition(JButton.CENTER);
        b.setVerticalTextPosition(JButton.BOTTOM);

        b.setBackground(new Color(80, 60, 40));
        b.setForeground(Color.WHITE);
        b.setBorder(BorderFactory.createLineBorder(new Color(200, 170, 80), 3));
        b.setFocusPainted(false);

        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) 
            {
                b.setBackground(new Color(110, 85, 55));
            }
            public void mouseExited(MouseEvent evt) 
            {
                b.setBackground(new Color(80, 60, 40));
            }
        });

        return b;
    }
}


class WindowEdificios extends JFrame {

 public WindowEdificios(final Civilization civ, final MainWindow mainWindow) 
 {
     setTitle("Build Structures");
     setSize(900, 200);
     setLayout(new GridLayout(1, 5)); 
     getContentPane().setBackground(new Color(40, 40, 40));
     setLocationRelativeTo(null);

     add(crearBotonEdificio("/img/Granja.png", "Farm", 
         civ.getFarmFoodCost(), civ.getFarmWoodCost(), civ.getFarmIronCost(), 0,
         new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 int before = civ.getFood();
                 civ.newFarm();
                 if (civ.getFood() < before) {
                     JOptionPane.showMessageDialog(null, "Farm built. Total: " + civ.getFarm());
                     mainWindow.getTopPanel().refresh();
                 } else {
                     JOptionPane.showMessageDialog(null, "Not enough resources.");
                 }
             }
         }
     ));

     add(crearBotonEdificio("/img/Carpinteria.png", "Carpentry", 
         civ.getCarpentryFoodCost(), civ.getCarpentryWoodCost(), civ.getCarpentryIronCost(), 0,
         new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 int before = civ.getFood();
                 civ.newCarpentry();
                 if (civ.getFood() < before) {
                     JOptionPane.showMessageDialog(null, "Carpentry built. Total: " + civ.getCarpentry());
                     mainWindow.getTopPanel().refresh();
                 } else {
                     JOptionPane.showMessageDialog(null, "Not enough resources.");
                 }
             }
         }
     ));

     add(crearBotonEdificio("/img/Herreria.png", "Smithy", 
         civ.getSmithyFoodCost(), civ.getSmithyWoodCost(), civ.getSmithyIronCost(), 0,
         new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 int before = civ.getFood();
                 civ.newSmithy();
                 if (civ.getFood() < before) {
                     JOptionPane.showMessageDialog(null, "Smithy built. Total: " + civ.getSmithy());
                     mainWindow.getTopPanel().refresh();
                 } else {
                     JOptionPane.showMessageDialog(null, "Not enough resources.");
                 }
             }
         }
     ));

     add(crearBotonEdificio("/img/TorreMagica.png", "Magic Tower", 
         civ.getMagicTowerFoodCost(), civ.getMagicTowerWoodCost(), civ.getMagicTowerIronCost(), 0,
         new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 int before = civ.getFood();
                 civ.newMagicTower();
                 if (civ.getFood() < before) {
                     JOptionPane.showMessageDialog(null, "Magic Tower built. Total: " + civ.getMagicTower());
                     mainWindow.getTopPanel().refresh();
                 } else {
                     JOptionPane.showMessageDialog(null, "Not enough resources.");
                 }
             }
         }
     ));

     add(crearBotonEdificio("/img/Iglesia.png", "Church", 
         civ.getChurchFoodCost(), civ.getChurchWoodCost(), civ.getChurchIronCost(), Variables.MANA_COST_CHURCH,
         new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 int before = civ.getFood();
                 civ.newChurch();
                 if (civ.getFood() < before) {
                     JOptionPane.showMessageDialog(null, "Church built. Total: " + civ.getChurch());
                     mainWindow.getTopPanel().refresh();
                 } else {
                     JOptionPane.showMessageDialog(null, "Not enough resources.");
                 }
             }
         }
     ));

     setVisible(true);
 }

 private JButton crearBotonEdificio(String ruta, String nombre, int food, int wood, int iron, int mana, ActionListener action)
 {
     ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
     Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

     JButton b = new JButton(nombre, new ImageIcon(img));
     b.setHorizontalTextPosition(JButton.CENTER);
     b.setVerticalTextPosition(JButton.BOTTOM);

     b.setBackground(new Color(70, 50, 30));
     b.setForeground(Color.WHITE);
     b.setFont(new Font("Arial", Font.BOLD, 14));
     b.setBorder(BorderFactory.createLineBorder(new Color(200, 170, 80), 3));

     String tooltip =
         "<html><body style='text-align:center;'>"
         + "<b>" + nombre + "</b><br>"
         + "Food: " + food + "<br>"
         + "Wood: " + wood + "<br>"
         + "Iron: " + iron;

     if (mana > 0)
     {
         tooltip = tooltip + "<br>Mana: " + mana;
     }

     tooltip = tooltip + "</body></html>";

     b.setToolTipText(tooltip);

     b.addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent e) 
         {
             b.setBackground(new Color(100, 75, 45));
         }
         public void mouseExited(MouseEvent e) 
         {
             b.setBackground(new Color(70, 50, 30));
         }
     });

     b.addActionListener(action);

     return b;
 }
}



class WindowTropas extends JFrame 
{
 public WindowTropas(final Civilization civ, final MainWindow mainWindow) 
 {
     setTitle("Select Troop Type");
     setSize(500, 600);
     setLayout(new GridLayout(3, 3));
     getContentPane().setBackground(new Color(40, 40, 40));
     setLocationRelativeTo(null);

     add(crearBotonTropa("/img/Espadachin.png", "Swordsman", 1, civ, mainWindow));
     add(crearBotonTropa("/img/Lancero.png", "Spearman", 2, civ, mainWindow));
     add(crearBotonTropa("/img/Ballestero.png", "Crossbowman", 3, civ, mainWindow));

     add(crearBotonTropa("/img/Artillero.png", "Cannon", 4, civ, mainWindow));
     add(crearBotonTropa("/img/TorreLanza.png", "Arrow Tower", 5, civ, mainWindow));
     add(crearBotonTropa("/img/Catapulta.png", "Catapult", 6, civ, mainWindow));

     add(crearBotonTropa("/img/TorreCohetes.png", "Rocket Tower", 7, civ, mainWindow));
     add(crearBotonTropa("/img/Mago.png", "Mage", 8, civ, mainWindow));
     add(crearBotonTropa("/img/Sacerdote.png", "Priest", 9, civ, mainWindow));

     setVisible(true);
 }
 private JButton crearBotonTropa(String ruta, String nombre, int tipo, Civilization civ, MainWindow mainWindow)
 {
     ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
     Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

     JButton b = new JButton(nombre, new ImageIcon(img));
     b.setHorizontalTextPosition(JButton.CENTER);
     b.setVerticalTextPosition(JButton.BOTTOM);

     b.setBackground(new Color(70, 50, 30));
     b.setForeground(Color.WHITE);
     b.setFont(new Font("Arial", Font.BOLD, 14));
     b.setBorder(BorderFactory.createLineBorder(new Color(200, 170, 80), 3));

     String tooltip = 
         "<html><body style='text-align:center;'>"
         + "<b>" + nombre + "</b><br>"
         + "Food: " + civ.getUnitFoodCost(tipo) + "<br>"
         + "Wood: " + civ.getUnitWoodCost(tipo) + "<br>"
         + "Iron: " + civ.getUnitIronCost(tipo);

     if (tipo == 8)
     {
         tooltip = tooltip + "<br>Mana: " + Variables.MANA_COST_MAGICIAN;
     }
     else if (tipo == 9)
     {
         tooltip = tooltip + "<br>Mana: " + Variables.MANA_COST_PRIEST;
     }

     tooltip = tooltip + "</body></html>";

     b.setToolTipText(tooltip);

     b.addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent e) 
         {
             b.setBackground(new Color(100, 75, 45));
         }
         public void mouseExited(MouseEvent e) 
         {
             b.setBackground(new Color(70, 50, 30));
         }
     });

     b.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) 
         {
             new WindowCantidadTropa(civ, mainWindow, tipo, nombre);
         }
     });

     return b;
 }




}

class WindowCantidadTropa extends JFrame 
{
    public WindowCantidadTropa(final Civilization civ, final MainWindow mainWindow, final int tipo, String nombre) 
    {
        setTitle("Create " + nombre);
        setSize(260, 180);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));
        getContentPane().setBackground(new Color(45, 35, 25));

        JLabel titulo = new JLabel("Create " + nombre);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 22));
        titulo.setForeground(new Color(230, 200, 120));

        JTextField cantidad = new JTextField();
        cantidad.setHorizontalAlignment(JTextField.CENTER);
        cantidad.setFont(new Font("Arial", Font.BOLD, 18));
        cantidad.setBackground(new Color(70, 55, 40));
        cantidad.setForeground(Color.WHITE);
        cantidad.setBorder(BorderFactory.createLineBorder(new Color(200, 170, 80), 2));

        JButton crear = new JButton("Create");
        crear.setBackground(new Color(120, 90, 50));
        crear.setForeground(Color.WHITE);
        crear.setFont(new Font("Arial", Font.BOLD, 16));
        crear.setFocusPainted(false);
        crear.setBorder(BorderFactory.createLineBorder(new Color(200, 170, 80), 3));

        crear.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { crear.setBackground(new Color(150, 110, 60)); }
            public void mouseExited(MouseEvent e)  { crear.setBackground(new Color(120, 90, 50)); }
        });

        crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                int n;

                try {
                    n = Integer.parseInt(cantidad.getText());
                    if (n <= 0) {
                        JOptionPane.showMessageDialog(null, "Enter a number greater than 0.");
                        return;
                    }
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Enter a valid number.");
                    return;
                }
                switch (tipo) 
                {
                    case 1:{
                        civ.newSwordsman(n);
                        break;
                    }

                    case 2:{
                        civ.newSpearman(n);
                        break;
                    }

                    case 3:{
                        civ.newCrossbow(n);
                        break;
                    }

                    case 4:{
                        civ.newCannon(n);
                        break;
                    }

                    case 5:{
                        civ.newArrowTower(n);
                        break;
                    }

                    case 6:{
                        civ.newCatapult(n);
                        break;
                    }

                    case 7:{
                        civ.newRocketLauncher(n);
                        break;
                    }

                    case 8:{
                        civ.newMagician(n);
                        break;
                    }

                    case 9:{
                        civ.newPriest(n);
                        break;
                    }
                }
                

                mainWindow.getTopPanel().refresh();
                dispose();
            }
        });

        add(titulo);
        add(cantidad);
        add(crear);

        setVisible(true);
    }
}


class WindowTecnologia extends JFrame {

 public WindowTecnologia(final Civilization civ, final MainWindow mainWindow) 
 {
     setTitle("Upgrade Technology");
     setSize(350, 220);
     setLayout(new GridLayout(2, 1));
     getContentPane().setBackground(new Color(40, 40, 40));
     setLocationRelativeTo(null);

     JButton atk = new JButton("Upgrade Attack (Lvl " + civ.getTechnologyAtack() + ")");
     atk.setToolTipText(
         "<html>Cost:<br>" +
         civ.getTechAttackIronCost() + " Iron<br>" +
         civ.getTechAttackWoodCost() + " Wood</html>"
     );

     JButton def = new JButton("Upgrade Defense (Lvl " + civ.getTechnologyDefense() + ")");
     def.setToolTipText(
         "<html>Cost:<br>" +
         civ.getTechDefenseIronCost() + " Iron<br>" +
         civ.getTechDefenseWoodCost() + " Wood</html>"
     );

     // Apply Medieval Gold theme
     styleTechButton(atk);
     styleTechButton(def);

     atk.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             civ.upgradeTechnologyAttack();
             mainWindow.getTopPanel().refresh();
             dispose();
             new WindowTecnologia(civ, mainWindow);
         }
     });

     def.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             civ.upgradeTechnologyDefense();
             mainWindow.getTopPanel().refresh();
             dispose();
             new WindowTecnologia(civ, mainWindow);
         }
     });

     add(atk);
     add(def);

     setVisible(true);
 }

 private void styleTechButton(JButton b)
 {
     b.setBackground(new Color(59, 47, 47)); // BASE
     b.setForeground(new Color(245, 230, 200)); // TEXT
     b.setFont(new Font("Arial", Font.BOLD, 18));
     b.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 106), 3));
     b.setFocusPainted(false);

     b.addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent e) {
        	 b.setBackground(new Color(90, 70, 56)); }
         public void mouseExited(MouseEvent e)  { 
        	 b.setBackground(new Color(59, 47, 47)); }
     });
 }
}

class WindowStatsTropas extends JFrame 
{
 public WindowStatsTropas(final Civilization civ) 
 {
     setTitle("Troop Statistics");
     setSize(400, 600);
     setLayout(new GridLayout(9, 1));
     getContentPane().setBackground(new Color(40, 40, 40));
     setLocationRelativeTo(null);

     add(crearFila("/img/Espadachin.png", "Swordsman",  civ.getArmy()[0].size()));
     add(crearFila("/img/Lancero.png",    "Spearman",   civ.getArmy()[1].size()));
     add(crearFila("/img/Ballestero.png", "Crossbowman",civ.getArmy()[2].size()));
     add(crearFila("/img/Artillero.png",  "Cannon",     civ.getArmy()[3].size()));
     add(crearFila("/img/TorreLanza.png", "Arrow Tower",civ.getArmy()[4].size()));
     add(crearFila("/img/Catapulta.png",  "Catapult",   civ.getArmy()[5].size()));
     add(crearFila("/img/TorreCohetes.png","Rocket Tower", civ.getArmy()[6].size()));
     add(crearFila("/img/Mago.png",       "Mage",       civ.getArmy()[7].size()));
     add(crearFila("/img/Sacerdote.png",  "Priest",     civ.getArmy()[8].size()));

     setVisible(true);
 }

 private JPanel crearFila(String ruta, String nombre, int cantidad) 
 {
     JPanel fila = new JPanel(new GridLayout(1, 3));
     fila.setBackground(new Color(30, 30, 30));

     ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
     Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

     JLabel icono = new JLabel(new ImageIcon(img));
     icono.setHorizontalAlignment(JLabel.CENTER);

     JLabel texto = new JLabel(nombre);
     texto.setForeground(Color.WHITE);
     texto.setFont(new Font("Arial", Font.BOLD, 16));
     texto.setHorizontalAlignment(JLabel.CENTER);

     JLabel num = new JLabel(String.valueOf(cantidad));
     num.setForeground(Color.YELLOW);
     num.setFont(new Font("Arial", Font.BOLD, 18));
     num.setHorizontalAlignment(JLabel.CENTER);

     fila.add(icono);
     fila.add(texto);
     fila.add(num);

     return fila;
 }
}

