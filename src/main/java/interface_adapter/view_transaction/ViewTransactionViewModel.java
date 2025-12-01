package interface_adapter.view_transaction;

import interface_adapter.ViewModel;

/**
 * View Model for View Transaction Use Case.
 */
public class ViewTransactionViewModel extends ViewModel<ViewTransactionState> {

    public ViewTransactionViewModel() {
        super("view transaction");
        setState(new ViewTransactionState());

    }
}
