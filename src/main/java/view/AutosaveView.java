package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import interface_adapter.autosave.AutosaveController;
import interface_adapter.autosave.AutosaveState;
import interface_adapter.autosave.AutosaveViewModel;

public class AutosaveView extends JPanel implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private static final int STATUS_BUTTON_SPACING_PX = 8;
    private static final int AUTOSAVE_INTERVAL_MS = 5_000;

    private AutosaveController controller;
    private final AutosaveViewModel viewModel;
    private final JLabel statusLabel = new JLabel();
    private final JButton saveNowButton = new JButton("Save Now");
    private final Timer autosaveTimer;

    /**
     * Creates the swing view for the autosave use case.
     *
     * @param viewModel autosave view model
     */
    public AutosaveView(AutosaveViewModel viewModel) {
        this.viewModel = viewModel;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(statusLabel);
        add(Box.createHorizontalStrut(STATUS_BUTTON_SPACING_PX));
        add(saveNowButton);

        final AutosaveState state = viewModel.getState();
        statusLabel.setText(labelText(state.getStatusMessage(), state.getLastSavedAt(),
                state.getErrorMessage()));

        this.viewModel.addPropertyChangeListener(this);
        saveNowButton.addActionListener(actionEvent -> controller.autosaveNow());

        autosaveTimer = new Timer(AUTOSAVE_INTERVAL_MS, timerEvent -> this.controller.autosaveNow());
        autosaveTimer.setRepeats(true);
        autosaveTimer.start();
    }

    /** {@inheritDoc} */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AutosaveState state = (AutosaveState) evt.getNewValue();
        statusLabel.setText(labelText(state.getStatusMessage(), state.getLastSavedAt(), state.getErrorMessage()));
    }

    /**
     * Formats a user-facing label based on autosave state.
     *
     * @param statusMessage text describing the state
     * @param timestamp     latest save timestamp, if available
     * @param errorMessage  latest error, if any
     * @return formatted label text
     */
    private String labelText(String statusMessage, LocalDateTime timestamp, String errorMessage) {
        String message = "";
        if (errorMessage != null) {
            message += "Autosave failed: " + errorMessage;
        }
        if (timestamp != null) {
            message += statusMessage + " (" + timestamp + ")";
        }
        else {
            message += statusMessage;
        }
        return message;
    }

    /**
     * Registers the autosave controller used for manual and scheduled saves.
     *
     * @param autosaveController autosave controller
     */
    public void setupAutosaveController(AutosaveController autosaveController) {
        this.controller = autosaveController;
    }

    /**
     * Returns the identifier for this view.
     *
     * @return view name from the view model
     */
    public String getViewName() {
        return viewModel.getViewName();
    }

}
