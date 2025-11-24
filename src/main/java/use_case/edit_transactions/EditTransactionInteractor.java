package use_case.edit_transactions;

import data_access.TransactionDataAccessObject;
import jdk.jfr.Category;

import javax.xml.transform.Source;

public class EditTransactionInteractor implements EditTransactionInputBoundary {

    private final EditTransactionDataAccessInterface editTransactionDataAccessObject;
    private final EditTransactionOutputBoundary editTransactionOutputBoundary;
    private final Source source;
    private final Category category;

    public EditTransactionInteractor(EditTransactionDataAccessInterface editTransactionDataAccessObject, EditTransactionOutputBoundary editTransactionOutputBoundary, Source source, Category category) {
        this.editTransactionDataAccessObject = editTransactionDataAccessObject;

        this.editTransactionOutputBoundary = editTransactionOutputBoundary;
        this.source = source;
        this.category = category;
    }

    @Override
    public void execute(EditTransactionInputData editTransactionInputData) {

        TransactionDataAccessObject.changeCategory(Source);
        final Source source = Source(EditTransactionInputData.getSource(), EditTransactionInputData.getCategory());

        editTransactionOutputBoundary.prepareSuccessView(editTransactionOutputData);

    }
}
