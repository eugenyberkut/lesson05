package logic;

import java.io.Serializable;

/**
 * Created by Eugeny on 07.06.2015.
 */
public class Toy implements Comparable<Toy>, Serializable {
    private String name;
    private String type;
    private int minAge;
    private int maxAge;
    private int price;
    private int parameter;
    private String units;

    public Toy(String name, String type, int minAge, int maxAge, int price, int parameter, String units) {
        this.name = name;
        this.type = type;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.price = price;
        this.parameter = parameter;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toy)) return false;

        Toy toy = (Toy) o;

        if (minAge != toy.minAge) return false;
        if (maxAge != toy.maxAge) return false;
        if (price != toy.price) return false;
        if (parameter != toy.parameter) return false;
        if (!name.equals(toy.name)) return false;
        if (!type.equals(toy.type)) return false;
        return units.equals(toy.units);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + minAge;
        result = 31 * result + maxAge;
        result = 31 * result + price;
        result = 31 * result + parameter;
        result = 31 * result + units.hashCode();
        return result;
    }

    @Override
    public int compareTo(Toy o) {
        int result = name.compareTo(o.name);
        if (result != 0) return result;
        result = type.compareTo(o.type);
        if (result != 0) return result;
        result = minAge - o.minAge;
        if (result != 0) return result;
        result = maxAge - o.maxAge;
        if (result != 0) return result;
        result = price - o.price;
        if (result != 0) return result;
        result = parameter - o.parameter;
        if (result != 0) return result;
        return units.compareTo(o.units);
    }

    @Override
    public String toString() {
        return "Toy{" +
                "name=\"" + name + '\"' +
                ", type=\"" + type + '\"' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", price=" + price +
                ", parameter=" + parameter +
                ", units=\"" + units + '\"' +
                '}';
    }

    public static Toy parseToy(String line) {
        // 0123456789     01234567     012345678  01234567  0123456    01234567890   01234567
        // Toy{name="Doll", type="doll", minAge=2, maxAge=5, price=100, parameter=30, units="cm"}

        if (!line.startsWith("Toy{name=\"")) {
            throw new IllegalArgumentException("Неправильный формат строки");
        }
        String t = line.substring(10);
        int p = t.indexOf('"');
        String name = t.substring(0,p);
        t = t.substring(p+1+8);
        p = t.indexOf('"');
        String type = t.substring(0,p);
        t = t.substring(p+1+9);
        p = t.indexOf(',');
        int minAge = Integer.parseInt(t.substring(0,p));
        t = t.substring(p+1+8);
        p = t.indexOf(',');
        int maxAge = Integer.parseInt(t.substring(0,p));
        t = t.substring(p+1+7);
        p = t.indexOf(',');
        int price = Integer.parseInt(t.substring(0,p));
        t = t.substring(p+1+11);
        p = t.indexOf(',');
        int parameter = Integer.parseInt(t.substring(0,p));
        p = t.indexOf('"')+1;
        int p2 = t.lastIndexOf('"');
        String units = t.substring(p,p2);
//        System.out.println(name);
//        System.out.println(type);
//        System.out.println(minAge);
//        System.out.println(maxAge);
//        System.out.println(price);
//        System.out.println(parameter);
//        System.out.println(units);

        return new Toy(name, type, minAge, maxAge, price, parameter, units);
    }
}
