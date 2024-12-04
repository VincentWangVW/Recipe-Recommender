package app;

import javax.swing.JFrame;

/**
 * Main class to run the application.
 * <p>
 * # Recipe Recommender - CSC207 Group 166
 * <p>
 * This application helps users generate recipes based on the ingredients they have, dietary restrictions, and other preferences.
 * Users can also generate recipes based on holidays, seasons, or custom searches.
 * The app provides a user-friendly interface to manage ingredients, user information, and generate recipes.
 * <p>
 * The application includes several views:
 * - Main View: The main interface of the application.
 * - Date View: Displays the current date, holiday, and season information.
 * - Recipe View: Allows users to generate recipes based on various criteria.
 * - Ingredients View: Manages the ingredients available to the user.
 * - Generated Recipes View: Displays the generated recipes with details.
 * - User Info View: Manages user-specific information such as dietary restrictions and allergies.
 * <p>
 * The application was developed to help users make the most out of their pantry items and improve their cooking skills.
 * It aims to reduce food waste by suggesting recipes based on available ingredients and user preferences.
 * The app also supports custom searches, making it versatile for various cooking needs.
 * <p>
 * Authors:
 * - Vincent Wang
 * - Laith Al Khoury
 * - Xinni Liu
 * - Yuchen Wang
 * - Jiahe Xiang
 * - Zifei Luo
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
