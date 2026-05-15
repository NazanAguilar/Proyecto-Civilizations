
package Proyecte_Civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// MAIN WINDOW
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

        add(new CenterPanel(), BorderLayout.CENTER);
        add(new BottomPanel(civ, this), BorderLayout.SOUTH);

        setVisible(true);
    }

    public TopPanel getTopPanel() 
    {
        return topPanel;
    }
}

// TOP PANEL (HUD)
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

// CENTER PANEL (FONDO)
class CenterPanel extends JPanel {

    private Image fondo;

    public CenterPanel() 
    {
        fondo = new ImageIcon(getClass().getResource("/img/Fondo.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}

// BOTTOM PANEL 
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

// VENTANA EDIFICIOS 
class WindowEdificios extends JFrame {

    public WindowEdificios(final Civilization civ, final MainWindow mainWindow) 
    {
        setTitle("Construir Edificios");
        setSize(400, 400);
        setLayout(new GridLayout(5, 1));

        add(boton("Granja", "/img/Granja.png",
            "Coste: " + civ.getFarmFoodCost() + " comida, " +
                       civ.getFarmWoodCost() + " madera, " +
                       civ.getFarmIronCost() + " hierro",
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int antes = civ.getFood();
                    civ.newFarm();
                    if (civ.getFood() < antes) {
                        JOptionPane.showMessageDialog(null, "Granja construida. Total: " + civ.getFarm());
                        mainWindow.getTopPanel().refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay recursos suficientes.");
                    }
                }
            }
        ));

        add(boton("Carpintería", "/img/Carpinteria.png",
            "Coste: " + civ.getCarpentryFoodCost() + " comida, " +
                       civ.getCarpentryWoodCost() + " madera, " +
                       civ.getCarpentryIronCost() + " hierro",
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int antes = civ.getFood();
                    civ.newCarpentry();
                    if (civ.getFood() < antes) {
                        JOptionPane.showMessageDialog(null, "Carpintería construida. Total: " + civ.getCarpentry());
                        mainWindow.getTopPanel().refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay recursos suficientes.");
                    }
                }
            }
        ));

        add(boton("Herrería", "/img/Herreria.png",
            "Coste: " + civ.getSmithyFoodCost() + " comida, " +
                       civ.getSmithyWoodCost() + " madera, " +
                       civ.getSmithyIronCost() + " hierro",
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int antes = civ.getFood();
                    civ.newSmithy();
                    if (civ.getFood() < antes) {
                        JOptionPane.showMessageDialog(null, "Herrería construida. Total: " + civ.getSmithy());
                        mainWindow.getTopPanel().refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay recursos suficientes.");
                    }
                }
            }
        ));

        add(boton("Torre Mágica", "/img/TorreMagica.png",
            "Coste: " + civ.getMagicTowerFoodCost() + " comida, " +
                       civ.getMagicTowerWoodCost() + " madera, " +
                       civ.getMagicTowerIronCost() + " hierro",
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int antes = civ.getFood();
                    civ.newMagicTower();
                    if (civ.getFood() < antes) {
                        JOptionPane.showMessageDialog(null, "Torre Mágica construida. Total: " + civ.getMagicTower());
                        mainWindow.getTopPanel().refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay recursos suficientes.");
                    }
                }
            }
        ));

        add(boton("Iglesia", "/img/Iglesia.png",
            "Coste: " + civ.getChurchFoodCost() + " comida, " +
                       civ.getChurchWoodCost() + " madera, " +
                       civ.getChurchIronCost() + " hierro",
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int antes = civ.getFood();
                    civ.newChurch();
                    if (civ.getFood() < antes) {
                        JOptionPane.showMessageDialog(null, "Iglesia construida. Total: " + civ.getChurch());
                        mainWindow.getTopPanel().refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay recursos suficientes.");
                    }
                }
            }
        ));

        setVisible(true);
    }

    private JButton boton(String nombre, String ruta, String tooltip, ActionListener action) 
    {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JButton b = new JButton(nombre, new ImageIcon(img));
        b.addActionListener(action);
        b.setToolTipText(tooltip);

        return b;
    }
}

// VENTANA TROPAS 
class WindowTropas extends JFrame {

    public WindowTropas(final Civilization civ, final MainWindow mainWindow) 
    {
        setTitle("Crear Tropas");
        setSize(400, 600);
        setLayout(new GridLayout(9, 1));

        add(unidad("Espadachín", "/img/Espadachin.png", civ, mainWindow, 1));
        add(unidad("Lancero", "/img/Lancero.png", civ, mainWindow, 2));
        add(unidad("Ballestero", "/img/Ballestero.png", civ, mainWindow, 3));
        add(unidad("Cañón", "/img/Artillero.png", civ, mainWindow, 4));
        add(unidad("Torre Lanza", "/img/TorreLanza.png", civ, mainWindow, 5));
        add(unidad("Catapulta", "/img/Catapulta.png", civ, mainWindow, 6));
        add(unidad("Torre Cohetes", "/img/TorreCohetes.png", civ, mainWindow, 7));
        add(unidad("Mago", "/img/Mago.png", civ, mainWindow, 8));
        add(unidad("Sacerdote", "/img/Sacerdote.png", civ, mainWindow, 9));

        setVisible(true);
    }

    private JPanel unidad(String nombre, String ruta, final Civilization civ, final MainWindow mainWindow, final int tipo) 
    {
        JPanel p = new JPanel(new GridLayout(1, 3));

        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JButton b = new JButton(nombre, new ImageIcon(img));
        final JTextField cantidad = new JTextField();

        b.setToolTipText(
            "Coste por unidad: " +
            civ.getUnitFoodCost(tipo) + " comida, " +
            civ.getUnitWoodCost(tipo) + " madera, " +
            civ.getUnitIronCost(tipo) + " hierro"
        );

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n;

                try {
                    n = Integer.parseInt(cantidad.getText());
                    if (n <= 0) {
                        JOptionPane.showMessageDialog(null, "Introduce un número mayor que 0.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Solo puedes introducir números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                switch (tipo) {
                    case 1: civ.newSwordsman(n); break;
                    case 2: civ.newSpearman(n); break;
                    case 3: civ.newCrossbow(n); break;
                    case 4: civ.newCannon(n); break;
                    case 5: civ.newArrowTower(n); break;
                    case 6: civ.newCatapult(n); break;
                    case 7: civ.newRocketLauncher(n); break;
                    case 8: civ.newMagician(n); break;
                    case 9: civ.newPriest(n); break;
                }

                mainWindow.getTopPanel().refresh();
            }
        });

        p.add(b);
        p.add(cantidad);
        return p;
    }
}

// VENTANA TECNOLOGÍA
class WindowTecnologia extends JFrame {

    public WindowTecnologia(final Civilization civ, final MainWindow mainWindow) 
    {
        setTitle("Mejorar Tecnología");
        setSize(300, 200);
        setLayout(new GridLayout(2, 1));

        JButton atk = new JButton("Mejorar Ataque");
        atk.setToolTipText(
            "Coste: " +
            civ.getTechAttackIronCost() + " hierro, " +
            civ.getTechAttackWoodCost() + " madera"
        );

        JButton def = new JButton("Mejorar Defensa");
        def.setToolTipText(
            "Coste: " +
            civ.getTechDefenseIronCost() + " hierro, " +
            civ.getTechDefenseWoodCost() + " madera"
        );

        atk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                civ.upgradeTechnologyAttack();
                mainWindow.getTopPanel().refresh();
            }
        });

        def.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                civ.upgradeTechnologyDefense();
                mainWindow.getTopPanel().refresh();
            }
        });

        add(atk);
        add(def);

        setVisible(true);
    }
}

// VENTANA STATS TROPAS
class WindowStatsTropas extends JFrame 
{
    public WindowStatsTropas(final Civilization civ) 
    {
        setTitle("Estadísticas de Tropas");
        setSize(400, 600);
        setLayout(new GridLayout(9, 1));
        setBackground(new Color(40, 40, 40));

        add(crearFila("/img/Espadachin.png", "Espadachín", civ.getArmy()[0].size()));
        add(crearFila("/img/Lancero.png", "Lancero", civ.getArmy()[1].size()));
        add(crearFila("/img/Ballestero.png", "Ballestero", civ.getArmy()[2].size()));
        add(crearFila("/img/Artillero.png", "Cañón", civ.getArmy()[3].size()));
        add(crearFila("/img/TorreLanza.png", "Torre Lanza", civ.getArmy()[4].size()));
        add(crearFila("/img/Catapulta.png", "Catapulta", civ.getArmy()[5].size()));
        add(crearFila("/img/TorreCohetes.png", "Torre Cohetes", civ.getArmy()[6].size()));
        add(crearFila("/img/Mago.png", "Mago", civ.getArmy()[7].size()));
        add(crearFila("/img/Sacerdote.png", "Sacerdote", civ.getArmy()[8].size()));

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
