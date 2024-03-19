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
        northPanel.add(new JButton("Perfil"));
        northPanel.add(new JButton("Personajes"));
        northPanel.add(new JButton("Clasificación"));
        northPanel.add(new JButton("Misiones"));
        northPanel.add(new JButton("Region"));
        northPanel.add(new JButton("Gremio"));
        northPanel.add(new JButton("Noticias"));
        mainContainer.add(northPanel, BorderLayout.NORTH);

        // Área Sur con BoxLayout y altura de 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton loginButton = new JButton("Cerrar sesión");
        // Agregar ActionListener para el botón de inicio de sesión
        // Todavia no implementar

        southPanel.add(loginButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        mainContainer.add(southPanel, BorderLayout.SOUTH);

        // Centro con BoxLayout simulando una cuadrícula de juegos
        Box centerBox = Box.createVerticalBox();
        centerBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor del panel

        // Crear panel para ver el perfil de un personaje
        centerBox.add(createPersonajePanel("Username", "Invocador", "./photos/invocador.jfif", "4500 y 250", "prueba", "prueba"));

        mainContainer.add(centerBox, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Método para crear un panel personalizado para representar un tipo de personaje
    private static Box createPersonajePanel(String nombre, String tipo, String imagePath, String vidaMana, String estadisticas, String habilidades) {
        Box panel = Box.createVerticalBox(); // Cambiar a Box vertical

        // Primera fila: Nombre de usuario, tipo y foto del personaje
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // Cambiar el layout a Box vertical

        // Ajustar el tamaño mínimo del panel superior
        topPanel.setMinimumSize(new Dimension(topPanel.getPreferredSize().width, 300)); // Cambiar 300 a la altura deseada

        // Etiqueta para el nombre de usuario
        JLabel nombreLabel = new JLabel("Nombre: " + nombre);
        topPanel.add(nombreLabel); // Añadir la etiqueta del nombre

        // Etiqueta para el tipo de personaje
        JLabel tipoLabel = new JLabel("Tipo: " + tipo);
        topPanel.add(tipoLabel); // Añadir la etiqueta del tipo

        // Etiqueta para la foto del personaje (se asume que se proporciona la ruta de la imagen)
        JLabel imagenLabel = new JLabel(new ImageIcon(imagePath));
        topPanel.add(imagenLabel); // Añadir la imagen

        panel.add(topPanel);

        // Segunda fila: Vida y mana del personaje
        JPanel vidaManaPanel = new JPanel();
        vidaManaPanel.setLayout(new FlowLayout());
        JLabel vidaManaLabel = new JLabel("Vida y Mana: " + vidaMana);
        vidaManaPanel.add(vidaManaLabel);
        panel.add(vidaManaPanel);

        // Tercera fila: Estadísticas del personaje
        JPanel estadisticasPanel = new JPanel();
        estadisticasPanel.setLayout(new FlowLayout());
        JLabel estadisticasLabel = new JLabel("Estadísticas: " + estadisticas);
        estadisticasPanel.add(estadisticasLabel);
        panel.add(estadisticasPanel);

        // Cuarta fila: Objetos y habilidades del personaje
        JPanel habilidadesPanel = new JPanel();
        habilidadesPanel.setLayout(new FlowLayout());
        JLabel habilidadesLabel = new JLabel("Habilidades: " + habilidades);
        habilidadesPanel.add(habilidadesLabel);
        panel.add(habilidadesPanel);

        return panel;
    }

}
