package common.megamek;

public interface IValueMapper<TKey, TValue> {
    TValue MapValue(TKey input);
}
