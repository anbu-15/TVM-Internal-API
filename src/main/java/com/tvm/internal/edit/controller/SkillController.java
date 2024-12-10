package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Skill;
import com.tvm.internal.edit.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill")
public class SkillController {
    private static final Logger logger = LoggerFactory.getLogger(SkillController.class);

    @Autowired
    private SkillService skillService;

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill createdSkill = skillService.createSkill(skill);
        logger.info("Successfully created skill: {}", createdSkill);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSkill);
    }

    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        logger.info("Successfully retrieved all skills");
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSkillById(@PathVariable Long id) {
        Skill skill = skillService.getSkillById(id);
        if (!skillService.existsById(id)) {
            logger.warn("Skill not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-Message", "Skill not found with id: " + id).body("Skill not found with id: " + id);
        }

        logger.info("Successfully retrieved skill with id: {}", id);
        return ResponseEntity.ok(skill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        Skill updatedSkill = skillService.updateSkill(id, skill);
        if (updatedSkill == null) {
            logger.info("Skill not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill not found with id: " + id);
        }
        logger.info("Successfully updated skill with id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSkill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long id) {
        ResponseEntity<String> deleteResponse = skillService.deleteSkill(id);
        if (deleteResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            logger.info("Skill not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill not found with id: " + id);
        }
        logger.info("Skill deleted skill with id: {}", id);
        return deleteResponse;
    }
}

