public class DangerPair {
    public int index;
    public int risk;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public DangerPair() {
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public DangerPair(int index, int risk) {
        this.index = index;
        this.risk = risk;
    }
}
