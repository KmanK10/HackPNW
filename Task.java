import javax.swing.*;

class Task {
    private JPanel container;
    private JLabel task;
    private JLabel value;
    
    public Task(String task, int value) {
        container = new JPanel();
        this.task = new JLabel(task);
        this.value = new JLabel(Integer.toString(value));
        
        container.add(this.task);
        container.add(this.value);
        
        Taskboard.addTask(task, value);
    }
}