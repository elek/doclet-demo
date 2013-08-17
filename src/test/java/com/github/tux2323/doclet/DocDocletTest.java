package com.github.tux2323.doclet;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import com.thoughtworks.xstream.XStream;

public class DocDocletTest {

    private DocDoclet docDoclet;

    @Before
    public void setup() {
        docDoclet = new DocDoclet();
    }

    @Test
    public void testDocDoclet() throws Exception {
        com.sun.tools.javadoc.Main.execute(new String[]{"-doclet",
                DocDoclet.class.getName(), "-sourcepath", "src/test/java",
                "com.github.tux2323.doclet.demo", "-d", "target"});

        XStream xStream = new XStream();
        Documentation actualDocumentation = (Documentation) xStream
                .fromXML(new FileInputStream("target/docu.xml"));

        Documentation expectedDocumentation = new Documentation();
        ClassDoc classDoc = new ClassDoc("DemoTest");

        MemberDoc memberDoc = new MemberDoc("testDemo");
        memberDoc.setTitle("Doclet Demo Test");
        memberDoc.setDescription("A demo test case for the doc doclet.");
        classDoc.addMethodDoc(memberDoc);

        MemberDoc fieldDoc = new MemberDoc("hoppa");
        fieldDoc.setTitle("hoppa.");
        classDoc.addFieldDoc(fieldDoc);


        expectedDocumentation.addTestClassDoc(classDoc);

        assertEquals(expectedDocumentation, actualDocumentation);
    }

}
