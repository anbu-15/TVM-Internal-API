package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Skill;
import com.tvm.internal.edit.repo.SkillRepository;
import com.tvm.internal.edit.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        return skill.orElse(null);
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        if (!skillRepository.existsById(id)) {
            return null;
        }
        skill.setId(id);
        return skillRepository.save(skill);
    }

    @Override
    public ResponseEntity<String> deleteSkill(Long id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Skill deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill not found with id: " + id);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return skillRepository.existsById(id);
    }
}