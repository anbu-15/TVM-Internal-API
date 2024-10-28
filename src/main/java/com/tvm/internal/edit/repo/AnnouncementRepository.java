package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcements,Long> {
}