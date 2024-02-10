package com.example.springboot_googlelogin_jwt.member.controller;

import com.example.springboot_googlelogin_jwt.member.controller.response.MemberListResponse;
import com.example.springboot_googlelogin_jwt.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/api/members")
    public ResponseEntity<MemberListResponse> getMembers() {
        return ResponseEntity.ok(new MemberListResponse(memberService.getMembers()));
    }

    @GetMapping("/api/admin/members/")
    public ResponseEntity<MemberListResponse> getAdminMembers() {
        return ResponseEntity.ok(new MemberListResponse(memberService.getMembers()));
    }
}
