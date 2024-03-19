import javax.swing.*;
import java.awt.*;

public class Usuario {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Página Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // Contenedor principal con BorderLayout
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // Área Norte con FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Personajes"));
        northPanel.add(new JButton("Clasificación"));
        northPanel.add(new JButton("Misiones"));
        northPanel.add(new JButton("Regiones"));
        northPanel.add(new JButton("Gremios"));
        northPanel.add(new JButton("Noticias"));
        mainContainer.add(northPanel, BorderLayout.NORTH);

        // Área Sur con BoxLayout y altura de 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton loginButton = new JButton("Iniciar sesión");
        JButton signupButton = new JButton("Registrarse");
        // Agregar ActionListener para el botón de inicio de sesión
        // Todavia no implementar

        southPanel.add(loginButton);
        southPanel.add(signupButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        mainContainer.add(southPanel, BorderLayout.SOUTH);

        // Centro con GridLayout simulando una cuadrícula de juegos
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 3, 10, 10)); // 2 filas, 3 columnas, espacio horizontal y vertical de 10
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor del panel

        // Crear paneles personalizados para representar los tipos de personajes
        centerPanel.add(createPersonajePanel("Guerrero", "Fuerte y resistente", "./photos/guerrero.jfif"));
        centerPanel.add(createPersonajePanel("Arquera", "Precisa y letal", "./photos/arquera.jfif"));
        centerPanel.add(createPersonajePanel("Hechicero", "Maestro del poder mágico", "./photos/hechicero.jfif"));
        centerPanel.add(createPersonajePanel("Invocador", "Controlador de criaturas", "./photos/invocador.jfif"));
        centerPanel.add(createPersonajePanel("GranyHunter", "Cazador voraz", "./photos/granyhunter.jfif"));
        centerPanel.add(createPersonajePanel("Tanque", "Escudo del equipo", "./photos/tanque.jfif"));
        centerPanel.add(createPersonajePanel("MilfSlayer", "Berserker imparable", "./photos/milfslayer.jfif"));
        centerPanel.add(createPersonajePanel("Asesino", "Veloz y sigiloso", "./photos/asesino.jfif"));
        centerPanel.add(createPersonajePanel("Paladin", "Soporte del equipo", "./photos/paladin.jfif"));
        centerPanel.add(createPersonajePanel("Proximamente", "Se vienen cositas", "./photos/new.jfif"));

        mainContainer.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Método para crear un panel personalizado para representar un tipo de personaje
    private static JPanel createPersonajePanel(String nombre, String descripcion, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Etiqueta para la imagen
        JLabel imagenLabel = new JLabel(new ImageIcon(imagePath));
        panel.add(imagenLabel, BorderLayout.CENTER);

        // Panel para el nombre y descripción
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel nombreLabel = new JLabel(nombre);
        JLabel descripcionLabel = new JLabel(descripcion);
        infoPanel.add(nombreLabel);
        infoPanel.add(descripcionLabel);

        panel.add(infoPanel, BorderLayout.SOUTH);

        return panel;
    }
}
