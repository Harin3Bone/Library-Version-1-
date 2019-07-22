package gl_Enum;

public enum BookCategory {
    Comic("A"),
    Novel("B"),
    Magazine("C"),
    Newspaper("D");

    private String Code;

    private BookCategory(String Code) {

        this.Code = Code;
    }

    public String getCode() {

        return Code;
    }

    public void setCode(String code) {

        Code = code;
    }
}
