package use_case.view_transactions;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class ViewTransactionOutputData {

    /// Convert list into string

    private final String month;
    private List<HashMap<String, Object>> total_transaction;

    public ViewTransactionOutputData(String month, List<HashMap<String, Object>> total_transaction) {
        this.month = month;
        this.total_transaction = total_transaction;
    }

    public List<HashMap<String, Object>> getAllTransactions() {
        return total_transaction;
    }

    public HashMap<String, Object> getTransactionByIndex(int tileNum) {
        if (!total_transaction.isEmpty() && tileNum < total_transaction.size()) {
            return total_transaction.get(tileNum);
        }
        return null;





}}
