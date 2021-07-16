
import java.awt.*;

// for dealing with the start stop button
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// for dealing with the delay slider
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

public class PhilAnimator extends java.applet.Applet
			  implements ActionListener, AdjustmentListener {

    private Button stopStartButton = new Button("start");

    // delays can go from 500 to 10,000
    // (they get multiplied by 100 in Philosopher
    Scrollbar grabDelaySlider = new Scrollbar(Scrollbar.HORIZONTAL,
						      5, 1, 0, 100);
    private Label label = new Label("  500 milliseconds");
    private FramedArea framedArea;

    public void init() {
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        setLayout(gridBag);

        framedArea = new FramedArea(this);
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        gridBag.setConstraints(framedArea, c);
        add(framedArea);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.weighty = 0.0;
        gridBag.setConstraints(stopStartButton, c);
        add(stopStartButton);


        c.gridwidth = GridBagConstraints.RELATIVE; //don't end row
        c.weightx = 1.0;
        c.weighty = 0.0;
        gridBag.setConstraints(grabDelaySlider, c);
        add(grabDelaySlider);

        c.weightx = 0.0;
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        gridBag.setConstraints(label, c);
        add(label);

        validate();
	stopStartButton.addActionListener(this);
	grabDelaySlider.addAdjustmentListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (stopStartButton.getLabel().equals("stop/reset")) {
            framedArea.stopButton();
            stopStartButton.setLabel("start");
        } else if (stopStartButton.getLabel().equals("start")) {
            framedArea.startButton();
            stopStartButton.setLabel("stop/reset");
        }
    }
    public void adjustmentValueChanged(AdjustmentEvent e) {
        label.setText(String.valueOf(100*grabDelaySlider.getValue()) +
		      " milliseconds");
    }
}
