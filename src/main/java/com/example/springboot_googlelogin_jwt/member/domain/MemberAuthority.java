package com.example.springboot_googlelogin_jwt.member.domain;

import lombok.Getter;

@Getter
public enum MemberAuthority {
    USER("사용자"),
    ADMIN("관리자");

    private final String korean;

    MemberAuthority(String korean){
        this.korean = korean;
    }

    public static MemberAuthority from(String korean){
        for(MemberAuthority memberAuthority : MemberAuthority.values()){
            if(memberAuthority.getKorean().equals(korean)){
                return memberAuthority;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 사용자 권한입니다.");
    }
}
