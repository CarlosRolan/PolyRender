package editor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import motorGraphic.MyBuffer;
import view.MyViewParams;

/**
 *
 * @author carlos
 */
public class EditorView extends JFrame {

    private static EditorView instance;

    public static void showEditor() {
        if (instance == null) {
            instance = new EditorView();
        }
        instance.setVisible(true);
    }

    private Scene scene;
    private MyBuffer buffer;

    /**
     * Creates new form DefaultView
     */
    public EditorView() {

        initWindow(MyViewParams.DEFAULT_WIDTH, MyViewParams.DEFAULT_HEIGHT);
        initComponents();
        initBuffer();

    }

    // INIT METHODS
    private void initBuffer() {
        buffer = new MyBuffer(new Runnable() {

            @Override
            public void run() {
                scene.init();
                long lastTime = System.nanoTime();
                long timer = System.currentTimeMillis();
                final double ns = 1000000000 / 60;
                // % of progress to get to the next update
                double delta = 0;

                while (buffer.isRunning()) {

                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;

                    lb_s_h.setText("Height:" + scene.getHeight());
                    lb_s_w.setText("Width:" + scene.getWidth());

                    while (delta >= 1) {
                        scene.update();
                        // Time between renders
                        delta--;

                        if (buffer.isRunning())
                            scene.render();

                        // Counts de nº of frames in a delta time
                        buffer.countFrame();

                        // 1 nanosec is 1000 milisec
                        if (System.currentTimeMillis() - timer > 1000) {
                            timer += 1000;
                            setTitle(String.valueOf(buffer.getFps()));
                            buffer.restartFrameCount();
                        }
                    }
                }
                buffer.stopBuffer();
            }
        });
        buffer.startBuffer();
    }

    private void initWindow(int width, int height) {
        Dimension size = new Dimension(width, height);
        this.setPreferredSize(size);
    }

    private void initComponents() {

        scene = new Scene();
        p_info = new javax.swing.JPanel();
        lb_x = new javax.swing.JLabel();
        lb_y = new javax.swing.JLabel();
        lb_z = new javax.swing.JLabel();
        lb_s_w = new javax.swing.JLabel();
        lb_s_h = new javax.swing.JLabel();
        p_control = new javax.swing.JPanel();
        p_south = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        p_info.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lb_x.setText("x-red");

        lb_y.setText("y-green");

        lb_z.setText("z-blue");

        javax.swing.GroupLayout p_infoLayout = new javax.swing.GroupLayout(p_info);
        p_info.setLayout(p_infoLayout);
        p_infoLayout.setHorizontalGroup(
                p_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(p_infoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lb_x)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_y)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_z)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_s_w)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_s_h)
                                .addContainerGap(255, Short.MAX_VALUE)));
        p_infoLayout.setVerticalGroup(
                p_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(p_infoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(p_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lb_x)
                                        .addComponent(lb_y)
                                        .addComponent(lb_z)
                                        .addComponent(lb_s_w)
                                        .addComponent(lb_s_h))
                                .addContainerGap(77, Short.MAX_VALUE)));

        getContentPane().add(p_info, java.awt.BorderLayout.PAGE_START);

        p_control.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout p_controlLayout = new javax.swing.GroupLayout(p_control);
        p_control.setLayout(p_controlLayout);
        p_controlLayout.setHorizontalGroup(
                p_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));
        p_controlLayout.setVerticalGroup(
                p_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 88, Short.MAX_VALUE));

        getContentPane().add(p_control, java.awt.BorderLayout.LINE_START);

        p_south.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout p_southLayout = new javax.swing.GroupLayout(p_south);
        p_south.setLayout(p_southLayout);
        p_southLayout.setHorizontalGroup(
                p_southLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 396, Short.MAX_VALUE));
        p_southLayout.setVerticalGroup(
                p_southLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));

        getContentPane().add(p_south, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(scene, BorderLayout.CENTER);

        pack();
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel lb_x;
    private javax.swing.JLabel lb_y;
    private javax.swing.JLabel lb_z;
    private javax.swing.JLabel lb_s_w;
    private javax.swing.JLabel lb_s_h;
    private javax.swing.JPanel p_control;
    private javax.swing.JPanel p_info;
    private javax.swing.JPanel p_south;
    // End of variables declaration

}
