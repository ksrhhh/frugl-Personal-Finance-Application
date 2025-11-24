package use_case.edit_transactions;

import javax.xml.transform.Source;

public interface EditTransactionDataAccessInterface {


    /**
     * Updates the data system to give new Cateogry for trsansactio
     * @param source is the Source whose cateogry being updated
     */
    void changePassword(Source source);



}
