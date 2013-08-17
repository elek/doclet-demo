package com.github.tux2323.doclet;

/**
 * Documentation for a java method.
 */
public class MemberDoc {

    private String name;

    private String title;

    private String description;

    public MemberDoc() {
    }

    public MemberDoc(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberDoc memberDoc = (MemberDoc) o;

        if (description != null ? !description.equals(memberDoc.description) : memberDoc.description != null)
            return false;
        if (name != null ? !name.equals(memberDoc.name) : memberDoc.name != null) return false;
        if (title != null ? !title.equals(memberDoc.title) : memberDoc.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MemberDoc{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
