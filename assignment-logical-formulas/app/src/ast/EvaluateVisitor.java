package ast;

import java.util.Map;

public class EvaluateVisitor implements FormulaVisitor<Boolean, Void> {
    private Map<String, Boolean> environment;

    public EvaluateVisitor(Map<String, Boolean> environment) {
        this.environment = environment;
    }

    @Override
    public Boolean visit(BinaryOperator binForm, Void a) {
        boolean lres = binForm.getLeft().accept(this,a);
        boolean rres = binForm.getRight().accept(this,a);
        switch(binForm.getOp()){
            case AndOp: return lres && rres;
            case OrOp: return lres || rres;
            case ImpliesOp: return !lres || rres;
            default:
                throw new UnsupportedOperationException("Unknown binary operator: " + binForm.getOp());
        }
    }

    @Override
    public Boolean visit(Not notForm, Void a) {
        boolean operandResult = notForm.getOperand().accept(this, a);
        return !operandResult;
    }

    @Override
    public Boolean visit(Atom atomForm, Void a) {
        String atomId = atomForm.getId();
        Boolean atomValue = environment.get(atomId);

        if (atomValue == null) {
            throw new IllegalArgumentException("Unknown atom ID: " + atomId);
        }

        return atomValue;
    }

    @Override
    public Boolean visit(Constant constantForm, Void a) {
        return constantForm.getValue();
    }
}
