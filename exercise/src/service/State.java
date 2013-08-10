package service;

public class State {
    private String fullName;
    private String twoDigitCode;

    public State() {
    }

    public State(String twoDigitCode, String fullName) {
        this.twoDigitCode = twoDigitCode;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTwoDigitCode() {
        return twoDigitCode;
    }

    public void setTwoDigitCode(String twoDigitCode) {
        this.twoDigitCode = twoDigitCode;
    }
}
