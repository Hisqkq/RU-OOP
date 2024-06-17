package ast;

public interface FormulaVisitor<Result, AdditionalArg>{
    Result visit(BinaryOperator binForm, AdditionalArg a);
    Result visit(Not notForm, AdditionalArg a);
    Result visit(Atom atomForm, AdditionalArg a);
    Result visit(Constant constantForm, AdditionalArg a);
}
