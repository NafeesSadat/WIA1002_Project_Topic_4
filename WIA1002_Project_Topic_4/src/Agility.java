/**
 *
 * @author User
 */
public class Agility {
    public String name;
    public int agility;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public Agility(String name, int agility) {
        this.name = name;
        this.agility = agility;
    }

    public Agility() {
    }
}
