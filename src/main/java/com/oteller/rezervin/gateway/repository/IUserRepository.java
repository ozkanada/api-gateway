package com.oteller.rezervin.gateway.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oteller.rezervin.gateway.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

    User getByUserIdAndDeletedFalse(Long userId);

    Page<User> getAllByDeletedFalse(Pageable pageable);

    User findByEmail(String email);

    Page<User> getAllByUserIdNotAndDeletedFalse(long id, Pageable pageable);

	User findByPasswordResetToken(String token);

	User findByUsername(String username);

}
