import javax.swing.*;

class Winner {
    private JPanel container;
    private JLabel rank;
    private JLabel name;
    private JLabel score;
    
    public Winner(String rank, String name, int score) {
        container = new JPanel();
        this.rank = new JLabel(rank);
        this.name = new JLabel(name);
        this.score = new JLabel(Integer.toString(score));
        
        container.add(this.rank);
        container.add(this.name);
        container.add(this.score);
    }

    public JPanel getContainer() {
        return container;
    }
}