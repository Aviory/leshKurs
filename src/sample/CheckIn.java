package sample;

public class CheckIn {

    public static boolean validationTxtLength(String name){
            if(name.length()<4)
                return false;
            return true;
    }


    public static boolean validationNumber(String numberText) {
        if(numberText.matches("[0-9]+"))
            return true;
        return false;
    }

    public static boolean validationTxtDescription(String text) {
        if(text.length()>=15)
            return true;
        return false;
    }


}
