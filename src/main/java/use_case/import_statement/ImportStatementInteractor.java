package use_case.import_statement;

import java.time.YearMonth;

/**
 * The Import Bank Statement Interactor.
 */
public class ImportStatementInteractor implements ImportStatementInputBoundary {

    private final ImportStatementDataAccessInterface userDataAccessObject;
    private final ImportStatementOutputBoundary presenter;

    public ImportStatementInteractor(ImportStatementDataAccessInterface userDataAccessObject, ImportStatementOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }


    @Override
    public void execute(ImportStatementInputData inputData) {

    }
}
