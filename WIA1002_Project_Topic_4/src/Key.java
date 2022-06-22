import java.util.Objects;


public class Key<T> {
    private final T key;

    public Key(T k) {
        this.key = k;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.key);
        return hash;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Key other = (Key) obj;
        return this.key == other.key;
    }
}
