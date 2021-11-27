package com.example.SDETExercise;

import com.example.SDETExercise.pages.HomePage;
import io.github.artsok.RepeatedIfExceptionsTest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SdetExerciseApplicationTests {

    private static Long newPetId;
    private static String newPetStatus;

    private static final String baseURI = Configuration.getInstance("application").getValue("baseURI");
    private static final String newPetName = Configuration.getInstance("application").getValue("newPetName");

    @Autowired
    private HomePage homePage;

    @Test
    @Order(0)
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
    }

    @Test
    @Order(1)
    void addNewPetAndUploadImage() throws IOException, JSONException {
        String jsonResponse = PetService.createPetRequest(newPetName).statusCode(200).extract().asString();
        newPetId = (Long) new JSONObject(jsonResponse).get("id");
        newPetStatus = (String) new JSONObject(jsonResponse).get("status");
        Assertions.assertNotNull(PetService.uploadAnImageRequest(newPetId).statusCode(200).extract().asString());
    }

    @RepeatedIfExceptionsTest(repeats = 5)
    @Order(2)
    void searchForNewPetAndSimilarPets() throws JSONException {
        String jsonResponse = PetService.getSinglePetRequest(newPetId).statusCode(200).extract().asString();
        Assertions.assertEquals(new JSONObject(jsonResponse).get("name"), newPetName);
        Assertions.assertNotNull(PetService.findPetsByStatusRequest(newPetStatus).statusCode(200).extract().asString());
    }

}
