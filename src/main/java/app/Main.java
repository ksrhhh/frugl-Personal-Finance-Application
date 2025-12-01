package app;

import javax.swing.JFrame;

import interface_adapter.set_goal.SetGoalController;
import view.DashboardView;

import view.TransactionsView;

/**
 * The Main class of the application.
 */
public class Main {
    /**
     * Main entry point for the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) throws Exception {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addDashboardView()
                .addDashboardUseCase()
                .addAutosaveView()
                .addAutosaveUseCase()
                .addImportStatementView()
                .addImportStatementUseCase()
                .addSetGoalView()
                .addGoalUseCase()
                .addTransactionsView()  //added
                .addTransactionViewUseCase() //aded
                .build();

        final SetGoalController goalController = appBuilder.getSetGoalController();
        if (goalController != null) {
            goalController.loadForest();
        }

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);

        final DashboardView dashboardView = appBuilder.getDashboardView();
        if (dashboardView != null) {
            dashboardView.loadInitialData();
        }
    }
}
