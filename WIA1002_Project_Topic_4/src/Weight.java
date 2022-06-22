/**
 *
 * @author User
 */
public class Weight {
    public String name;
    public int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Weight(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Weight() {
    }
}
