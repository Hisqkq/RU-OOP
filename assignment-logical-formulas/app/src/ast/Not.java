package ast;

public class Not implements Formula {
    private Formula operand;

    public Not(Formula oper) {
        this.operand = oper;
    }

    public Formula getOperand() {
        return this.operand;
    }

    @Override
    public <R, A> R accept(FormulaVisitor<R, A> visitor, A a) {
        return visitor.visit(this, a);
    }
}


