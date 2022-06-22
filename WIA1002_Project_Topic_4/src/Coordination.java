

/**
 *
 * @author User
 */
public class Coordination {
    public String name;
    public int coordination;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoordination() {
        return coordination;
    }

    public void setCoordination(int coordination) {
        this.coordination = coordination;
    }

    public Coordination(String name, int coordination) {
        this.name = name;
        this.coordination = coordination;
    }

    public Coordination() {
    }
}
