package com.contacttura.contacttura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contacttura.contacttura.model.ContactturaUser;

public interface ContactturaUserRepository extends JpaRepository<ContactturaUser, Long>{

}