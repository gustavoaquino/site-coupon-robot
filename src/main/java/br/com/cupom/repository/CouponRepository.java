package br.com.cupom.repository;

import br.com.cupom.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query(value = "SELECT * FROM CUPOM.COUPON WHERE COMPANY_ID = ? AND DATE_EXCLUDED IS NULL AND DATE_EXPIRATION >= DATE_TRUNC('DAY', NOW())", nativeQuery = true)
    List<Coupon> findAllByCompanyIdWhereDateExpiredIsMoreThanNow(Long companyId);

    @Query(value = "SELECT COUNT(*) FROM CUPOM.COUPON", nativeQuery = true)
    int getCountCoupon();

    @Query(value = "SELECT * FROM CUPOM.COUPON WHERE DATE_EXCLUDED IS NULL AND DATE_EXPIRATION >= DATE_TRUNC('DAY', NOW()) limit 18", nativeQuery = true)
    List<Coupon> findAllCouponPrincipalPage();

}

