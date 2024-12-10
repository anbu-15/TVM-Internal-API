package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Skill;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface SkillService {

    Skill createSkill(Skill skill);

    List<Skill> getAllSkills();

    Skill getSkillById(Long id);

    Skill updateSkill(Long id, Skill skill);

    ResponseEntity<String> deleteSkill(Long id);

    boolean existsById(Long id);
}