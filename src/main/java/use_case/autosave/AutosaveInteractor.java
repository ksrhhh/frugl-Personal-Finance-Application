package use_case.autosave;

public class AutosaveInteractor implements AutosaveInputBoundary {
    private final AutosaveDataAccessInterface autosaveDataAccessObject;
    
    public AutosaveInteractor(AutosaveDataAccessInterface autosaveDataAccessObject) {
        this.autosaveDataAccessObject = autosaveDataAccessObject;
    }
    
    @Override
    public void execute(AutosaveInputData inputData) {
        try {
            autosaveDataAccessObject.save();
        } catch (RuntimeException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }
}
