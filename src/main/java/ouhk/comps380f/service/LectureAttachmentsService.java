package ouhk.comps380f.service;

import ouhk.comps380f.model.LectureAttachments;

public interface LectureAttachmentsService {

    public LectureAttachments getAttachment(long ticketId, String name);
}