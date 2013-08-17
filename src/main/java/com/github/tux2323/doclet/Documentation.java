package com.github.tux2323.doclet;

import java.util.LinkedList;
import java.util.List;

public class Documentation {

    private final List<ClassDoc> classDocs = new LinkedList<ClassDoc>();

    public void addTestClassDoc(ClassDoc classDoc) {
        getClassDocs().add(classDoc);
    }

    public List<ClassDoc> getClassDocs() {
        return classDocs;
    }

    @Override
    public String toString() {
        return "Documentation [classDocs=" + classDocs + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((classDocs == null) ? 0 : classDocs.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Documentation other = (Documentation) obj;
        if (classDocs == null) {
            if (other.classDocs != null)
                return false;
        } else if (!classDocs.equals(other.classDocs))
            return false;
        return true;
    }


}
