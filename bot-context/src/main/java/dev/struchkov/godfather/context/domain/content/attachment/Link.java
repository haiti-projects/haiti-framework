package dev.struchkov.godfather.context.domain.content.attachment;

public class Link extends Attachment {

    private String url;

    public Link() {
        this.type = AttachmentType.LINK;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
