import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main extends JFrame{
    private static Map<String, String[]> usersMap;

    public static void main(String[] args) {
        Conexion conexion = new Conexion("videojuegosdb");
        Connection conn = conexion.conectar();

        usersMap = loadUsersFromFile("users.txt");

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Main Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // Main container with BorderLayout
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Characters"));
        northPanel.add(new JButton("Ranking"));
        northPanel.add(new JButton("Missions"));
        northPanel.add(new JButton("Regions"));
        northPanel.add(new JButton("News"));
        mainContainer.add(northPanel, BorderLayout.NORTH);

        // South Area with BoxLayout and height of 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton loginButton = new JButton("Log in");

        // Adding ActionListener for the log in button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show login form
                showLoginForm(frame);
            }
        });

        southPanel.add(loginButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        mainContainer.add(southPanel, BorderLayout.SOUTH);

        // Center with GridLayout simulating a grid of games
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Creating custom panels to represent character types
        centerPanel.add(createCharacterPanel("Warrior", "Strong and resilient", "./photos/warrior.jfif"));
        centerPanel.add(createCharacterPanel("Archer", "Precise and lethal", "./photos/archer.jfif"));
        centerPanel.add(createCharacterPanel("Wizard", "Master of magical power", "./photos/wizard.jfif"));
        centerPanel.add(createCharacterPanel("Summoner", "Creature controller", "./photos/summoner.jfif"));
        centerPanel.add(createCharacterPanel("Hunter", "Voracious hunter", "./photos/granyhunter.png"));
        centerPanel.add(createCharacterPanel("Tank", "Team shield", "./photos/tank.jfif"));
        centerPanel.add(createCharacterPanel("Slayer", "Unstoppable berserker", "./photos/milfslayer.jfif"));
        centerPanel.add(createCharacterPanel("Assassin", "Swift and stealthy", "./photos/assassin.jfif"));
        centerPanel.add(createCharacterPanel("Paladin", "Team support", "./photos/paladin.jfif"));
        centerPanel.add(createCharacterPanel("Coming Soon", "Exciting things coming", "./photos/new.jfif"));

        mainContainer.add(centerPanel, BorderLayout.CENTER);



        frame.setVisible(true);
    }

    // Method to create a custom panel to represent a character type
    private static JPanel createCharacterPanel(String name, String description, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Label for the image
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Image img = icon.getImage();
                Image newImg = img.getScaledInstance(icon.getIconWidth() + 50, icon.getIconHeight() + 50, java.awt.Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(newImg));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imageLabel.setIcon(icon);
            }
        });
        panel.add(imageLabel, BorderLayout.CENTER);

        // Panel for name and description
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel(name);
        JLabel descriptionLabel = new JLabel(description);
        infoPanel.add(nameLabel);
        infoPanel.add(descriptionLabel);

        panel.add(infoPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static void showLoginForm(JFrame parentFrame) {
        JFrame loginFrame = new JFrame("Log In");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField();
        JButton loginButton = new JButton("Log in");
        JButton cancelButton = new JButton("Cancel");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                char[] password = passwordText.getPassword();
                String passwordString = new String(password);
                int userType = authenticateUser(username, passwordString);
                if (userType == 0) {
                    // Log in as user
                    loginFrame.dispose(); // Close login frame
                    parentFrame.dispose(); // Close current frame
                    JFrame userFrame = new JFrame("User"); // Create a new frame for the user
                    userFrame.getContentPane().add(createUserPanel());
                    userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    userFrame.setSize(1000, 600);
                    userFrame.setVisible(true);
                } else if (userType == 1) {
                    // Log in as admin
                    loginFrame.dispose(); // Close login frame
                    parentFrame.dispose(); // Close current frame
                    JFrame adminFrame = new JFrame("Admin"); // Create a new frame for the admin
                    adminFrame.getContentPane().add(createAdminPanel());
                    adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    adminFrame.setSize(1000, 600);
                    adminFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Incorrect username or password.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose(); // Close login frame
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

    // Method to authenticate the user
    private static int authenticateUser(String username, String password) {
        if (usersMap.containsKey(username)) {
            String[] userData = usersMap.get(username);
            if (userData[0].equals(password)) {
                return Integer.parseInt(userData[1]);
            }
        }
        return -1;
    }

    // Method to load users from file
    private static Map<String, String[]> loadUsersFromFile(String filename) {
        Map<String, String[]> users = new HashMap<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" ");
                users.put(data[0], new String[]{data[1], data[2]});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }
        return users;
    }

    private static JPanel createUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Characters"));
        northPanel.add(new JButton("Messages"));
        northPanel.add(new JButton("Ranking"));
        northPanel.add(new JButton("Missions"));
        northPanel.add(new JButton("Regions"));
        northPanel.add(new JButton("Guild"));
        northPanel.add(new JButton("News"));
        panel.add(northPanel, BorderLayout.NORTH);

        // South Area with BoxLayout and height of 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Log out and restart the application
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                topFrame.dispose(); // Close current frame
                createAndShowGUI(); // Restart the application
            }
        });

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);

        // Center with GridLayout simulating a grid of characters
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Creating custom panels to represent user's characters
        centerPanel.add(createCharacterPanel("Chayane", "Summoner Level: 90", "./photos/summoner.jfif"));
        centerPanel.add(createCharacterPanel("Gandalf", "Wizard Level: 45", "./photos/wizard.jfif"));
        centerPanel.add(createCharacterPanel("Ibai", "Tank Level: 10", "./photos/tank.jfif"));
        centerPanel.add(createCharacterPanel("Jesus", "Paladin Level: 33", "./photos/paladin.jfif"));
        centerPanel.add(createCharacterPanel("Add New Character", "", "./photos/add.png"));

        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("User Management"));
        northPanel.add(new JButton("Guild Management"));
        northPanel.add(new JButton("Advanced Statistics"));
        northPanel.add(new JButton("System"));
        northPanel.add(new JButton("Support"));
        panel.add(northPanel, BorderLayout.NORTH);

        // South Area with BoxLayout and height of 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Log out and restart the application
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                topFrame.dispose(); // Close current frame
                createAndShowGUI(); // Restart the application
            }
        });

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);

        // Center with BoxLayout divided into three parts
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));

        // Panel for user list on the left
        JList<String> userList = createUserListPanel();
        centerPanel.add(new JScrollPane(userList));

        // Panel for character list on the top right
        JList<String> charactersList = createCharactersListPanel();
        centerPanel.add(new JScrollPane(charactersList));

        // Panel for possible changes on the bottom right
        // You should implement or remove this panel based on your needs
        // centerPanel.add(createChangesPanel());

        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    // Method to create a JList containing the list of users
    private static JList<String> createUserListPanel() {
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        for (String user : usersMap.keySet()) {
            userListModel.addElement(user);
        }
        return new JList<>(userListModel);
    }

    // Method to create a JList containing the list of characters
    private static JList<String> createCharactersListPanel() {
        DefaultListModel<String> charactersListModel = new DefaultListModel<>();
        // You can load characters here in some way
        return new JList<>(charactersListModel);
    }
}
