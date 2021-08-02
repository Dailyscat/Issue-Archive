public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
    for(Apple apple: inventory) {
        String output = formatter.accept(apple);
        System.out.println(output);
    }
}

public interface AppleFormatter (
    String accept(Apple a);
)

public class AppleFancyFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}

public class AppleSimpleFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        return "An apple of  " + apple.getWeight() + "g";
    }
}

public class Apple {
    public Integer weight = 0;
    public String color = "black";

    public Integer getWeight () {
        return this.weight;
    }

    public String getColor () {
        return this.color;
    }
}

prettyPrintApple(inventory, new AppleFancyFormatter);