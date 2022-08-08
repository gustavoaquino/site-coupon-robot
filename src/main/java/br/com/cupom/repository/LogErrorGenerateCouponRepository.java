package br.com.cupom.repository;

import br.com.cupom.domain.LogErrorCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogErrorGenerateCouponRepository extends JpaRepository<LogErrorCoupon, Long> {
}
