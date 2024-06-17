package ast;

public interface Formula {
    public <R, A> R accept(FormulaVisitor<R, A> visitor, A a);
}
