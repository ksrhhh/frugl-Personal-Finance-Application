package use_case.import_statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Import Bank Statement Interactor.
 */
public class ImportStatementInteractor implements ImportStatementInputBoundary {

    private final ImportStatementDataAccessInterface userDataAccessObject;
    private final ImportStatementOutputBoundary presenter;

    public ImportStatementInteractor(ImportStatementDataAccessInterface userDataAccessObject, ImportStatementOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }


    @Override
    public void execute(ImportStatementInputData inputData) {
       File file = new File(inputData.getFilePath());
       if (!file.exists()){
           presenter.prepareFailView("Import unsuccessful: file does not exist");
       }

       JsonArray array;
        try {
            array = readArrayFromFile(inputData.getFilePath());
        } catch (Exception e) {
            presenter.prepareFailView("Import unsuccessful: unsupported file");
            return;
        }

        List<JsonObject> categorized = new ArrayList<>();
        List<JsonObject> uncategorized = new ArrayList<>();

        try {
            separateTransactions(transactions, categorized, uncategorized);
        } catch (Exception e) {
            presenter.prepareFailView("Import unsuccessful: unsupported file");
            return;
        }

        try {
            addTransactions(categorized);
        } catch (Exception e) {
            presenter.prepareFailView("failed to save categorized transactions");
            return;
        }



    }



    private JsonArray readArrayFromFile(String filePath) throws Exception {
        try (FileReader reader = new FileReader(filePath)) {

            JsonElement element = JsonParser.parseReader(reader);

            if (!element.isJsonArray()) {
                throw new Exception("File does not contain a JSON array");
            }

            return element.getAsJsonArray();
        }
    }


}
