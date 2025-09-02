import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Collections;
import javax.swing.Timer;

public class siyax_final extends JFrame {
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String currentUsername;

    public void showWelcomeScreen() {
        JFrame welcomeFrame = new JFrame("COSMO CLASH");
        welcomeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        welcomeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        welcomeFrame.setLayout(null);
    
        try {
            ImageIcon bgIcon = new ImageIcon("front_picc.jpg");
            Image scaled = bgIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            JLabel bg = new JLabel(new ImageIcon(scaled));
            bg.setBounds(0, 0, 1920, 1080);
            bg.setLayout(null);
    
            JLabel title = new JLabel("COSMO-CLASH");
            title.setFont(new Font("Arial", Font.BOLD, 64));
            title.setForeground(Color.WHITE);
            title.setBounds(100, 100, 1000, 80);
            bg.add(title);
    
            JLabel tagline = new JLabel("This isn't just a quiz, it's WAR for the crown!");
            tagline.setFont(new Font("Arial", Font.PLAIN, 26));
            tagline.setForeground(Color.WHITE);
            tagline.setBounds(100, 200, 1000, 40);
            bg.add(tagline);
    
            JButton enterButton = new JButton("ENTER ARENA");
            enterButton.setFont(new Font("Arial", Font.BOLD, 14));
            enterButton.setBounds(100, 280, 150, 30);
            enterButton.setForeground(Color.WHITE);
            enterButton.setBackground(new Color(255, 255, 255, 50));
            enterButton.setOpaque(true);
            enterButton.setBorderPainted(false);
            enterButton.setFocusPainted(false);
            bg.add(enterButton);
    
            enterButton.addActionListener(e -> {
                welcomeFrame.dispose(); // Close welcome screen
                new siyax_final();      // Start your login screen app
            });
    
            welcomeFrame.add(bg);
            welcomeFrame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Image error: " + e.getMessage());
        }
    }
    
    public siyax_final() {
        setTitle("Gaming Login UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel(null);
        loginPanel.setPreferredSize(new Dimension(600, getHeight()));
        loginPanel.setBackground(new Color(13, 13, 13));

        JLabel authTitle = new JLabel("AUTHORIZATION");
        authTitle.setFont(new Font("Arial", Font.BOLD, 28));
        authTitle.setForeground(Color.WHITE);
        authTitle.setBounds(150, 100, 400, 30);
        loginPanel.add(authTitle);

        JLabel subTitle = new JLabel("OF YOUR ACCOUNT");
        subTitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subTitle.setForeground(Color.LIGHT_GRAY);
        subTitle.setBounds(150, 130, 300, 30);
        loginPanel.add(subTitle);

        usernameField = new JTextField();
        usernameField.setBounds(150, 200, 300, 40);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBackground(new Color(30, 30, 30));
        usernameField.setForeground(Color.WHITE);
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        loginPanel.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 270, 300, 40);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBackground(new Color(30, 30, 30));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(150, 340, 300, 40);
        loginButton.setBackground(new Color(245, 66, 93));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginPanel.add(loginButton);

        JCheckBox rememberMe = new JCheckBox("Remember Me");
        rememberMe.setBounds(150, 390, 150, 20);
        rememberMe.setForeground(Color.LIGHT_GRAY);
        rememberMe.setBackground(new Color(13, 13, 13));
        loginPanel.add(rememberMe);

        JLabel signUpPrompt = new JLabel("Haven't Account?");
        signUpPrompt.setBounds(150, 430, 150, 20);
        signUpPrompt.setForeground(Color.GRAY);
        loginPanel.add(signUpPrompt);

        JButton signUpButton = new JButton("Create New Account!");
        signUpButton.setBounds(280, 425, 170, 30);
        signUpButton.setContentAreaFilled(false);
        signUpButton.setForeground(new Color(245, 66, 93));
        signUpButton.setBorderPainted(false);
        signUpButton.setFocusPainted(false);
        loginPanel.add(signUpButton);

        JLabel tagline = new JLabel("Gear Up, Brainiac! Enter the Arena. Outscore, Outwit, Outslay!");
        tagline.setFont(new Font("Arial", Font.PLAIN, 14));
        tagline.setForeground(Color.LIGHT_GRAY);
        tagline.setBounds(125, 600, 500, 30);
        loginPanel.add(tagline);

        add(loginPanel, BorderLayout.WEST);

        JPanel imagePanel = new JPanel(null);
        imagePanel.setBackground(Color.BLACK);
        try {
            ImageIcon icon = new ImageIcon("log_pic.png");
            Image img = icon.getImage();
            int w = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.6);
            int h = Toolkit.getDefaultToolkit().getScreenSize().height;
            JLabel imgLabel = new JLabel(new ImageIcon(img.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
            imgLabel.setBounds(0, 0, w, h);
            imagePanel.add(imgLabel);
        } catch (Exception e) {
            System.out.println("Image not found.");
        }
        add(imagePanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            if (authenticate(user, pass)) {
                dispose();
                SwingUtilities.invokeLater(() -> showGameHomeUI(user));
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password.");
            }
        });

        signUpButton.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill both fields.");
            } else if (userExists(user)) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
            } else {
                saveUser(user, pass);
                JOptionPane.showMessageDialog(this, "Signup Successful!");
            }
        });

        setVisible(true);
    }

    private boolean authenticate(String user, String pass) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 2 && p[0].equals(user) && p[1].equals(pass)) return true;
            }
        } catch (IOException e) {}
        return false;
    }

    private boolean userExists(String user) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) if (line.startsWith(user + ",")) return true;
        } catch (IOException e) {}
        return false;
    }

    private void saveUser(String user, String pass) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv", true))) {
            bw.write(user + "," + pass); bw.newLine();
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new siyax_final().showWelcomeScreen();  // Show welcome first
        });
    }
    
    public void showGameHomeUI(String user) {
        this.currentUsername = user;

        JFrame frame = new JFrame("Game Home");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        ImageIcon rawIcon = new ImageIcon("land_pic.png");
        JLabel topBanner = new JLabel(new ImageIcon(rawIcon.getImage().getScaledInstance(1920, 500, Image.SCALE_SMOOTH)));
        topBanner.setPreferredSize(new Dimension(1920, 500));
        topBanner.setLayout(null);

        JLabel titleText = new JLabel("GET STARTED");
        titleText.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleText.setForeground(Color.WHITE);
        titleText.setBounds(40, 30, 300, 50);
        topBanner.add(titleText);

        frame.add(topBanner, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        optionsPanel.setBackground(Color.BLACK);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        optionsPanel.add(createCard("quiz.jpg", "Quiz", () -> new QuizGame(user)));
        //optionsPanel.add(createCard("guess_word.jpg", "Guess Word", () -> new GuessWordGame()));
        optionsPanel.add(createCard("guess_word.jpg", "Guess Word", () -> {
            GuessWordGame game = new GuessWordGame();
            game.setVisible(true);
        }));
        
        optionsPanel.add(createCard("Match.png", "Match Game", () -> new StateCapitalMatchGame()));

        optionsPanel.add(createCard("lead.jpg", "Leaderboard", this::showLeaderboard));

        frame.add(optionsPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createCard(String path, String text, Runnable onClick) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.BLACK);
        ImageIcon icon = new ImageIcon(path);
        JLabel img = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(240, 140, Image.SCALE_SMOOTH)));
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(img, BorderLayout.CENTER);
        card.add(label, BorderLayout.SOUTH);
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                onClick.run();
            }
        });
        return card;
    }

    class QuizGame extends JFrame {
        List<Question> questions = new ArrayList<>();
        JLabel qLabel, timerLabel;
        JButton[] opts = new JButton[4];
        JButton nextBtn;
        int score = 0, qIndex = 0, timeLeft = 15;
        Timer timer;
        String username;

        QuizGame(String user) {
            username = user;
            setTitle("Quiz");
            setSize(600, 400);
            setLayout(null);
            setLocationRelativeTo(null);
            getContentPane().setBackground(Color.BLACK);

            qLabel = new JLabel("", SwingConstants.CENTER);
            qLabel.setBounds(50, 20, 500, 60);
            qLabel.setFont(new Font("Arial", Font.BOLD, 18));
            qLabel.setForeground(Color.CYAN);
            add(qLabel);

            timerLabel = new JLabel("Time: 15", SwingConstants.RIGHT);
            timerLabel.setBounds(460, 10, 100, 20);
            timerLabel.setForeground(Color.WHITE);
            add(timerLabel);

            int y = 100;
            for (int i = 0; i < 4; i++) {
                opts[i] = new JButton("Option " + (i + 1));
                opts[i].setBounds(150, y, 300, 40);
                opts[i].setBackground(Color.DARK_GRAY);
                opts[i].setForeground(Color.WHITE);
                int finalI = i;
                opts[i].addActionListener(e -> checkAns(finalI));
                add(opts[i]);
                y += 50;
            }

            nextBtn = new JButton("NEXT");
            nextBtn.setBounds(250, 310, 100, 30);
            nextBtn.setEnabled(false);
            nextBtn.addActionListener(e -> loadNext());
            add(nextBtn);

            loadQuestions();
            loadQ();
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        void loadQuestions() {
            try (BufferedReader br = new BufferedReader(new FileReader("space_geo_real_quiz.csv"))) {
                br.readLine();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] p = line.split(",");
                    if (p.length >= 6)
                        questions.add(new Question(p[0], p[1], p[2], p[3], p[4], p[5]));
                }
                Collections.shuffle(questions);
                questions = questions.subList(0, 10);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Quiz file error");
            }
        }

        void loadQ() {
            if (qIndex >= questions.size()) {
                showResult();
                return;
            }
            Question q = questions.get(qIndex);
            qLabel.setText("Q" + (qIndex + 1) + ": " + q.q);
            String[] o = q.getShuffled();
            for (int i = 0; i < 4; i++) {
                opts[i].setText(o[i]);
                opts[i].setEnabled(true);
                opts[i].setBackground(Color.DARK_GRAY);
            }
            nextBtn.setEnabled(false);
            timeLeft = 15;
            timerLabel.setText("Time: 15");
            timer = new Timer(1000, e -> {
                timeLeft--;
                timerLabel.setText("Time: " + timeLeft);
                if (timeLeft == 0) {
                    timer.stop();
                    checkAns(-1);
                    nextBtn.setEnabled(true);
                }
            });
            timer.start();
        }

        void checkAns(int idx) {
            timer.stop();
            for (JButton b : opts)
                b.setEnabled(false);
            String correct = questions.get(qIndex).ans;
            if (idx >= 0 && opts[idx].getText().equals(correct)) {
                opts[idx].setBackground(Color.GREEN);
                score++;
            } else {
                if (idx >= 0)
                    opts[idx].setBackground(Color.RED);
                for (JButton b : opts)
                    if (b.getText().equals(correct))
                        b.setBackground(Color.GREEN);
            }
            nextBtn.setEnabled(true);
        }

        void loadNext() {
            qIndex++;
            loadQ();
        }

        void showResult() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("quiz_leaderboard.csv", true))) {
                bw.write(username + "," + score + ",," + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                bw.newLine();
            } catch (IOException e) {}
            JOptionPane.showMessageDialog(this, "Your Score: " + score + "/10");
            dispose();
        }

        class Question {
            String q, o1, o2, o3, o4, ans;
            Question(String q, String a, String b, String c, String d, String ans) {
                this.q = q;
                o1 = a; o2 = b; o3 = c; o4 = d;
                this.ans = ans;
            }

            String[] getShuffled() {
                List<String> list = Arrays.asList(o1, o2, o3, o4);
                Collections.shuffle(list);
                return list.toArray(new String[0]);
            }
        }
    }
    
    void showLeaderboard() {
        JFrame frame = new JFrame("Leaderboard");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        String[] cols = {"Name", "Score", "Achieved", "Date/Time"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        try (BufferedReader br = new BufferedReader(new FileReader("quiz_leaderboard.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 4) {
                    int score = Integer.parseInt(p[1]);
                    String tag = score <= 4 ? "Try Again" : (score <= 7 ? "Nice Try" : (score <= 9 ? "Excellent" : "Master"));
                    model.addRow(new Object[]{p[0], score + "/10", tag, p[3]});
                }
            }
        } catch (IOException e) {}

        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(28);
        JScrollPane scroll = new JScrollPane(table);
        frame.add(scroll);
        frame.setVisible(true);
    }
        
    class GuessWordGame extends JFrame {
        private JLabel riddleLabel, wordHintLabel, timerLabel, feedbackLabel;
        private JTextField answerField;
        private JButton submitButton;
        private java.util.List<WordClue> wordList;
        private int currentIndex = 0;
        private int score = 0;
        private int triesLeft = 2;
        private Timer timer;
        private int timeLeft = 25; 

        private final Color PINK = Color.decode("#191970");
        private final Color BLUE = Color.decode("#121212");

        public GuessWordGame() {
            setTitle("Guess the Word");
            setSize(900, 600);
            setLayout(null);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(Color.decode("#B7D3DF"));

            riddleLabel = new JLabel("Riddle", SwingConstants.CENTER);
            riddleLabel.setBounds(50, 50, 800, 60);
            riddleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
            riddleLabel.setForeground(PINK);
            add(riddleLabel);

            wordHintLabel = new JLabel("Word Hint: _ _ _ _ _", SwingConstants.CENTER);
            wordHintLabel.setBounds(50, 130, 800, 50);
            wordHintLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
            wordHintLabel.setForeground(BLUE);
            add(wordHintLabel);

            timerLabel = new JLabel("Time Left: 25", SwingConstants.CENTER); 
            timerLabel.setBounds(350, 190, 200, 30);
            timerLabel.setForeground(Color.RED);
            timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
            add(timerLabel);

            answerField = new JTextField();
            answerField.setBounds(250, 250, 400, 40);
            answerField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            add(answerField);

            submitButton = new JButton("Submit");
            submitButton.setBounds(370, 310, 150, 35);
            submitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
            submitButton.setBackground(new Color(245, 66, 93));
            submitButton.setForeground(Color.WHITE);
            add(submitButton);

            feedbackLabel = new JLabel("", SwingConstants.CENTER);
            feedbackLabel.setBounds(50, 370, 800, 40);
            feedbackLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            feedbackLabel.setForeground(Color.WHITE);
            add(feedbackLabel);

            submitButton.addActionListener(e -> checkAnswer());

            loadWords();
            Collections.shuffle(wordList);
            wordList = wordList.subList(0, Math.min(5, wordList.size()));

            startGame();
        }

        private void loadWords() {
            wordList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("guess_word_easy_moderate.csv"))) {
                String line = reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        wordList.add(new WordClue(parts[0].trim(), parts[1].trim()));
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "CSV file not found or invalid format.");
            }
        }

        private void startGame() {
            if (currentIndex >= wordList.size()) {
                endGame();
                return;
            }

            WordClue current = wordList.get(currentIndex);
            riddleLabel.setText(" Riddle: " + current.riddle);
            wordHintLabel.setText(" Word Hint: " + generateBlanks(current.answer));
            answerField.setText("");
            feedbackLabel.setText("");
            triesLeft = 2;
            startTimer();
        }

        private void checkAnswer() {
            if (timer != null) timer.stop();

            String userAnswer = answerField.getText().trim().toLowerCase();
            String correctAnswer = wordList.get(currentIndex).answer.toLowerCase();

            if (userAnswer.equals(correctAnswer)) {
                feedbackLabel.setForeground(PINK);
                feedbackLabel.setText(" Correct!");
                score++;
                nextWord();
            } else {
                triesLeft--;
                feedbackLabel.setForeground(Color.RED);
                feedbackLabel.setText(" Wrong! Tries left: " + triesLeft);
                if (triesLeft <= 0) {
                    showCorrectAnswer();
                } else {
                    startTimer();
                }
            }
        }

        private void showCorrectAnswer() {
            feedbackLabel.setForeground(BLUE);
            feedbackLabel.setText(" Correct Answer: " + wordList.get(currentIndex).answer.toUpperCase());
            nextWord();
        }

        private void nextWord() {
            currentIndex++;
            Timer pause = new Timer(1500, e -> {
                ((Timer) e.getSource()).stop();
                startGame();
            });
            pause.setRepeats(false);
            pause.start();
        }

        private void endGame() {
            JOptionPane.showMessageDialog(this, " Game Over!\nYour Score: " + score + " / " + wordList.size());
            dispose();
        }

        private void startTimer() {
            timeLeft = 25; 
            timerLabel.setText("Time Left: " + timeLeft);
            timer = new Timer(1000, e -> {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft);
                if (timeLeft <= 0) {
                    ((Timer) e.getSource()).stop();
                    feedbackLabel.setForeground(Color.ORANGE);
                    feedbackLabel.setText(" Time's Up!");
                    showCorrectAnswer();
                }
            });
            timer.start();
        }

        private String generateBlanks(String word) {
            StringBuilder display = new StringBuilder();
            Random rand = new Random();
            Set<Integer> revealIndexes = new HashSet<>();
            int revealCount = Math.max(1, word.length() / 3);
            while (revealIndexes.size() < revealCount) {
                revealIndexes.add(rand.nextInt(word.length()));
            }

            for (int i = 0; i < word.length(); i++) {
                if (revealIndexes.contains(i)) {
                    display.append(Character.toUpperCase(word.charAt(i))).append(" ");
                } else {
                    display.append("_ ");
                }
            }
            return display.toString();
        }


        class WordClue {
            String riddle, answer;

            WordClue(String riddle, String answer) {
                this.riddle = riddle;
                this.answer = answer;
            }
        }
    }

}