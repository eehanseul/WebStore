package com.example.webstore.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByUserId(String userId);
    Member findByUserIdAndPw(String userId, String pw);
    void deleteByUserId(String userId);
}
