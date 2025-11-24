package use_case.edit_transactions;

/*
* This is the input data for the Edit Transaction Use Case
 */

import jdk.jfr.Category;

import javax.xml.transform.Source;

public class EditTransactionInputData {
    private final Source source;
    private final Category newCategory;
    private final Category oldCategory;


    public EditTransactionInputData(Source source, Category newCategory, Category oldCategory) {
        this.source = source;
        this.newCategory = newCategory;
        this.oldCategory = oldCategory;
    }

    Category getNewCategory(){
        return newCategory;
    }
    Source getSource(){
        return source;
    }
    Category getOldCategory(){
        return oldCategory;
    }
}
