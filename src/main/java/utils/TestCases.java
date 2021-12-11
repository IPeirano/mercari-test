package utils;

// For test reporting implementation
public enum TestCases {
    T1("Add a new address"),
    T2("Search for a product");

    private String testName;

    public String getTestName() {
        return testName;
    }

    TestCases(String value) {
        this.testName = value;
    }
}
