package com.example.SDETExercise;

import io.github.artsok.RepeatedIfExceptionsTest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SDETExerciseApiTest {

    private static final String newPetName = Configuration.getInstance("application").getValue("newPetName");

    private static Long newPetId;
    private static String newPetStatus;

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
