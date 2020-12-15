package sample.objjects;

public class Contact {
    private int id;
    private String nameText;
    private String numberText;
    private String companyText;
    private String descriptionText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public String getNumberText() {
        return numberText;
    }

    public void setNumberText(String numberText) {
        this.numberText = numberText;
    }

    public String getCompanyText() {
        return companyText;
    }

    public void setCompanyText(String companyText) {
        this.companyText = companyText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public Contact(String nameText, String numberText, String companyText, String descriptionText) {
        this.nameText = nameText;
        this.numberText = numberText;
        this.companyText = companyText;
        this.descriptionText = descriptionText;
    }

    public Contact(int id,String nameText, String numberText, String companyText, String descriptionText) {
        this.id = id;
        this.nameText = nameText;
        this.numberText = numberText;
        this.companyText = companyText;
        this.descriptionText = descriptionText;
    }
}
