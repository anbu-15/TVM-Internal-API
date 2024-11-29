package com.tvm.internal.edit.request;

import lombok.Data;

@Data
public class AnnouncementFilterRequest {
    private String category;
    private String location;
    private Boolean status;
    private Boolean pinAllAnnouncement;
}
