package com.example.springboot_googlelogin_jwt.member.dto;

import com.example.springboot_googlelogin_jwt.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String authority;
    private String googleProfilePictureUrl;

    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .email(member.getEmail())
                .authority(member.getAuthority().getKorean())
                .googleProfilePictureUrl(member.getGoogleProfilePictureUrl())
                .build();
    }
}
