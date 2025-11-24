package interface_adapter.transactions;

import use_case.view_transactions.ViewTransactionInputBoundary;

public class ViewTransactionsController {

    private final ViewTransactionInputBoundary useCase;

    public ViewTransactionController(ViewTransactionInputBoundary useCase) {
        this.useCase = useCase;
    }


}
