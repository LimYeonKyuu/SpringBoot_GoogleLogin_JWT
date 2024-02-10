package com.example.springboot_googlelogin_jwt.member.controller.response;

import com.example.springboot_googlelogin_jwt.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberListResponse {
    private final List<Member> members;

    public MemberListResponse(List<MemberDto> members) {
        this.members = members.stream()
                .map(Member::from)
                .toList();
    }

    @Getter
    @Builder
    private static class Member {
        private String firstName;
        private String lastName;
        private String googleProfilePictureUrl;

        public static Member from(MemberDto dto) {
            return Member.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .googleProfilePictureUrl(dto.getGoogleProfilePictureUrl())
                    .build();
        }
    }
}
