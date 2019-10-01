package javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.javadoc.description.JavadocInlineTag;
import com.github.javaparser.javadoc.description.JavadocSnippet;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.javaparser.ast.Modifier.Keyword.PRIVATE;

/**
 * Some code that uses JavaParser.
 */
public class LogicPositivizer {
    public List<Model> models = new ArrayList<>();
    public String moduleName = "notice";

    public LogicPositivizer() {

        models.add(new Model("id", "String"));
        models.add(new Model("name", "String"));
        models.add(new Model("age", "int"));
    }


    public static void main(String[] args) throws IOException {

        LogicPositivizer logicPositivizer = new LogicPositivizer();
        logicPositivizer.generate();
    }

    private void generate() {
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(LogicPositivizer.class).resolve("src/main/resources"));

        //CompilationUnit cu = sourceRoot.parse("", "AgentGroup.java");

        try {
            sourceRoot.tryToParse();

            List<CompilationUnit> compilationUnits = sourceRoot.getCompilationUnits();
            for (CompilationUnit compilationUnit : compilationUnits) {
                removeMemberVariable(compilationUnit);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }



        // This saves all the files we just read to an output directory.
        sourceRoot.saveAll(
                CodeGenerationUtils.mavenModuleRoot(LogicPositivizer.class)
                        .resolve(Paths.get("output")));
    }

    private void removeMemberVariable(CompilationUnit cu) {
        List<Node> childNodes = cu.getChildNodes();
        for (Node childNode : childNodes) {
            if (childNode instanceof ClassOrInterfaceDeclaration) {

                ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) childNode;
                NodeList nodeList = classOrInterfaceDeclaration.getMembers();

                for (int i=nodeList.size()-1; i>=0; i--) {
                    Node node = nodeList.get(i);
                    //System.err.println(node.getMetaModel() + " " + node.getTokenRange());

                    if (node instanceof FieldDeclaration) { // remove member variable
                        removeFieldDeclaration(node);
                        /*FieldDeclaration fieldDeclaration = (FieldDeclaration) node;
                        fieldDeclaration.remove();*/

                        //fieldDeclaration.setComment(new LineComment("__"));
                        /*FieldDeclaration newFieldDeclaration = new FieldDeclaration();
                        newFieldDeclaration.setPrivate(true);
                        newFieldDeclaration.set

                        fieldDeclaration.replace(fieldDeclaration, new FieldDeclaration())
*/
                        /*BlockStmt blockStmt = new BlockStmt();
                        blockStmt.addOrphanComment(new LineComment("__SET_VALUES__"));
                        node.setBody(blockStmt);*/
                    } else if (node instanceof ConstructorDeclaration) { // remove constructor

                        /*List<ConstructorDeclaration> list = new ArrayList<>();

                        node.accept(new VoidVisitorAdapter<Void>() {
                            @Override public void visit(ConstructorDeclaration n, Void arg) {
                                list.add(n);
                            }
                        }, null);
                        list.forEach(n -> n.remove());*/

                        removeConstructorDeclaration(node);

                        changeConstructorParameterAndBody(classOrInterfaceDeclaration, "Object", "__CONSTRUCTOR_PARAMETERS__", "__CONSTRUCTOR_BODY__");

                        /*ConstructorDeclaration constructorDeclaration = classOrInterfaceDeclaration.addConstructor(Modifier.publicModifier().getKeyword());
                        constructorDeclaration.addParameter("Object", "__CONSTRUCTOR_PARAMETERS__");*/

                        /*BlockStmt blockStmt = new BlockStmt();
                        blockStmt.addOrphanComment(new LineComment("__CONSTRUCTOR_BODY__"));
                        constructorDeclaration.setBody(blockStmt);*/

                    } else if (node instanceof MethodDeclaration) {
                        MethodDeclaration methodDeclaration = (MethodDeclaration) node;
                        /*if ("setValues".equals(methodDeclaration.getName().getIdentifier())) {
                            BlockStmt blockStmt = new BlockStmt();
                            blockStmt.addOrphanComment(new LineComment("__SET_VALUES__"));
                            methodDeclaration.setBody(blockStmt);
                        }*/

                        if ("setValues".equals(methodDeclaration.getName().getIdentifier())) {
                            setMethodBodyAsLineComment(node, "__SET_VALUES__");
                        }
                    }
                }


                if (classOrInterfaceDeclaration.getNameAsString().endsWith("AgentGroup")) {
                    // add member variable
                    classOrInterfaceDeclaration.addField(Object.class, "__MEMBER_VARIABLES__", PRIVATE);
                }





                /*BlockStmt blockStmt = new BlockStmt();
                String statement = "super(agentGroupKey);\n";
                for (Model model: models) {
                    statement += "this." + model.getName() + " = " + model.getName() + ";\n";
                }
                System.err.println(statement);*/
                //blockStmt.addStatement(statement);
                //constructorDeclaration.setBody(blockStmt);


/*                for (Model model: models) {
                    classOrInterfaceDeclaration.addConstructor(Modifier.publicModifier().getKeyword())
                            .addParameter(model.getType(), model.getName())
                            .setBody(new BlockStmt().addStatement(new ExpressionStmt(new AssignExpr(new FieldAccessExpr(new ThisExpr(), model.getName()), new NameExpr(model.getName()), AssignExpr.Operator.ASSIGN))));
                }*/

            }
        }

    }

    private boolean isDomainJavaSource(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
        return classOrInterfaceDeclaration.getNameAsString().endsWith("AgentGroup");
    }

    private void removeFieldDeclaration(Node node) {
        FieldDeclaration fieldDeclaration = (FieldDeclaration) node;
        fieldDeclaration.remove();
    }

    private void removeConstructorDeclaration(Node node) {
        List<ConstructorDeclaration> list = new ArrayList<>();

        node.accept(new VoidVisitorAdapter<Void>() {
            @Override public void visit(ConstructorDeclaration n, Void arg) {
                list.add(n);
            }
        }, null);
        list.forEach(n -> n.remove());
    }

    private void setMethodBodyAsLineComment(Node node, String comment) {
        MethodDeclaration methodDeclaration = (MethodDeclaration) node;
        BlockStmt blockStmt = new BlockStmt();
        blockStmt.addOrphanComment(new LineComment(comment));
        methodDeclaration.setBody(blockStmt);
    }

    private void changeConstructorParameterAndBody(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, String type, String name, String bodyComment) {
        ConstructorDeclaration constructorDeclaration = classOrInterfaceDeclaration.addConstructor(Modifier.publicModifier().getKeyword());
        constructorDeclaration.addParameter(type, name);

        BlockStmt blockStmt = new BlockStmt();
        blockStmt.addOrphanComment(new LineComment(bodyComment));
        constructorDeclaration.setBody(blockStmt);
    }


    public void addMemberVariable(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
        // member variable
        VariableDeclarator variables = new VariableDeclarator();
        variables.setType("String");
        variables.setName("username");
        //variables.setInitializer(new NameExpr("xxxx"));
        FieldDeclaration fieldDeclaration = new FieldDeclaration().setModifier(Modifier.privateModifier().getKeyword(), true).addVariable(variables);
        classOrInterfaceDeclaration.getMembers().add(0, fieldDeclaration);

        classOrInterfaceDeclaration.addField(String.class, "addField", PRIVATE);


        // constructor

    }


    public void test() {
        //removeMemberVariable(cu);

        //removeConstructor(cu);
        // 멤버변수 추가
        /*model.add("username");
        model.add("address");*/



        /*cu.getClassByName("AgentGroup").ifPresent(classOrInterfaceDeclaration -> {

            // 멤버변수 추가
           // addMemberVariable(classOrInterfaceDeclaration);

            //addConstructor(classOrInterfaceDeclaration);

        });


        cu.findAll(ClassOrInterfaceDeclaration.class).stream()
                .forEach(c -> {
                    String oldName = c.getNameAsString();
                    String newName = "Abstract" + oldName;
                    System.out.println("Renaming class " + oldName + " into " + newName);
                    c.setName(newName);
                });*/
/*

        CompilationUnit compilationUnit = new CompilationUnit();
        ClassOrInterfaceDeclaration myClass = compilationUnit
                .addClass("MyClass")
                .setPublic(true);
        myClass.addField(int.class, "A_CONSTANT", PUBLIC, STATIC);
        myClass.addField(String.class, "name", PRIVATE);
        String code = myClass.toString();*/
/*

        cu.findAll(ClassOrInterfaceDeclaration.class).stream()
                .filter(c -> !c.isInterface()
                        && c.isAbstract()
                        && !c.getNameAsString().startsWith("Abstract"))
                .forEach(c -> {
                    String oldName = c.getNameAsString();
                    String newName = "Abstract" + oldName;
                    System.out.println("Renaming class " + oldName + " into " + newName);
                    c.setName(newName);
                });


*/
    }
}
