package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
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
        JFrame application = appBuilder.build();
        application.pack();
        application.setVisible(true);
    }
}