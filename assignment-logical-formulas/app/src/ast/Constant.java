package ast;

public enum Constant implements Formula {
    TRUE(true), FALSE(false);

    private final boolean value;

    private Constant(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public <R, A> R accept(FormulaVisitor<R, A> visitor, A a) {
        return visitor.visit(this, a);
    }
}
