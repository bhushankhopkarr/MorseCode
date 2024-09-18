import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // invokeLater insures that the GUI is created and updated on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MorseCodeTranslatorGUI().setVisible(true);;
            }
        });
    }

}