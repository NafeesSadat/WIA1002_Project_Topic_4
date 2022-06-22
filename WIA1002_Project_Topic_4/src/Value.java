import java.util.Objects;


public class Value<T> {
    private T value;

    public Value(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
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
        final Value other = (Value) obj;
        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.value);
        return hash;
    }
    
}
