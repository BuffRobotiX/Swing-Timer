//
// David Buff
//
// Description:
//      A simple count down timer using a slider.
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class StopWatchRT {

    JLabel display;
    JButton start;
    JSlider slider;
    double time;
    Timer timer;
    int resolution = 500;

    StopWatchRT() {

        // Create a new JFrame container. 
        JFrame frame = new JFrame("D. Buff's Timer");

        // Specify FlowLayout for the layout manager. 
        frame.setLayout(new FlowLayout());

        // Give the frame an initial size. 
        frame.setSize(220, 120);

        // Terminate the program when the user closes the application. 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JLabel("David Buff");

        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (display.getText().equals("David Buff") || time == 0)
                {
                    time = slider.getValue();
                    display.setText(String.valueOf(time));
                }
                if (start.getText().equals("Start"))
                {
                    timer.start();
                    start.setText("Stop");
                }
                else
                {
                    timer.stop();
                    start.setText("Start");
                }
            }
        });

        timer = new Timer(resolution, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (time > 0)
                    time -= resolution/1000.0;
                else
                {
                    timer.stop();
                    start.setText("Start");
                }
                display.setText(String.valueOf(time));
            }
        });

        slider = new JSlider(5, 15, 10);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);

        slider.setLabelTable(slider.createStandardLabels(1));

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                if (slider.getValueIsAdjusting())
                    return;
                time = slider.getValue();
                display.setText(String.valueOf(time));
                timer.stop();
                start.setText("Start");
            }
        });

        frame.add(slider);
        frame.add(start);
        frame.add(display);

        frame.getRootPane().setDefaultButton(start);

        frame.setVisible(true);
    }

    public static void main(String args[]) {

        // Create the frame on the event dispatching thread. 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StopWatchRT();
            }
        });

    }
}