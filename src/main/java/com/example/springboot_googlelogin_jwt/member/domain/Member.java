package com.example.springboot_googlelogin_jwt.member.domain;

import com.example.springboot_googlelogin_jwt.base.domain.BaseTime;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;
    @Column(name = "email", length = 30, nullable = false)
    private String email;
    @Column(name = "authority", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberAuthority authority;
    @Column(name = "google_profile_picture_url", length = 100, nullable = false)
    private String googleProfilePictureUrl;

    public static Member from(GoogleIdToken.Payload payload) {
        Member member = new Member();
        member.setFirstName((String) payload.get("given_name"));
        member.setLastName((String) payload.get("family_name"));
        member.setEmail(payload.getEmail());
        member.setAuthority(MemberAuthority.USER);
        member.setGoogleProfilePictureUrl((String) payload.get("picture"));
        return member;
    }
}
