package ast;

public class PrintVisitor implements FormulaVisitor<Void, Integer> {
    private StringBuilder result;

    public PrintVisitor() {
        result = new StringBuilder();
    }

    public String getResult() {
        return result.toString();
    }

    @Override
    public Void visit(BinaryOperator binForm, Integer indentation) {
        boolean needParentheses = false;

        // Check if parentheses are needed based on operator precedence
        if (indentation >= binForm.getOp().predecessor && indentation > 0) {
            needParentheses = true;
        }

        if (needParentheses) {
            result.append("(");
        }

        binForm.getLeft().accept(this, binForm.getOp().predecessor);
        result.append(binForm.getOp().toString());
        binForm.getRight().accept(this, binForm.getOp().predecessor);

        if (needParentheses) {
            result.append(")");
        }

        return null;
    }
    
    @Override
    public Void visit(Not notForm, Integer indentation) {
        boolean needParentheses = false;
        if (notForm.getOperand() instanceof BinaryOperator) {
            needParentheses = true;
        }

        result.append("!");
        if (needParentheses){
            result.append("(");
        }
        notForm.getOperand().accept(this, indentation);
        if (needParentheses){
            result.append(")");
        }
        return null;
    }

    @Override
    public Void visit(Atom atomForm, Integer indentation) {
        result.append(atomForm.getId());
        return null;
    }

    @Override
    public Void visit(Constant constantForm, Integer indentation) {
        if (constantForm.getValue()){
            result.append("True");
        } else {
            result.append("False");
        }
        return null;
    }
}
