package com.example.SDETExercise;

import com.example.SDETExercise.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SDETExerciseWebUITest {

    private static final String baseURI = Configuration.getInstance("application").getValue("baseURI");

    private HomePage homePage = new HomePage();

    @Test
    void collapseExpandRestMethodSections() {
        homePage.navigate(baseURI);

        homePage.clickOnPetSection();
        Assertions.assertTrue(homePage.isPetSectionCollapsed());
        homePage.clickOnPetSection();
        Assertions.assertTrue(homePage.isPetSectionExpanded());

        homePage.clickOnStoreSection();
        Assertions.assertTrue(homePage.isStoreSectionCollapsed());
        homePage.clickOnStoreSection();
        Assertions.assertTrue(homePage.isStoreSectionExpanded());

        homePage.clickOnUserSection();
        Assertions.assertTrue(homePage.isUserSectionCollapsed());
        homePage.clickOnUserSection();
        Assertions.assertTrue(homePage.isUserSectionExpanded());

        homePage.closeDriver();
    }
}
