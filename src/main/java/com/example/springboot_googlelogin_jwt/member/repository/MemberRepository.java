package com.example.springboot_googlelogin_jwt.member.repository;

import com.example.springboot_googlelogin_jwt.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
