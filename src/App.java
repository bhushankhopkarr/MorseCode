import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Exception handling for potential issues during GUI initialization
        try {
            // Ensure the GUI is created and updated in a thread-safe manner
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new MorseCodeTranslatorGUI().setVisible(true);
                }
            });
        } catch (Exception e) {
            System.err.println("An Error Occurred: " + e.getMessage());
        }
    }
}