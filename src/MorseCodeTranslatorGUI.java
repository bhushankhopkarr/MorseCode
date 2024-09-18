import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.LineUnavailableException;

public class MorseCodeTranslatorGUI extends JFrame implements KeyListener {
    private MorseCodeController morseCodeController;
    private JTextArea textInputArea, morseCodeArea;
    private JButton translateButton, clearButton;

    public MorseCodeTranslatorGUI() {
        super("Morse Code Translator");

        // Set the size and disable resizing
        setSize(new Dimension(600, 800));
        setResizable(false);

        // Use a modern layout manager for better structure
        setLayout(new BorderLayout(10, 10));

        // Set the aesthetic background color
        getContentPane().setBackground(Color.decode("#264653"));

        // Place the GUI in the center of the screen
        setLocationRelativeTo(null);

        morseCodeController = new MorseCodeController();

        // Add the main content
        add(createMainPanel(), BorderLayout.CENTER);

        // Close operation
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setOpaque(false);  // Transparent background to inherit from parent

        // Create input area with padding and font changes
        textInputArea = new JTextArea();
        textInputArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textInputArea.setLineWrap(true);
        textInputArea.setWrapStyleWord(true);
        textInputArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#2a9d8f"), 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        textInputArea.setBackground(Color.decode("#e9c46a"));
        textInputArea.setForeground(Color.decode("#264653"));

        // Add ScrollPane to TextArea
        JScrollPane inputScroll = new JScrollPane(textInputArea);
        inputScroll.setBorder(BorderFactory.createTitledBorder("Text Input"));
        mainPanel.add(inputScroll, BorderLayout.NORTH);

        // Create output (morse code) area
        morseCodeArea = new JTextArea();
        morseCodeArea.setFont(new Font("Monospaced", Font.BOLD, 18));
        morseCodeArea.setLineWrap(true);
        morseCodeArea.setWrapStyleWord(true);
        morseCodeArea.setEditable(false);
        morseCodeArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e76f51"), 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        morseCodeArea.setBackground(Color.decode("#f4a261"));
        morseCodeArea.setForeground(Color.decode("#264653"));

        // Add ScrollPane to Morse Code Area
        JScrollPane outputScroll = new JScrollPane(morseCodeArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Morse Code Output"));
        mainPanel.add(outputScroll, BorderLayout.CENTER);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        // Translate Button
        translateButton = new JButton("Translate");
        styleButton(translateButton);
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textInputArea.getText();
                String morseCode = morseCodeController.translateToMorse(inputText);
                morseCodeArea.setText(morseCode);

                // Split Morse code by spaces to handle individual letters
                String[] morseMessage = morseCode.split(" ");

                // Create a new thread to play the sound
                new Thread(() -> {
                    try {
                        morseCodeController.playSound(morseMessage);
                    } catch (LineUnavailableException | InterruptedException ex) {
                        ex.printStackTrace(); // Handle exceptions appropriately
                    }
                }).start();
            }
        });
        buttonPanel.add(translateButton);

        // Clear Button
        clearButton = new JButton("Clear");
        styleButton(clearButton);
        clearButton.addActionListener(e -> {
            textInputArea.setText("");
            morseCodeArea.setText("");
        });
        buttonPanel.add(clearButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.decode("#2a9d8f"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}