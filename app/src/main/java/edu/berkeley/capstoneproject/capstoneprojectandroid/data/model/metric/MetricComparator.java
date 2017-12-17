package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricComparator {

    public enum Type {
        EQ, NEQ, GT, GTE, LT, LTE
    }

    private final Type mType;

    public MetricComparator(Type type) {
        mType = type;
    }

    public Type getType() {
        return mType;
    }

    public boolean compare(float actual, float expected) {
        switch(mType) {
            case EQ:
                return actual == expected;
            case NEQ:
                return actual != expected;
            case GT:
                return actual > expected;
            case GTE:
                return actual >= expected;
            case LT:
                return actual < expected;
            case LTE:
                return actual <= expected;
        }

        return false;
    }
}
