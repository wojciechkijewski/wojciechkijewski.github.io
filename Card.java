public class Card {
    private String value;
    private String type;

    Card(String value, String type){
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        if ("AJQK".contains(value)) {
            if (value == "A") {
                return 11;
            }           
            return 10;      
        }
        return Integer.parseInt(value);
    }

    public boolean isAce() {
        return value == "A";
    }

    public String getImagePath() {
        return "./cards/" + toString() + ".png";
    }

    public String toString() {
        return value + "-" + type;
    }   

}
