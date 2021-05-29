package com.mstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mstore.domain.Account;


@Repository
public interface AccountDAO extends JpaRepository<Account, String>{

}
