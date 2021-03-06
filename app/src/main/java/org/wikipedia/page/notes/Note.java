package org.wikipedia.page.notes;

import org.wikipedia.page.notes.database.ArticleNoteTable;

/**
 * Created by amawai on 27/03/18.
 */

public class Note {
    private long id;

    private long articleId;
    private String noteContent;

    public static final ArticleNoteTable DATABASE_TABLE = new ArticleNoteTable();

    public Note(long articleId) {
        this(articleId,"");
    }

    public Note(long articleId, String noteContent) {
        this.articleId = articleId;
        this.noteContent = noteContent;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setArticleId(long id) {
        this.articleId = id;
    }

    public void setNoteContent(String content) {
        this.noteContent = content;
    }

    public String getNoteContent() {
        return this.noteContent;
    }

    public long getArticleId() {
        return this.articleId;
    }

    public long getNoteId() {
        return this.id;
    }
}
