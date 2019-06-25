package kata.ex02;

@FunctionalInterface
public interface Plugin<T> {
    void run(T arg);
}
