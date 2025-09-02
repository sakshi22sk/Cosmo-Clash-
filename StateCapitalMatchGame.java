import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class StateCapitalMatchGame extends JFrame {
    private final Map<String, String> correctPairs = new HashMap<>();
    private final List<JButton> stateButtons = new ArrayList<>();
    private final List<JButton> capitalButtons = new ArrayList<>();
    private final Map<JButton, Point> buttonCenters = new HashMap<>();
    private final List<Pair<JButton, JButton>> userMatches = new ArrayList<>();

    private JButton selectedState = null;
    private MatchCanvas canvas;

    public StateCapitalMatchGame() {
        setTitle("State-Capital Match");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0x898AA6));

        setLayout(null);

        loadCorrectPairs();

        canvas = new MatchCanvas();
        canvas.setBounds(0, 0, 1000, 600);
        canvas.setOpaque(false);
        add(canvas);

        addStateAndCapitalButtons();

        JButton submitBtn = new JButton("SUBMIT");
        submitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        submitBtn.setBounds(420, 500, 150, 40);
        submitBtn.setBackground(Color.BLACK);
        submitBtn.setForeground(Color.WHITE);
        add(submitBtn);

        submitBtn.addActionListener(e -> evaluateResult());

        setVisible(true);
    }

    private void loadCorrectPairs() {
        correctPairs.put("Punjab", "Chandigarh");
        correctPairs.put("Maharashtra", "Mumbai");
        correctPairs.put("Karnataka", "Bengaluru");
        correctPairs.put("Tamil Nadu", "Chennai");
        correctPairs.put("West Bengal", "Kolkata");
        correctPairs.put("Rajasthan", "Jaipur");
    }

    private void addStateAndCapitalButtons() {
        String[] states = correctPairs.keySet().toArray(new String[0]);
        String[] capitals = correctPairs.values().toArray(new String[0]);
        Collections.shuffle(Arrays.asList(capitals));

        int stateStartY = 80;
        int capitalStartY = 80;

        for (int i = 0; i < 6; i++) {
            JButton stateBtn = new JButton(states[i]);
            stateBtn.setBounds(100, stateStartY, 200, 40);
            styleButton(stateBtn);
            add(stateBtn);
            stateButtons.add(stateBtn);

            JButton capBtn = new JButton(capitals[i]);
            capBtn.setBounds(700, capitalStartY, 200, 40);  // Moved left from 1500 to 700
            styleButton(capBtn);
            add(capBtn);
            capitalButtons.add(capBtn);

            Point stateCenter = new Point(stateBtn.getX() + 100, stateBtn.getY() + 20);
            Point capitalCenter = new Point(capBtn.getX(), capBtn.getY() + 20);
            buttonCenters.put(stateBtn, stateCenter);
            buttonCenters.put(capBtn, capitalCenter);

            stateBtn.addActionListener(e -> selectedState = stateBtn);

            capBtn.addActionListener(e -> {
                if (selectedState != null && !alreadyMatched(selectedState) && !alreadyMatched(capBtn)) {
                    userMatches.add(new Pair<>(selectedState, capBtn));
                    selectedState.setEnabled(false);
                    capBtn.setEnabled(false);
                    selectedState = null;
                    canvas.repaint();
                }
            });

            stateStartY += 60;
            capitalStartY += 60;
        }
    }

    private boolean alreadyMatched(JButton btn) {
        for (Pair<JButton, JButton> pair : userMatches) {
            if (pair.first == btn || pair.second == btn)
                return true;
        }
        return false;
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(0xFEC8D8));
        btn.setForeground(Color.BLACK);
    }

    private void evaluateResult() {
        int score = 0;
        for (Pair<JButton, JButton> pair : userMatches) {
            String state = pair.first.getText();
            String capital = pair.second.getText();
            if (correctPairs.containsKey(state) && correctPairs.get(state).equals(capital)) {
                score++;
            }
        }

        JOptionPane.showMessageDialog(this, "You scored " + score + "/6");
    }

    class Pair<F, S> {
        F first;
        S second;

        Pair(F f, S s) {
            first = f;
            second = s;
        }
    }

    class MatchCanvas extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));

            for (Pair<JButton, JButton> pair : userMatches) {
                Point p1 = buttonCenters.get(pair.first);
                Point p2 = buttonCenters.get(pair.second);
                g2.drawLine(p1.x + 100, p1.y, p2.x, p2.y);
            }
        }
    }

    public static void main(String[] args) {
        new StateCapitalMatchGame();
    }
}
