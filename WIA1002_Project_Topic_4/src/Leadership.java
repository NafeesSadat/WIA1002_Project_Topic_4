
/**
 *
 * @author User
 */
public class Leadership {
    public String name;
    public int leadership;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int leadership) {
        this.leadership = leadership;
    }

    public Leadership(String name, int leadership) {
        this.name = name;
        this.leadership = leadership;
    }

    public Leadership() {
    }
}
