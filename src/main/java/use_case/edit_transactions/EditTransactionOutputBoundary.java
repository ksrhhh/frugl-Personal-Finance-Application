package use_case.edit_transactions;


/**
 * This is the outboundary for Edit Transaction Use case
 */
public interface EditTransactionOutputBoundary {
    //prepare success view when the category is succesfully edited

    /**
     * Preps the success view when edit category is succesful
     * @param outputData
     */
    void prepareSuccessView(EditTransactionOutputData outputData);

    /**
     * Preapres the failure view for Edit Transaction Use Case
     * @param errorMsg describes the type of the error
     */

    void prepareFailView(String errorMsg);
}
