public class Language {
    private String languageID;
    private String name;

    public Language(String languageID, String name) {
        this.languageID = languageID;
        this.name = name;
    }

    public String getLanguageID() {
        return languageID;
    }

    public void setLanguageID(String languageID) {
        this.languageID = languageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
