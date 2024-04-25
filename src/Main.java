import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, String[]> usuariosMap;

    public static void main(String[] args) {
        usuariosMap = cargarUsuariosDesdeArchivo("usuarios.txt");

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Aplicación Principal");
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
        northPanel.add(new JButton("Noticias"));
        mainContainer.add(northPanel, BorderLayout.NORTH);

        // Área Sur con BoxLayout y altura de 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton loginButton = new JButton("Iniciar sesión");

        // Agregar ActionListener para el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar formulario de inicio de sesión
                showLoginForm(frame);
            }
        });

        southPanel.add(loginButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        mainContainer.add(southPanel, BorderLayout.SOUTH);

        // Centro con GridLayout simulando una cuadrícula de juegos
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor del panel

        // Crear paneles personalizados para representar los tipos de personajes
        centerPanel.add(createPersonajePanel("Guerrero", "Fuerte y resistente", "./photos/guerrero.jfif"));
        centerPanel.add(createPersonajePanel("Arquera", "Precisa y letal", "./photos/arquera.jfif"));
        centerPanel.add(createPersonajePanel("Hechicero", "Maestro del poder mágico", "./photos/hechicero.jfif"));
        centerPanel.add(createPersonajePanel("Invocador", "Controlador de criaturas", "./photos/invocador.jfif"));
        centerPanel.add(createPersonajePanel("GranyHunter", "Cazador voraz", "./photos/granyhunter.png"));
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

    private static void showLoginForm(JFrame parentFrame) {
        JFrame loginFrame = new JFrame("Inicio de Sesión");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordText = new JPasswordField();
        JButton loginButton = new JButton("Iniciar sesión");
        JButton cancelButton = new JButton("Cancelar");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userText.getText();
                char[] password = passwordText.getPassword();
                String contraseña = new String(password);
                int tipoUsuario = authenticateUser(usuario, contraseña);
                if (tipoUsuario == 0) {
                    // Iniciar sesión como usuario
                    loginFrame.dispose(); // Cerrar el frame de inicio de sesión
                    parentFrame.dispose(); // Cerrar el frame actual
                    JFrame userFrame = new JFrame("Usuario"); // Crear un nuevo frame para el usuario
                    // Crear y mostrar el panel del usuario
                    userFrame.getContentPane().add(createUserPanel());
                    userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    userFrame.setSize(1000, 600);
                    userFrame.setVisible(true);
                } else if (tipoUsuario == 1) {
                    // Iniciar sesión como administrador
                    loginFrame.dispose(); // Cerrar el frame de inicio de sesión
                    parentFrame.dispose(); // Cerrar el frame actual
                    JFrame adminFrame = new JFrame("Admin"); // Crear un nuevo frame para el administrador
                    // Crear y mostrar el panel del administrador
                    adminFrame.getContentPane().add(createAdminPanel());
                    adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    adminFrame.setSize(1000, 600);
                    adminFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Usuario o contraseña incorrectos.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose(); // Cerrar el frame de inicio de sesión
            }
        });

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(cancelButton);
        panel.add(loginButton);

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }

    // Método para autenticar al usuario
    private static int authenticateUser(String usuario, String contraseña) {
        if (usuariosMap.containsKey(usuario)) {
            String[] userData = usuariosMap.get(usuario);
            if (userData[0].equals(contraseña)) {
                return Integer.parseInt(userData[1]);
            }
        }
        return -1;
    }

    // Método para cargar los usuarios desde el archivo
    private static Map<String, String[]> cargarUsuariosDesdeArchivo(String filename) {
        Map<String, String[]> usuarios = new HashMap<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(" ");
                usuarios.put(datos[0], new String[]{datos[1], datos[2]});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("No se pudo encontrar el archivo: " + filename);
            e.printStackTrace();
        }
        return usuarios;
    }

    private static JPanel createUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Área Norte con FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Personajes"));
        northPanel.add(new JButton("Mensajes"));
        northPanel.add(new JButton("Clasificación"));
        northPanel.add(new JButton("Misiones"));
        northPanel.add(new JButton("Regiones"));
        northPanel.add(new JButton("Gremio"));
        northPanel.add(new JButton("Noticias"));
        panel.add(northPanel, BorderLayout.NORTH);

        // Área Sur con BoxLayout y altura de 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton logoutButton = new JButton("Log out");
        // Agregar ActionListener para el botón de cierre de sesión
        // Todavía no implementado

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);

        // Centro con GridLayout simulando una cuadrícula de juegos
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor del panel

        // Crear paneles personalizados para representar los personajes del usuario
        centerPanel.add(createPersonajePanel("Chayane", "Invocador Nivel: 90", "./photos/invocador.jfif"));
        centerPanel.add(createPersonajePanel("Gandalf", "Hechicero Nivel: 45", "./photos/hechicero.jfif"));
        centerPanel.add(createPersonajePanel("Ibai", "Tanque Nivel: 10", "./photos/tanque.jfif"));
        centerPanel.add(createPersonajePanel("Jesus", "Paladin Nivel: 33", "./photos/paladin.jfif"));
        centerPanel.add(createPersonajePanel("Añadir nuevo personaje", "", "./photos/añadir.png"));

        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Área Norte con FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Gestion de Usuario"));
        northPanel.add(new JButton("Gestion de Gremios"));
        northPanel.add(new JButton("Estadisticas avanzadas"));
        northPanel.add(new JButton("Sistema"));
        northPanel.add(new JButton("Soporte"));
        panel.add(northPanel, BorderLayout.NORTH);

        // Área Sur con BoxLayout y altura de 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton loginButton = new JButton("Cerrar sesión");
        // Agregar ActionListener para el botón de cierre de sesión
        // Todavía no implementado

        southPanel.add(loginButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);

        // Centro con BoxLayout y división en tres partes
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));

        // Panel para la lista de usuarios a la izquierda
        JList<String> userList = createUserListPanel();
        centerPanel.add(new JScrollPane(userList));

        // Panel para la lista de personajes en la parte superior derecha
        JList<String> charactersList = createCharactersListPanel();
        centerPanel.add(new JScrollPane(charactersList));

        // Panel para los cambios posibles en la parte inferior derecha
        // Aquí deberías implementar o eliminar este panel según tus necesidades
        // centerPanel.add(createChangesPanel());

        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    // Método para crear un JList que contenga la lista de usuarios
    private static JList<String> createUserListPanel() {
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        for (String usuario : usuariosMap.keySet()) {
            userListModel.addElement(usuario);
        }
        return new JList<>(userListModel);
    }

    // Método para crear un JList que contenga la lista de personajes
    private static JList<String> createCharactersListPanel() {
        DefaultListModel<String> charactersListModel = new DefaultListModel<>();
        // Aquí podrías cargar los personajes de alguna manera
        return new JList<>(charactersListModel);
    }
}
