package com.example.SDETExercise;

import com.example.SDETExercise.jsonModels.Root;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PetService {

    private static final String baseURI = Configuration.getInstance("application").getValue("baseURI");
    private static final String petPath = Configuration.getInstance("application").getValue("petPath");
    private static final String jsonCreatePetPath = Configuration.getInstance("application").getValue("jsonCreatePetPath");
    private static final String petFilePath = Configuration.getInstance("application").getValue("petFilePath");

    public static ValidatableResponse createPetRequest(String petName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Root root = objectMapper.readValue(new File(jsonCreatePetPath), Root.class);
        root.setName(petName);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonBody = objectWriter.writeValueAsString(root);

        return given().header("Content-Type", "application/json").body(jsonBody).
                when().post(baseURI + petPath).then().log().all();
    }

    public static ValidatableResponse uploadAnImageRequest(Long petId) {
        File petFile = new File(petFilePath);
        return given().header("Content-Type", "multipart/form-data").pathParams("petId", petId).multiPart(petFile).
                when().post(baseURI + petPath + "/{petId}/uploadImage").then().log().all();
    }

    public static ValidatableResponse getSinglePetRequest(Long petId) {
        return given().pathParams("petId", petId).when().get(baseURI + petPath + "/{petId}").then().log().all();
    }

    public static ValidatableResponse findPetsByStatusRequest(String status) {
        return given().queryParam("status", status).when().get(baseURI + petPath + "/findByStatus").then().log().all();
    }
}
