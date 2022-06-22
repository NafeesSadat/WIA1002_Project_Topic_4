
/**
 *
 * @author User
 */
public class Intelligence {
    public String name;
    public int intelligence;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public Intelligence(String name, int intelligence) {
        this.name = name;
        this.intelligence = intelligence;
    }

    public Intelligence() {
    }
}
