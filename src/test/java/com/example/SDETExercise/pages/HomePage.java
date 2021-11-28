package com.example.SDETExercise.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

    @FindBy(how = How.ID, using = "operations-tag-pet")
    public WebElement petSection;

    @FindBy(how = How.ID, using = "operations-tag-store")
    public WebElement storeSection;

    @FindBy(how = How.ID, using = "operations-tag-user")
    public WebElement userSection;

    public void clickOnPetSection() {
        petSection.click();
    }

    public void clickOnStoreSection() {
        storeSection.click();
    }

    public void clickOnUserSection() {
        userSection.click();
    }

    public boolean isPetSectionCollapsed() {
        return !Boolean.parseBoolean(petSection.getAttribute("data-is-open"));
    }

    public boolean isStoreSectionCollapsed() { return !Boolean.parseBoolean(storeSection.getAttribute("data-is-open")); }

    public boolean isUserSectionCollapsed() {
        return !Boolean.parseBoolean(userSection.getAttribute("data-is-open"));
    }

    public boolean isPetSectionExpanded() {
        return Boolean.parseBoolean(petSection.getAttribute("data-is-open"));
    }

    public boolean isStoreSectionExpanded() {
        return Boolean.parseBoolean(storeSection.getAttribute("data-is-open"));
    }

    public boolean isUserSectionExpanded() {
        return Boolean.parseBoolean(userSection.getAttribute("data-is-open"));
    }
}
