package de.telran.bank.account;


public enum AccountTypes {

    CHECKING_ACCOUNT("checkingAccount"),
    SAVING_ACCOUNT("savingAccount"),
    RETIREMENT_ACCOUNT("retirementAccount");

    private String value;

    AccountTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

