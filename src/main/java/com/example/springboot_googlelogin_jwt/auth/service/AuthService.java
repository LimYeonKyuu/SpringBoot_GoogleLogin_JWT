package com.example.springboot_googlelogin_jwt.auth.service;

import com.example.springboot_googlelogin_jwt.auth.dto.AuthDto;
import com.example.springboot_googlelogin_jwt.auth.util.JwtUtil;
import com.example.springboot_googlelogin_jwt.member.domain.Member;
import com.example.springboot_googlelogin_jwt.member.domain.MemberAuthority;
import com.example.springboot_googlelogin_jwt.member.repository.MemberRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    private final GoogleIdTokenVerifier verifier;

    @Value("${custom.jwt.secret}")
    private String SECRET_KEY;

    @Value("${custom.jwt.expire-time-ms}")
    private long EXPIRE_TIME_MS;

    public Member getLoginMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }

    public AuthDto loginOAuthGoogle(AuthDto dto) {
        Member member = verifyIDToken(dto.getGoogleIdToken());
        if (member == null) {
            throw new IllegalArgumentException();
        }
        member = createOrUpdateUser(member);
        return AuthDto.builder().jwt(JwtUtil.createToken(member.getId(),SECRET_KEY,EXPIRE_TIME_MS)).build();
    }

    public Member createOrUpdateUser(Member member) {
        Member existingMember = memberRepository.findByEmail(member.getEmail());
        if (existingMember == null) {
            member.setAuthority(MemberAuthority.USER);
            memberRepository.save(member);
            return member;
        }
        existingMember.setFirstName(member.getFirstName());
        existingMember.setLastName(member.getLastName());
        existingMember.setGoogleProfilePictureUrl(member.getGoogleProfilePictureUrl());
        return existingMember;
    }

    private Member verifyIDToken(String idToken) {
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
            }
            return Member.from(idTokenObj.getPayload());
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }
}