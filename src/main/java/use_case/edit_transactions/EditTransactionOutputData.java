package use_case.edit_transactions;

import jdk.jfr.Category;

import javax.xml.transform.Source;

public class EditTransactionOutputData {

    private final Category newCategory;

    private final boolean useCaseSuccess;;
    private final Source source;

    public EditTransactionOutputData(Category newCat, boolean useCaseSuccess, Source source)
    {
        this.newCategory = newCat;
        this.useCaseSuccess = useCaseSuccess;
        this.source = source;
    }

    public Category getCategory(){
        return newCategory;
    }

    public boolean isUseCaseSucceed(){
        return useCaseSuccess;
    }

    public Source getSource(){
       return source;
    }
}
