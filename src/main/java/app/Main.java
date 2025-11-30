package app;

import javax.swing.JFrame;

import view.DashboardView;

/**
 * The Main class of the application.
 */
public class Main {

    /**
     * Builds and runs the CA architecture application.
     * @param args unused command line arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addAutosaveView()
                .addAutosaveUseCase()
                .addImportStatementView()
                .addImportStatementUseCase()
                .addSetGoalView()
                .addGoalUseCase()
                .addDashboardView()
                .addDashboardUseCase()
                .addTransactionsView()
                .transactionViewUseCase()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);

        final DashboardView dashboardView = appBuilder.getDashboardView();
        if (dashboardView != null) {
            dashboardView.loadInitialData();
        }
    }
}
