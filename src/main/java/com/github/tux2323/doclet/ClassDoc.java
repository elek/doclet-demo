package com.github.tux2323.doclet;

import java.util.LinkedList;
import java.util.List;

public class ClassDoc {

    String className;

    final List<MemberDoc> methodDocs = new LinkedList<MemberDoc>();

    final List<MemberDoc> fieldDocs = new LinkedList<MemberDoc>();

    public ClassDoc() {
    }

    public ClassDoc(String name) {
        this.className = name;
    }

    public void addMethodDoc(MemberDoc memberDoc) {
        methodDocs.add(memberDoc);
    }

    public void addFieldDoc(MemberDoc testDoc) {
        fieldDocs.add(testDoc);
    }

    @Override
    public String toString() {
        return "ClassDoc{" +
                "className='" + className + '\'' +
                ", methodDocs=" + methodDocs +
                ", fieldDocs=" + fieldDocs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassDoc classDoc = (ClassDoc) o;

        if (fieldDocs != null ? !fieldDocs.equals(classDoc.fieldDocs) : classDoc.fieldDocs != null) return false;
        if (methodDocs != null ? !methodDocs.equals(classDoc.methodDocs) : classDoc.methodDocs != null) return false;
        if (className != null ? !className.equals(classDoc.className) : classDoc.className != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return className != null ? className.hashCode() : 0;
    }
}
