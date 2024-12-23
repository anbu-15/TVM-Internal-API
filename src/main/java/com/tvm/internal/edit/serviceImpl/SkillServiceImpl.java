package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Skill;
import com.tvm.internal.edit.repo.SkillRepository;
import com.tvm.internal.edit.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    private static final Logger logger = LoggerFactory.getLogger(SkillServiceImpl.class);

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> createSkills(List<Skill> skills) {
        return skillRepository.saveAll(skills);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (!skillRepository.existsById(id)) {
            logger.error("Skill not found with id: " + id);
            return null;
        }
        logger.info("Successfully retrieved skill with id: {}", id);
        return skill.orElse(null);
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        Skill updateskill = skillRepository.findById(id).orElse(null);
        if (!skillRepository.existsById(id)) {
            logger.error("Skill not found with id: " + id);
            return null;
        }
        logger.error("Successfully updated skill with id: " + id);
        updateskill.setName(skill.getName());
        updateskill.setYof(skill.getYof());
        updateskill.setRating(skill.getRating());
        return skillRepository.save(updateskill);
    }

    @Override
    public ResponseEntity<String> deleteSkill(Long id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
            logger.warn("Skill deleted successfully: " + id);
            return ResponseEntity.status(HttpStatus.OK).body("Skill deleted successfully");
        } else {
            logger.warn("Skill not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill not found with id: " + id);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return skillRepository.existsById(id);
    }
}