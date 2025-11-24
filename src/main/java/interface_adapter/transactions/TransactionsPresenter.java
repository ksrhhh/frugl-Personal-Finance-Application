    package interface_adapter.transactions;

    import interface_adapter.ViewManagerModel;
    import use_case.view_transactions.ViewTransactionOutputBoundary;
    import use_case.view_transactions.ViewTransactionOutputData;

    public class TransactionsPresenter implements ViewTransactionOutputBoundary {
        private final TransactionsViewModel transactionsViewModel;
        private final ViewManagerModel viewManagerModel;

        public TransactionsPresenter(ViewManagerModel viewManagerModel,
                                      TransactionsViewModel transactionsViewModel) {
            this.viewManagerModel = viewManagerModel;
            this.transactionsViewModel = transactionsViewModel;
        }

        public void prepare(ViewTransactionOutputData response) {
        }

    }
