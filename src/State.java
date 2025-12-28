public enum State {

    MAIN("Main"), SUMMARY("Summary"), MONTHLY("Monthly");

    private final String state;

    State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
