package rssPackage;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimpleRRSReader extends JFrame {

    private Parser parser = new Parser();
    private List<String> urls = new ArrayList<>();
    
    private ExecutorService Executor = Executors.newCachedThreadPool();
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> scheduledTask;

    private JTextArea textArea;
    private JButton btnScheduler;

    public SimpleRRSReader() {
        setTitle("Simple RRSReader");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        urls.add("https://www.spiegel.de/schlagzeilen/index.rss");

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel Panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAdd = new JButton("Add URL...");
        btnScheduler = new JButton("Disactivate Scheduler");
        JButton btnUpdate = new JButton("Update");

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUrlAction();
            }
        });

        btnScheduler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disactivateSchedulerAction();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Update();
            }
        });

        Panel.add(btnAdd);
        Panel.add(btnScheduler);
        Panel.add(btnUpdate);

        add(Panel, BorderLayout.SOUTH);

        startScheduler();
    }

    /**
     * Startet den Timer der alle 10 Sekunden den Feed mit Update() updated.
     */
    private void startScheduler() {
        Runnable task = new Runnable() {
            public void run() {
                Update();
            }
        };
        scheduledTask = scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
        textArea.append("Message: Scheduler started automatically.\n");
    }

    /**
     * Öffnet ein kleines Fenster damit der User eine eigene RSS url eingeben kann
     */
    private void addUrlAction() {
        String input = JOptionPane.showInputDialog(this, "Enter RSS URL:");
        if (input != null && !input.trim().isEmpty()) {
            urls.add(input.trim());
            textArea.append("Message: URL added: " + input + "\n");
        }
    }

    /**
     * Schaltet den timer aus falls er eingeschalten ist aus und falls er ausgeschalten ist wieder ein
     */
    private void disactivateSchedulerAction() {
        if (!scheduledTask.isCancelled()) {
            scheduledTask.cancel(false);
            btnScheduler.setText("Activate Scheduler");
            textArea.append("Message: Scheduler deactivated \n");
        } else {
            startScheduler();
            btnScheduler.setText("Disactivate Scheduler");
        }
    }

    /**
     * Geht durch alle Urls die gespeichert worden sind durch und laded die neuesten Nachrichten herunter
     */
    private void Update() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea.append("Message: Updating channels...\n");
            }
        });

        for (final String url : urls) {
            Executor.submit(new Runnable() {
                public void run() {
                    Sender s = parser.parse(url);
                    if (s != null && !s.getNachrichten().isEmpty()) {
                        Nachricht newest = s.getNachrichten().get(0);
                        final String ausgabe = String.format("Channel: %s Newest item: %s Date: %s",
                                s.getTitel(),
                                newest.getTitel(),
                                newest.getDatum()
                        );
                        
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                textArea.append(ausgabe + "\n");
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * Setzt das Fenster des RRS Feeds auf visible
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleRRSReader().setVisible(true);
            }
        });
    }
}