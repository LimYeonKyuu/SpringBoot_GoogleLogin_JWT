package com.example.springboot_googlelogin_jwt.member.service;

import com.example.springboot_googlelogin_jwt.member.dto.MemberDto;
import com.example.springboot_googlelogin_jwt.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberDto> getMembers() {
        return memberRepository.findAll().stream()
                .map(MemberDto::from)
                .toList();
    }
}
