package dev.struchkov.godfather.context.domain.content.attachment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Link extends Attachment {
    private String url;

    public Link() {
        this.type = AttachmentType.LINK;
    }

}