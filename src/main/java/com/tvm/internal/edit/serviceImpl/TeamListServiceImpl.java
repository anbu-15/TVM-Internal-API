package com.tvm.internal.edit.serviceImpl;


import com.tvm.internal.edit.model.TeamList;
import com.tvm.internal.edit.repo.TeamListRepository;
import com.tvm.internal.edit.service.TeamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamListServiceImpl implements TeamListService {

    @Autowired
    private TeamListRepository teamListRepository;

    @Override
    public List<TeamList> getAllTeamMembers() {
        return teamListRepository.findAll();
    }

    @Override
    public Optional<TeamList> getTeamMemberById(Long id) {
        return teamListRepository.findById(id);
    }

    @Override
    public TeamList createTeamMember(TeamList teamMember) {
        return teamListRepository.save(teamMember);
    }

    @Override
    public TeamList updateTeamMember(Long id, TeamList updatedTeamMember) {
        return teamListRepository.findById(id)
                .map(member -> {
                    member.setFirstName(updatedTeamMember.getFirstName());
                    member.setLastName(updatedTeamMember.getLastName());
                    member.setNickName(updatedTeamMember.getNickName());
                    member.setEmailAddress(updatedTeamMember.getEmailAddress());
                    member.setPhoto(updatedTeamMember.getPhoto());
                    member.setDepartment(updatedTeamMember.getDepartment());
                    member.setDesignation(updatedTeamMember.getDesignation());
                    member.setRole(updatedTeamMember.getRole());
                    member.setEmploymentType(updatedTeamMember.getEmploymentType());
                    member.setPresentAddress(updatedTeamMember.getPresentAddress());
                    member.setAadharNumber(updatedTeamMember.getAadharNumber());
                    member.setPan(updatedTeamMember.getPan());
                    member.setUan(updatedTeamMember.getUan());
                    return teamListRepository.save(member);
                }).orElseThrow(() -> new RuntimeException("Team member not found"));
    }

    @Override
    public void deleteTeamMember(Long id) {
        teamListRepository.deleteById(id);
    }
}
