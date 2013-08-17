package com.github.tux2323.doclet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;
import com.thoughtworks.xstream.XStream;

public class DocDoclet {

    private static final String DOCU_XML_FILE = "docu.xml";

    private static final String JAVADOC_DESCRIPTION_TAG = "description";
    private static final String JAVADOC_TITLE_TAG = "title";

    private File reportDirectory;

    private final XStream xStream = new XStream();

    public static boolean start(RootDoc root) throws Exception {
        DocDoclet doc = new DocDoclet();
        String outputDir = readOptionParameter("-d", root.options());
        if (outputDir != null) {
            doc.reportDirectory = new File(outputDir);
        }
        System.out.println(outputDir);
        doc.createTestDocumentation(root.classes());
        System.out.println();
        return true;
    }

    protected final void createTestDocumentation(com.sun.javadoc.ClassDoc[] classes) throws Exception {
        final Documentation documentation = new Documentation();
        for (com.sun.javadoc.ClassDoc classDoc : classes) {
            final ClassDoc testClassDoc = new ClassDoc(classDoc.name());
            documentation.addTestClassDoc(testClassDoc);
            for (com.sun.javadoc.MethodDoc methodDoc : classDoc.methods()) {
                MemberDoc testDoc = new MemberDoc(methodDoc.name());
                testClassDoc.addMethodDoc(testDoc);
                testDoc.setTitle(extractTitle(methodDoc));
                testDoc.setDescription(extractDescription(methodDoc));
            }

            for (com.sun.javadoc.FieldDoc fieldDoc : classDoc.fields(false)) {
                MemberDoc testDoc = new MemberDoc(fieldDoc.name());
                testClassDoc.addFieldDoc(testDoc);
                if (fieldDoc.getRawCommentText() != null) {
                    testDoc.setTitle(fieldDoc.getRawCommentText().trim());
                }
            }
        }
        writeXmlModel(documentation);
    }

    private String extractDescription(com.sun.javadoc.MemberDoc memberDoc) {
        return extractTag(memberDoc, JAVADOC_DESCRIPTION_TAG);
    }

    private String extractTitle(com.sun.javadoc.MemberDoc memberDoc) {
        return extractTag(memberDoc, JAVADOC_TITLE_TAG);
    }

    private String extractTag(com.sun.javadoc.MemberDoc memberDoc, String tagName) {
        String tagTextContent = null;
        final Tag[] tags = memberDoc.tags(tagName);
        if (tags.length > 0) {
            tagTextContent = tags[0].text();
        }
        return tagTextContent;
    }

    private void writeXmlModel(Documentation documentation) throws FileNotFoundException {
        getReportDirectory().mkdirs();
        xStream.toXML(documentation, new FileOutputStream(getDocumentationFile()));
    }

    public File getDocumentationFile() {
        return new File(getReportDirectory(), DOCU_XML_FILE);
    }

    public File getReportDirectory() {
        return reportDirectory;
    }


    public static int optionLength(String option) {
        if (option.equals("-d")) {
            return 2;
        }
        return 2;
    }


    private static String readOptionParameter(String name, String[][] options) {
        String param = null;
        for (int i = 0; i < options.length; i++) {
            String[] opt = options[i];
            if (opt[0].equals(name)) {
                param = opt[1];
            }
        }
        return param;
    }

}
