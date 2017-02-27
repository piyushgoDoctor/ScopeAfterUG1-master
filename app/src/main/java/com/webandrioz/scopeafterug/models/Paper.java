package com.webandrioz.scopeafterug.models;

/**
 * Created by godoctor on 27/2/17.
 */

public class Paper {
    String paperId,paperName;

    public Paper(String paperId, String paperName) {
        this.paperId = paperId;
        this.paperName = paperName;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }
}
