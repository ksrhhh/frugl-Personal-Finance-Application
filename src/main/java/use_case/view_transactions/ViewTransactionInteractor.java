package use_case.view_transactions;

import entity.Transaction;
import entity.User;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class ViewTransactionInteractor {
    private final ViewTransactionDataAccessInterface userDataAccessObject;
    private final ViewTransactionOutputBoundary viewTransactionOutputBoundary;

    public ViewTransactionInteractor(ViewTransactionDataAccessInterface userDataAccessObject,ViewTransactionOutputBoundary viewTransactionOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.viewTransactionOutputBoundary = viewTransactionOutputBoundary;
    }


    public void execute(ViewTransactionInputData transactionInputData) {
        final String month = ViewTransactionInputData.getMonth();
        final List<Transaction> trans = ViewTransactionInputData.getTransactionList();
        List<HashMap<String, Object>> proccessed_transactions = convert_transaction_toString(trans);


        //final ViewTransactionOutputputData = ()


        }

        private List<HashMap<String, Object>> convert_transaction_toString(List<Transaction> trans) {

            List<HashMap<String, Object>> proccessed_transactions = new ArrayList<>();

            for (int i = 0; i< trans.size() ; i++) {

                Transaction transac = trans.get(i);
                HashMap<String, Object> t1 = new HashMap<>();

                t1.put("date", transac.getDate());
                t1.put("source", transac.getSource().getName());
                t1.put("amount", String.valueOf(transac.getAmount()));
                t1.put("category", transac.getCategory().getName());

                proccessed_transactions.add(t1);

            }
            return proccessed_transactions;

    }

    }









    // convert everthing to string in outputData




