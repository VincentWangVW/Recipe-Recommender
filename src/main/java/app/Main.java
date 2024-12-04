package app;

import javax.swing.JFrame;

/**
 * Main class to run the application.
 */
public class Main {
    /**
     * Main method to run the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        appBuilder.addMainView()
                .addDateView()
                .addRecipeView()
                .addIngredientsView()
                .addGeneratedRecipesView()
                .addUserInfoView()
                .addMainUseCase()
                .addDateUseCase()
                .addRecipeUseCase()
                .addIngredientsUseCase()
                .addGeneratedRecipesUseCase()
                .addUserInfoUseCase();
        final JFrame application = appBuilder.build();
        application.pack();
        application.setVisible(true);
    }
}
