package com.example.bankapplication;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerPolicy {

  private CreditOffer economyCreditOffer;
  private CreditOffer businessCreditOffer;
  private Customer sonam;
  private Customer deepa;

  @Given("there is an economy credit offer")
  public void thereIsAnEconomyCreditOffer() {
    economyCreditOffer = new EconomyCreditOffer("1");
  }

  @When("we have a usual customer")
  public void weHaveAUsualCustomer() {
    sonam = new Customer("Sonam",false);
  }

  @Then("you can add and remove him from an economy credit offer")
  public void youCanAddAndRemoveHimFromAnEconomyCreditOffer() {
    assertAll(
        "Verify all conditions for a usual customer and an economy customer",
        () -> assertEquals("1", economyCreditOffer.getId()),
        () -> assertEquals(true, economyCreditOffer.addCustomer(sonam)),
        () -> assertEquals(1, economyCreditOffer.getCustomersList().size()),
        () -> assertEquals("Sonam", economyCreditOffer.getCustomersList().get(0).getName()),
        () -> assertEquals(true, economyCreditOffer.removeCustomer(sonam)),
        () -> assertEquals(0, economyCreditOffer.getCustomersList().size()));
  }

  @When("we have a VIP customer")
  public void weHaveAVIPCustomer() {
    deepa = new Customer("Deepa",true);
  }

  @Then("you can add him but cannot remove him from an economy credit offer")
  public void youCanAddHimButCannotRemoveHimFromAnEconomyCreditOffer() {
    Assertions.assertAll("Verify all conditions for a VIP customer and an economy credit offer",
        ()->assertEquals("1",economyCreditOffer.getId()),
        ()->assertEquals(true,economyCreditOffer.addCustomer(deepa)),
        ()->assertEquals(1,economyCreditOffer.getCustomersList().size()),
        ()->assertEquals("Deepa",economyCreditOffer.getCustomersList().get(0).getName()),

        ()->assertEquals(false,economyCreditOffer.removeCustomer(deepa)),
        ()->assertEquals(1,economyCreditOffer.getCustomersList().size()));
  }

  @Given("there is a business credit offer")
  public void thereIsABusinessCreditOffer() {
    businessCreditOffer = new BusinessCreditOffer("2");
  }

  @Then("you cannot add or remove him from a business credit offer")
  public void youCannotAddOrRemoveHimFromABusinessCreditOffer() {
    Assertions.assertAll("Verify all conditions for a usual customer and an business credit offer",
        ()-> assertEquals(false,businessCreditOffer.addCustomer(sonam)),
        ()->assertEquals(0,businessCreditOffer.getCustomersList().size()),
        ()->assertEquals(false,businessCreditOffer.removeCustomer(sonam)),
        ()->assertEquals(0,businessCreditOffer.getCustomersList().size()));
  }

  @Then("you can add him but cannot remove him from a business credit offer")
  public void youCanAddHimButCannotRemoveHimFromABusinessCreditOffer() {
    Assertions.assertAll("Verify all conditions for a VIP customer and an business credit offer",
        ()-> assertEquals(true,businessCreditOffer.addCustomer(deepa)),
        ()->assertEquals(1,businessCreditOffer.getCustomersList().size()),
        ()->assertEquals(false,businessCreditOffer.removeCustomer(deepa)),
        ()->assertEquals(1, businessCreditOffer.getCustomersList().size()));
  }
}
