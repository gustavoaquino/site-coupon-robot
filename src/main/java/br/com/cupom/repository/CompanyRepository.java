package br.com.cupom.repository;

import br.com.cupom.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "SELECT * FROM CUPOM.COMPANY ORDER BY ID ASC LIMIT 6", nativeQuery = true)
    List<Company> findAllLimit6ByOrderByIdAsc();

    @Query(value = "SELECT * FROM CUPOM.COMPANY ORDER BY NAME_COMPANY ASC", nativeQuery = true)
    List<Company> findAllCompanyByOrderByNameCompanyAsc();

    @Query(value = "SELECT * FROM CUPOM.COMPANY WHERE URI_SOCIAL_SOUL = ? ", nativeQuery = true)
    Optional<Company> findCompanyByUriSocialSoul(String uriSocialSoul);

}
