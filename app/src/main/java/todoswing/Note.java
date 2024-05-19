/*
*
* 
*
* Creada el 18 may 2024, 22:18:25
*
* Desarrollada por Bluesadsilk en l'empresa Abastos el dia 18 may 2024
*
* Email de contacto: bluesadsilk@proton.me
*
*
* @autor Bluesadsilk
* @date 18 may 2024
*/
package todoswing;

import java.io.Serializable;
import java.util.Objects;

public class Note implements Serializable, Comparable<Note> {
    private String date;
    private String noteTitle;
    private String noteContent;
    private static final long serialVersionUID = 1L;

    public Note() {
    }

    public Note(String date, String noteTitle, String noteContent) {
        this.date = date;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoteTitle() {
        return this.noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return this.noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Note date(String date) {
        setDate(date);
        return this;
    }

    public Note noteTitle(String noteTitle) {
        setNoteTitle(noteTitle);
        return this;
    }

    public Note noteContent(String noteContent) {
        setNoteContent(noteContent);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Note)) {
            return false;
        }
        Note nota = (Note) o;
        return Objects.equals(date, nota.date) && Objects.equals(noteTitle, nota.noteTitle)
                && Objects.equals(noteContent, nota.noteContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, noteTitle, noteContent);
    }

    @Override
    public String toString() {
        return "{" +
                " date='" + getDate() + "'" +
                ", noteTitle='" + getNoteTitle() + "'" +
                ", noteContent='" + getNoteContent() + "'" +
                "}";
    }

    @Override
    public int compareTo(Note other) {
        return this.noteTitle.compareTo(other.noteTitle);
    }

}
