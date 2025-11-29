package use_case.autosave;

import java.time.LocalDateTime;

public class AutosaveInteractor implements AutosaveInputBoundary {

    private final AutosaveDataAccessInterface autosaveDataAccessObject;

    private final AutosaveOutputBoundary autosaveOutputBoundary;

    public AutosaveInteractor(AutosaveDataAccessInterface autosaveDataAccessObject,
                              AutosaveOutputBoundary autosaveOutputBoundary) {
        this.autosaveDataAccessObject = autosaveDataAccessObject;
        this.autosaveOutputBoundary = autosaveOutputBoundary;
    }

    /**
     * Executes an autosave request.
     *
     * @param inputData contextual input for the request
     */
    @Override
    public void execute(AutosaveInputData inputData) {
        try {
            autosaveDataAccessObject.save();
            final AutosaveOutputData outputData = new AutosaveOutputData(
                    "Autosave completed successfully",
                    LocalDateTime.now());
            autosaveOutputBoundary.presentSuccess(outputData);
        }
        catch (RuntimeException ex) {
            final String errorMessage = "Failed to save data: " + ex.getMessage();
            autosaveOutputBoundary.presentFailure(errorMessage);
        }
    }
}

