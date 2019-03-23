package org.smarttechie.graphqldemo.repository;


import org.smarttechie.graphqldemo.model.Seo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeoRepository extends JpaRepository<Seo, String> {
}
