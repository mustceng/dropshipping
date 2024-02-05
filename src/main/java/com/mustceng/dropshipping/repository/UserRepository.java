package com.mustceng.dropshipping.repository;

import com.mustceng.dropshipping.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Page<User> findAll(Pageable pageable);

	Optional<User> findByUsername(@Param("username") String username);

	Optional<User> findByIdAndActive(@Param("id") Long id, @Param("active") Boolean active);

}
