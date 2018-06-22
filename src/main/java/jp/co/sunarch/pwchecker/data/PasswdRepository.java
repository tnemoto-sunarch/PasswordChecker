package jp.co.sunarch.pwchecker.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswdRepository extends JpaRepository<TCommonPassEntity, Integer>{

	public TCommonPassEntity findByPassword(String password);
}
