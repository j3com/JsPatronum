package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.KeywordLiteral;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.PropertyGet;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.StringLiteral;
import org.mozilla.javascript.ast.VariableInitializer;

public class VisitorGlobalToLocal implements NodeVisitor {

    private List<Scope> scopes = new ArrayList<Scope>();
    private Map<String, String> paramMap;

    public VisitorGlobalToLocal(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }
    
    @Override
    public boolean visit(AstNode astNode) {
        
        if (astNode.getClass() == FunctionNode.class) {
            this.scopes.add((Scope) astNode);
        }
        if (astNode.getClass() == Name.class) {
            Name name = (Name)astNode;
            String nameStr = name.getIdentifier();
            if (!paramMap.containsKey(nameStr)) {
                return true;
            }
            name.setIdentifier(paramMap.get(name.getIdentifier()));
            AstNode parent = name.getParent();
            if (parent.getClass() == VariableInitializer.class) {
                VariableInitializer variableInitializer = (VariableInitializer)parent;
                AstNode variableDeclaration = variableInitializer.getParent();
                AstNode top = variableDeclaration.getParent();
                ExpressionStatement expressionStatement = new ExpressionStatement();
                Assignment assignment = new Assignment();
<<<<<<< HEAD
                PropertyGet propertyGet = new PropertyGet();
                KeywordLiteral keywordLiteral = new KeywordLiteral();
                propertyGet = ;
                // VariableInitializer variableInitializer = (VariableInitializer)parent;
                    // AstNode variableDeclaration = variableInitializer.getParent();
                // AstNode top = variableDeclaration.getParent();
                // ExpressionStatement expressionStatement = new ExpressionStatement();
                // Assignment assignment = new Assignment();
                // assignment.setLeft(name);
                // assignment.setRight(variableInitializer.getInitializer());
                // System.out.println(variableInitializer.getTarget().getClass());
                // assignment.setOperator(Token.ASSIGN);
                // expressionStatement.setExpression(assignment);
                // top.replaceChild(variableDeclaration, expressionStatement);
=======
                ElementGet elementGet = new ElementGet();
                KeywordLiteral keywordLiteral = new KeywordLiteral();
                keywordLiteral.setType(Token.THIS);
                elementGet.setTarget(keywordLiteral);
				elementGet.setElement(name);
                assignment.setLeft(elementGet);
                assignment.setRight(variableInitializer.getInitializer());
                assignment.setOperator(Token.ASSIGN);
                expressionStatement.setExpression(assignment);
                top.replaceChild(variableDeclaration, expressionStatement);
            } else if (parent.getClass() == FunctionCall.class ) {
                FunctionCall functionCall = (FunctionCall)parent;
                ElementGet elementGet = new ElementGet();
                KeywordLiteral keywordLiteral = new KeywordLiteral();
                List<AstNode> arguments = new ArrayList<AstNode>();
                keywordLiteral.setType(Token.THIS);
                elementGet.setTarget(keywordLiteral);
				elementGet.setElement(name);
                arguments.add(elementGet);
                functionCall.setArguments(arguments);
>>>>>>> 5a8386177e30ce7f78b1476732361dbc73d0a325
            }
        }
        return true;
    }

}
