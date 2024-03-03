import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

class UI {
    // Frame
    private static JFrame frame = new JFrame();
    // Swap tab
    private static JTabbedPane tabbedPane = new JTabbedPane();
    // Taskboard
    private static JPanel taskboardPanel = new JPanel();
    private static JTable taskboard = new JTable(new DefaultTableModel(new String[] { "Task", "Value" }, 0));
    private static DefaultTableModel model = (DefaultTableModel) taskboard.getModel();
    // Menu
    private static JPanel menu = new JPanel();
    private static JButton newTaskBtn = new JButton("New Task");
    private static JButton removeTaskBtn = new JButton("Remove Task");
    private static JButton completeTaskBtn = new JButton("Complete Task");
    // Leaderboard
    private static JPanel leaderboardPanel = new JPanel();
    private static Winner first;
    private static Winner second;
    private static Winner third;
    // private static JPanel loserPanel = new JPanel();
    private static Winner loser;

    public static void initialize() {
        // Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.CYAN.brighter());

        // Taskboard
        taskboardPanel.setLayout(new BorderLayout());

        taskboard.setFont(new Font("Arial", Font.PLAIN, 15));
        taskboardPanel.add(taskboard, BorderLayout.CENTER);

        menu.setLayout(new GridLayout(1, 0));

        newTaskBtn.setFont(new Font("Arial", Font.BOLD, 20));
        removeTaskBtn.setFont(new Font("Arial", Font.BOLD, 20));
        completeTaskBtn.setFont(new Font("Arial", Font.BOLD, 20));
        menu.add(newTaskBtn);
        menu.add(removeTaskBtn);
        menu.add(completeTaskBtn);
        taskboardPanel.add(menu, BorderLayout.SOUTH);

        tabbedPane.addTab("Taskboard", taskboardPanel);

        // Leaderboard
        leaderboardPanel.setLayout(new GridLayout(0, 1));

        tabbedPane.addTab("Leaderboard", leaderboardPanel);

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 1) {
                leaderboardPanel.removeAll();

                if (Scoreboard.getNames().size() == 0) {
                    leaderboardPanel.add(new JLabel("No winners yet."));
                } else if (Scoreboard.getNames().size() == 1) {
                    first = new Winner("1.", Scoreboard.getWinners(1).get(0),
                                Scoreboard.getTopScore(Scoreboard.getWinners(1).get(0)));
                    leaderboardPanel.add(first.getContainer());
                } else if (Scoreboard.getNames().size() == 2) {
                    first = new Winner("1.", Scoreboard.getWinners(1).get(0),
                                Scoreboard.getTopScore(Scoreboard.getWinners(1).get(0)));
                    leaderboardPanel.add(first.getContainer());
                    loser = new Winner("LOSER", Scoreboard.getLosers(1).get(0),
                        Scoreboard.getTopScore(Scoreboard.getLosers(1).get(0)));
                    leaderboardPanel.add(loser.getContainer());
                } else if (Scoreboard.getNames().size() == 3) {
                    first = new Winner("2.", Scoreboard.getWinners(1).get(0),
                                Scoreboard.getTopScore(Scoreboard.getWinners(1).get(0)));
                    leaderboardPanel.add(first.getContainer());
                    second = new Winner("3.", Scoreboard.getWinners(2).get(1),
                                Scoreboard.getTopScore(Scoreboard.getWinners(2).get(1)));
                    leaderboardPanel.add(second.getContainer());
                    loser = new Winner("LOSER", Scoreboard.getLosers(1).get(0),
                        Scoreboard.getTopScore(Scoreboard.getLosers(1).get(0)));
                    leaderboardPanel.add(loser.getContainer());
                } else {
                    first = new Winner("1.", Scoreboard.getWinners(1).get(0),
                                Scoreboard.getTopScore(Scoreboard.getWinners(1).get(0)));
                    leaderboardPanel.add(first.getContainer());
                    second = new Winner("2.", Scoreboard.getWinners(2).get(1),
                                Scoreboard.getTopScore(Scoreboard.getWinners(2).get(1)));
                    leaderboardPanel.add(second.getContainer());
                    third = new Winner("3.", Scoreboard.getWinners(3).get(2),
                                Scoreboard.getTopScore(Scoreboard.getWinners(3).get(2)));
                    leaderboardPanel.add(second.getContainer());
                    loser = new Winner("LOSER", Scoreboard.getLosers(1).get(0),
                        Scoreboard.getTopScore(Scoreboard.getLosers(1).get(0)));
                    leaderboardPanel.add(loser.getContainer());
                }

                leaderboardPanel.revalidate();
                leaderboardPanel.repaint();
            }
        });

        frame.add(tabbedPane, BorderLayout.NORTH);
        frame.setVisible(true);

        // Event listeners
        newTaskBtn.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setSize(300, 200);
            dialog.setLayout(new GridLayout(0, 1));

            JPanel taskPanel = new JPanel();
            JLabel taskLabel = new JLabel("Task:");
            JTextField taskField = new JTextField();
            taskField.setPreferredSize(new Dimension(200, 20));
            taskPanel.add(taskLabel);
            taskPanel.add(taskField);
            dialog.add(taskPanel);

            JPanel valuePanel = new JPanel();
            JLabel valueLabel = new JLabel("Value:");
            JTextField valueField = new JTextField();
            valueField.setPreferredSize(new Dimension(200, 20));
            valuePanel.add(valueLabel);
            valuePanel.add(valueField);
            dialog.add(valuePanel);

            JPanel buttonPanel = new JPanel();
            JButton cancelBtn = new JButton("Cancel");
            JButton addBtn = new JButton("Add");
            buttonPanel.add(cancelBtn);
            buttonPanel.add(addBtn);
            dialog.add(buttonPanel);

            dialog.setVisible(true);

            cancelBtn.addActionListener(e1 -> {
                dialog.dispose();
            });

            addBtn.addActionListener(e1 -> {
                int value;

                try {
                    value = Integer.parseInt(valueField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Value must be an integer.");
                    return;
                }

                model.addRow(new Object[] { taskField.getText(), valueField.getText() });
                Taskboard.addTask(taskField.getText(), value);

                dialog.dispose();
            });
        });

        removeTaskBtn.addActionListener(e -> {
            if (taskboard.getSelectedRow() == -1) {
                return;
            }

            int row = taskboard.getSelectedRow();

            Taskboard.removeTask(model.getValueAt(row, 0).toString());
            model.removeRow(row);
        });

        completeTaskBtn.addActionListener(e -> {
            if (taskboard.getSelectedRow() == -1) {
                return;
            }

            int row = taskboard.getSelectedRow();

            JDialog dialog = new JDialog();
            dialog.setSize(200, 150);
            dialog.setLayout(new GridLayout(0, 1));

            JPanel namePanel = new JPanel();
            JLabel nameLabel = new JLabel("Name:");
            JTextField name = new JTextField();
            name.setPreferredSize(new Dimension(150, 20));
            namePanel.add(nameLabel);
            namePanel.add(name);
            dialog.add(namePanel);

            JPanel buttonPanel = new JPanel();
            JButton cancelBtn = new JButton("Cancel");
            JButton completeBtn = new JButton("Complete");
            buttonPanel.add(cancelBtn);
            buttonPanel.add(completeBtn);
            dialog.add(buttonPanel);

            dialog.setVisible(true);

            cancelBtn.addActionListener(e1 -> {
                dialog.dispose();
            });

            completeBtn.addActionListener(e1 -> {
                dialog.dispose();

                Taskboard.completeTask(name.getText(), model.getValueAt(row, 0).toString());
            });
        });

        // END GAME NEFARIOUS SHENANIGANS
    }

    public String announcement;

    public String EndGame(String input) {

        if (input == "tame") {
            int rand = (int) (Math.random() * Punishments.tamePunishment.size()) - 1;
            announcement = (Scoreboard.getLosers(1) + "has to:" + Punishments.tamePunishment.get(rand));

        } else if (input == "medium") {
            int rand = (int) (Math.random() * Punishments.mediumPunishment.size()) - 1;
            announcement = (Scoreboard.getLosers(1) + "has to:" + Punishments.mediumPunishment.get(rand));

        } else if (input == "extreme") {
            int rand = (int) (Math.random() * Punishments.extremePunishment.size()) - 1;
            announcement = (Scoreboard.getLosers(1) + "has to:" + Punishments.extremePunishment.get(rand));

        } else {
            announcement = ("invalid input BRROSKI! XDDDDDDDDDDDD");
        }

        return announcement;
    }
}