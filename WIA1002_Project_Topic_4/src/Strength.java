
/**
 *
 * @author User
 */
public class Strength {
    public String name;
    public int strength;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Strength(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    public Strength() {
    }
}
