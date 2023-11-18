import javax.swing.*;
import java.util.ArrayList;

public class Main {
    JFrame frame = new JFrame("Determinarea radacina");

    Frame Graph=new Frame();

    public Main() {
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        // Create a menu item
        JMenuItem menuItem = new JMenuItem("Menu Item");
        menu.add(menuItem);

        // Create a toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // make it non-floatable

        //add button to frame. When click call TopologicSort
        JButton button = new JButton("Determinare radacina");
        button.addActionListener(e -> {
            GetRoot getRoot = new GetRoot();
            getRoot.createListaAdiacenta();
            if (getRoot.isCiclic()) {
                JOptionPane.showMessageDialog(null, "Graful este ciclic");
                JOptionPane.showMessageDialog(null, "Graful nu arborescență");
                Graph.setRadacina(-1);
                Graph.repaint();
                //topologicSort.printListaAdiacenta();
            } else {
                //topologicSort.printListaAdiacenta();

                if(getRoot.isQuasiStronglyConnected())
                {
                    JOptionPane.showMessageDialog(null, "Graful este arborescență");
                    getRoot.printListaAdiacenta();
                    int root=getRoot.findRoot();
                    if(root==-1)
                    {
                        JOptionPane.showMessageDialog(null, "Grafului nu i se poate determina rădăcina");
                        Graph.setRadacina(-1);
                        Graph.repaint();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Rădăcina este: "+(++root));
                        Graph.setRadacina(root);
                        Graph.repaint();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Graful nu este tare conex");
                    JOptionPane.showMessageDialog(null, "Graful nu este arborescență");
                    Graph.setRadacina(-1);
                    Graph.repaint();
                }



            }

        });

        //add undo with ctrl+z
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Z"), "undo");
        button.getActionMap().put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Graph.undo();
            }
        });


        // Add the button to the toolbar
        toolBar.add(button);

        // Add the toolbar to the menu bar
        menuBar.add(toolBar);

        // Add the menu bar to the frame
        frame.setJMenuBar(menuBar);

        frame.add(Graph);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
